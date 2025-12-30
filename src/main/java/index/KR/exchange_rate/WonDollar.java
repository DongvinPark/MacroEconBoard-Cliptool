package index.KR.exchange_rate;

import static constants.Constant.CSV_EXTENSION;
import static constants.Constant.JSON_EXTENSION;
import static constants.Constant.WON_DOLLAR_EXCHANGE_RATE_EXCEL_TO_CSV_SUFFIX;
import static constants.Constant.WON_DOLLAR_EXCHANGE_RATE_HEADER_LENGTH;
import static constants.Constant.WON_DOLLAR_EXCHANGE_RATE_JSON_SUFFIX;
import static utils.General.getDoubleVal;
import static utils.General.removeQuotationMarks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import index.Index;
import index.KR.dto.WonDollarDtoForJson;
import index.KR.dto.WonDollarDto;
import java.io.BufferedReader;
import java.io.File;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import utils.ExcelToCsv;
import utils.Logger;

public class WonDollar implements Index {

  @Override
  public boolean convertToJson(String excelPath, String jsonDirPath) {

    String csvPath = jsonDirPath + File.separator + WON_DOLLAR_EXCHANGE_RATE_EXCEL_TO_CSV_SUFFIX + CSV_EXTENSION;
    boolean translateResult = ExcelToCsv.convertExcelToCSV(
        excelPath, csvPath
    );

    if(!translateResult){
      Logger.error("failed to convert excel to csv! excel path : " + excelPath);
      return false;
    }

    try {
      Path inputCsv = Paths.get(csvPath);
      Path outputDir = Paths.get(jsonDirPath);

      try (BufferedReader br = Files.newBufferedReader(inputCsv)) {
        String header1 = br.readLine();
        String header2 = br.readLine();
        String header3 = br.readLine();
        String header4 = br.readLine();
        String header5 = br.readLine();
        String header6 = br.readLine();
        String header7 = br.readLine();
        System.out.println("header1 = " + header1);
        System.out.println("header2 = " + header2);
        System.out.println("header3 = " + header3);
        System.out.println("header4 = " + header4);
        System.out.println("header5 = " + header5);
        System.out.println("header6 = " + header6);
        System.out.println("header7 = " + header7);

        String line;
        List<WonDollarDto> dtoList = new ArrayList<>();
        while( (line=br.readLine()) != null ){
          String[] tokens = line.split(",");
          if(tokens.length < WON_DOLLAR_EXCHANGE_RATE_HEADER_LENGTH){
            continue;
          }
          // 예를 들어서, 2008년 2월 06~08 일은 평일이기는 하지만 설연휴였기 때문에 값이 없다.
          // 따라서 걸러야 한다.
          if(tokens[1].equals("\"\"")){
            continue;
          }

          String dateStr = removeQuotationMarks(tokens[0]);
          String rateVal = removeQuotationMarks(tokens[1]);
          LocalDate date = LocalDate.parse(dateStr);
          dtoList.add(
              new WonDollarDto(date, getDoubleVal(rateVal))
          );

        }//wh

        // 날짜 오름차순 정렬
        dtoList.sort(Comparator.comparing(b -> b.time));

        Map<Integer, List<WonDollarDto>> yearMap = new TreeMap<>();
        for (WonDollarDto dto : dtoList) {
          int year = dto.time.getYear();
          yearMap.computeIfAbsent(year, y -> new ArrayList<>()).add(dto);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for( Map.Entry<Integer, List<WonDollarDto>> entry : yearMap.entrySet() ){
          int year = entry.getKey();
          List<WonDollarDto> yearData = entry.getValue();
          List<WonDollarDtoForJson> yearJsonData = new ArrayList<>();

          for(WonDollarDto dto : yearData){
            yearJsonData.add(
                new WonDollarDtoForJson(dto.time.toString(), dto.value)
            );
          }

          Path outputFile = outputDir.resolve(
              year + WON_DOLLAR_EXCHANGE_RATE_JSON_SUFFIX + JSON_EXTENSION
          );

          // 과거 데이터는 전부 만들었으므로, 2025/12/30 부터는 올해 꺼만 새로 만들면 된다.
          LocalDate today = LocalDate.now();
          if(year == today.getYear()){
            try(Writer writer = Files.newBufferedWriter(outputFile)){
              gson.toJson(yearJsonData, writer);
            }
          }
        }
      }

      Logger.info("csv to json translation completed. dir : " + outputDir);
      return true;
    } catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }

}
