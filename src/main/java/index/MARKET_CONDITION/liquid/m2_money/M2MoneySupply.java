package index.MARKET_CONDITION.liquid.m2_money;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import index.Index;
import index.MARKET_CONDITION.dto.M2Dto;
import index.MARKET_CONDITION.dto.M2DtoForJson;
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

public class M2MoneySupply implements Index {

  @Override
  public boolean convertToJson(String inputCsvPath, String jsonDirPath) {
    try {
      Path inputCsv = Paths.get(inputCsvPath);
      Path outputDir = Paths.get(jsonDirPath);

      try( BufferedReader br = Files.newBufferedReader(inputCsv) ) {
        String header = br.readLine();

        String line;
        List<M2Dto> dtoList = new ArrayList<>();
        while( (line=br.readLine()) != null ){
          String[] tokens = line.split(",");
          if(tokens.length < US_M2_MONEY_SUPPLY_CSV_HEADER_LENGTH){
            continue;
          }
          String dateStr = removeQuotationMarks(tokens[0]);
          String interestVal = removeQuotationMarks(tokens[1]);
          LocalDate date = LocalDate.parse(dateStr);
          dtoList.add(
              new M2Dto(date, getDoubleVal(interestVal))
          );
        }//wh

        // 날짜 오름차순 정렬.
        dtoList.sort(Comparator.comparing(b -> b.time));

        Map<Integer, List<M2Dto>> yearMap = new TreeMap<>();
        for (M2Dto dto : dtoList) {
          int year = dto.time.getYear();
          yearMap.computeIfAbsent(year, y -> new ArrayList<>()).add(dto);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for( Map.Entry<Integer, List<M2Dto>> entry : yearMap.entrySet() ) {
          int year = entry.getKey();
          List<M2Dto> yearData = entry.getValue();
          List<M2DtoForJson> yearJsonData = new ArrayList<>();

          for (M2Dto dto : yearData) {
            yearJsonData.add(
                new M2DtoForJson(dto.time.toString(), dto.value)
            );
          }

          Path outputFile = outputDir.resolve(
              year + US_M2_MONEY_SUPPLY_JSON_SUFFIX + JSON_EXTENSION
          );

          // 과거 데이터는 전부 만들었으므로, 2025/12/30 부터는 올해 꺼만 새로 만들면 된다.
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



























