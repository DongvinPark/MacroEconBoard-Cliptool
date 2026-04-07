package index.MARKET_CONDITION.risk.tenY_twoY_diff;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import index.Index;
import index.MARKET_CONDITION.dto.TenYearTwoYearDiffDto;
import index.MARKET_CONDITION.dto.TenYearTwoYearDiffDtoForJson;
import utils.Logger;

import java.io.BufferedReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

import static constants.Constant.*;
import static utils.General.getDoubleVal;
import static utils.General.removeQuotationMarks;

public class TenYearTwoYearSpread implements Index {

  @Override
  public boolean convertToJson(String inputCsvPath, String jsonDirPath) {
    try {
      Path inputCsv = Paths.get(inputCsvPath);
      Path outputDir = Paths.get(jsonDirPath);

      try( BufferedReader br = Files.newBufferedReader(inputCsv) ) {
        String header = br.readLine();

        String line;
        List<TenYearTwoYearDiffDto> dtoList = new ArrayList<>();
        while( (line=br.readLine()) != null ){
          String[] tokens = line.split(",");
          if(tokens.length < US_10Y_MINUS_2Y_DIFF_CSV_HEADER_LENGTH){
            continue;
          }
          String dateStr = removeQuotationMarks(tokens[0]);
          String interestVal = removeQuotationMarks(tokens[1]);
          LocalDate date = LocalDate.parse(dateStr);
          dtoList.add(
              new TenYearTwoYearDiffDto(date, getDoubleVal(interestVal))
          );
        }//wh

        // 날짜 오름차순 정렬.
        dtoList.sort(Comparator.comparing(b -> b.time));

        Map<Integer, List<TenYearTwoYearDiffDto>> yearMap = new TreeMap<>();
        for (TenYearTwoYearDiffDto dto : dtoList) {
          int year = dto.time.getYear();
          yearMap.computeIfAbsent(year, y -> new ArrayList<>()).add(dto);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for( Map.Entry<Integer, List<TenYearTwoYearDiffDto>> entry : yearMap.entrySet() ) {
          int year = entry.getKey();
          List<TenYearTwoYearDiffDto> yearData = entry.getValue();
          List<TenYearTwoYearDiffDtoForJson> yearJsonData = new ArrayList<>();

          for (TenYearTwoYearDiffDto dto : yearData) {
            yearJsonData.add(
                new TenYearTwoYearDiffDtoForJson(dto.time.toString(), dto.value)
            );
          }

          Path outputFile = outputDir.resolve(
              year + US_10Y_MINUS_2Y_DIFF_JSON_SUFFIX + JSON_EXTENSION
          );

          // 과거 데이터는 전부 만들었으므로, 올해 꺼만 새로 만들면 된다.
          LocalDate today = LocalDate.now();
          if(year == today.getYear()){
            try (Writer writer = Files.newBufferedWriter(outputFile)) {
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
