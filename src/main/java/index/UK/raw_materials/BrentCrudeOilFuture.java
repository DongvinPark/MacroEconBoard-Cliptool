package index.UK.raw_materials;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import index.Index;
import index.UK.dto.BrentOilFutureDto;
import index.UK.dto.BrentOilFutureDtoForJson;
import index.US.dto.GoldFutureDto;
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

public class BrentCrudeOilFuture implements Index {
  @Override
  public boolean convertToJson(String inputCsvPath, String jsonDirPath) {
    try {
      Path inputCsv = Paths.get(inputCsvPath);
      Path outputDir = Paths.get(jsonDirPath);

      try( BufferedReader br = Files.newBufferedReader(inputCsv) ){
        String header = br.readLine();

        String line;
        List<BrentOilFutureDto> dtoList = new ArrayList<>();
        while( (line=br.readLine()) != null ){
          String[] tokens = line.split(",");
          if(tokens.length < BRENT_OIL_FUTURE_CSV_HEADER_LENGTH){
            continue;
          }
          String dateStr = removeQuotationMarks(tokens[0].split(" ")[0]);
          String interestVal = removeQuotationMarks(tokens[1]);
          LocalDate date = LocalDate.parse(dateStr);
          dtoList.add(
              new BrentOilFutureDto(date, getDoubleVal(interestVal))
          );
        }//wh

        // 날짜 오름차순 정렬
        dtoList.sort(Comparator.comparing(b -> b.time));

        Map<Integer, List<BrentOilFutureDto>> yearMap = new TreeMap<>();
        for (BrentOilFutureDto dto : dtoList){
          int year = dto.time.getYear();
          yearMap.computeIfAbsent(year, y -> new ArrayList<>()).add(dto);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for( Map.Entry<Integer, List<BrentOilFutureDto>> entry : yearMap.entrySet()){
          int year = entry.getKey();
          List<BrentOilFutureDto> yearData = entry.getValue();
          List<BrentOilFutureDtoForJson> yearJsonData = new ArrayList<>();

          for (BrentOilFutureDto dto : yearData){
            yearJsonData.add(
                new BrentOilFutureDtoForJson(dto.time.toString(), dto.value)
            );
          }

          Path outputFile = outputDir.resolve(
              year + BRENT_OIL_FUTURE_JSON_SUFFIX + JSON_EXTENSION
          );

          try (Writer writer = Files.newBufferedWriter(outputFile)) {
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

}






















