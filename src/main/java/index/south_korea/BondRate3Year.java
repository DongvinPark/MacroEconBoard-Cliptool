package index.south_korea;

import static constants.Constant.BOND_3_YAR_EXCEL_TO_CSV_SUFFIX;
import static constants.Constant.BOND_3_YEAR_JSON_SUFFIX;
import static constants.Constant.BOND_HEADER_LENGTH;
import static constants.Constant.CSV_EXTENSION;
import static constants.Constant.JSON_EXTENSION;
import static utils.General.getDoubleVal;
import static utils.General.removeQuotationMarks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import index.Index;
import index.south_korea.dto.BondDto;
import index.south_korea.dto.BondDtoForJson;
import java.io.BufferedReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import meta.GraphMeta;
import utils.ExcelToCsv;
import utils.Logger;

public class BondRate3Year implements Index {

  @Override
  public boolean convertToJson(String excelPath, String jsonDirPath) {

    String csvPath = jsonDirPath + "\\" + BOND_3_YAR_EXCEL_TO_CSV_SUFFIX + CSV_EXTENSION;
    boolean translateResult = ExcelToCsv.convertExcelToCSV(
        excelPath, csvPath
    );

    if(!translateResult){
      Logger.error("failed to convert excel to csv! excel path : " + excelPath);
      return false;
    }

    try {
      // TODO : implement later
      Path inputCsv = Paths.get(csvPath);
      Path outputDir = Paths.get(jsonDirPath);

      try ( BufferedReader br = Files.newBufferedReader(inputCsv) ) {

        // TODO : 이 헤더는 나중에 메타데이터 만들 때 활용한다.
        String header1 = br.readLine();
        String header2 = br.readLine();
        String header3 = br.readLine();
        String header4 = br.readLine();
        System.out.println("header1 = " + header1);
        System.out.println("header2 = " + header2);
        System.out.println("header3 = " + header3);
        System.out.println("header4 = " + header4);

        String line;

        List<BondDto> dtoList = new ArrayList<>();
        while( (line=br.readLine()) != null ){
          String[] tokens = line.split(",");
          if(tokens.length < BOND_HEADER_LENGTH){
            continue;
          }

          String dateStr = removeQuotationMarks(tokens[0]);
          String interestVal = removeQuotationMarks(tokens[1]);
          LocalDate date = LocalDate.parse(dateStr);
          dtoList.add(
              new BondDto(date, getDoubleVal(interestVal))
          );
        }//wh

        dtoList.sort(Comparator.comparing(b -> b.time));
        Map<Integer, List<BondDto>> yearMap = new TreeMap<>();
        for (BondDto dto : dtoList) {
          int year = dto.time.getYear();
          yearMap.computeIfAbsent(year, y -> new ArrayList<>()).add(dto);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for( Map.Entry<Integer, List<BondDto>> entry : yearMap.entrySet() ){
          int year = entry.getKey();
          List<BondDto> yearData = entry.getValue();
          List<BondDtoForJson> yearJsonData = new ArrayList<>();

          for(BondDto dto : yearData){
            yearJsonData.add(
                new BondDtoForJson(dto.time.toString(), dto.value)
            );
          }

          Path outputFile = outputDir.resolve(
              year + BOND_3_YEAR_JSON_SUFFIX + JSON_EXTENSION
          );

          try(Writer writer = Files.newBufferedWriter(outputFile)){
            gson.toJson(yearJsonData, writer);
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

  @Override
  public GraphMeta buildGraphMeta(String graphName, String yAxisUnit, ChronoUnit timeUnit) {
    // TODO : json들 업로드할 때 같이 업로드 한다. 단, 이미 S3에 업로드 돼 있을 때는 제외.
    return null;
  }

  @Override
  public boolean loadToOriginStorage(String dir) {
    // TODO : 나중에 S3 클라이언트 호출해야 한다.
    return false;
  }

}
