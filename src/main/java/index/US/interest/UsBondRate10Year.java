package index.US.interest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import index.Index;
import index.US.dto.UsBondDto;
import index.US.dto.UsBondDtoForJson;
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

public class UsBondRate10Year implements Index {

  @Override
  public boolean convertToJson(String inputCsvPath, String jsonDirPath) {
    try {
      Path inputCsv = Paths.get(inputCsvPath);
      Path outputDir = Paths.get(jsonDirPath);

      try( BufferedReader br = Files.newBufferedReader(inputCsv) ){
        String header = br.readLine();

        String line;
        List<UsBondDto> dtoList = new ArrayList<>();
        while( (line=br.readLine()) != null ){
          String[] tokens = line.split(",");
          if(tokens.length < US_BOND_10Y_CSV_HEADER_LENGTH){
            continue;
          }
          String dateStr = removeQuotationMarks(tokens[0]);
          String interestVal = removeQuotationMarks(tokens[1]);
          LocalDate date = LocalDate.parse(dateStr);
          dtoList.add(
              new UsBondDto(date, getDoubleVal(interestVal))
          );
        }//wh

        // 날짜 오름차순 정렬.
        dtoList.sort(Comparator.comparing(b -> b.time));

        Map<Integer, List<UsBondDto>> yearMap = new TreeMap<>();
        for (UsBondDto dto : dtoList) {
          int year = dto.time.getYear();
          yearMap.computeIfAbsent(year, y -> new ArrayList<>()).add(dto);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for( Map.Entry<Integer, List<UsBondDto>> entry : yearMap.entrySet() ) {
          int year = entry.getKey();
          List<UsBondDto> yearData = entry.getValue();
          List<UsBondDtoForJson> yearJsonData = new ArrayList<>();

          for (UsBondDto dto : yearData) {
            yearJsonData.add(
                new UsBondDtoForJson(dto.time.toString(), dto.value)
            );
          }

          Path outputFile = outputDir.resolve(
              year + US_BOND_10Y_RATE_JSON_SUFFIX + JSON_EXTENSION
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
