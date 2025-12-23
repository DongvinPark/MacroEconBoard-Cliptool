package index.US.interest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import index.Index;
import index.KR.dto.BondDto;
import index.KR.dto.BondDtoForJson;
import index.US.dto.FederalFundsRateDto;
import index.US.dto.FederalFundsRateDtoForJson;
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

public class FederalFundsRate implements Index {

  @Override
  public boolean convertToJson(String inputCsvPath, String jsonDirPath) {
    try {
      Path inputCsv = Paths.get(inputCsvPath);
      Path outputDir = Paths.get(jsonDirPath);

      try( BufferedReader br = Files.newBufferedReader(inputCsv) ){
        String header = br.readLine();

        String line;
        List<FederalFundsRateDto> dtoList = new ArrayList<>();
        while( (line=br.readLine()) != null ){
          String[] tokens = line.split(",");
          if(tokens.length < US_FEDERAL_FUNDS_RATE_CSV_HEADER_LENGTH){
            continue;
          }
          String dateStr = removeQuotationMarks(tokens[0]);
          String interestVal = removeQuotationMarks(tokens[1]);
          LocalDate date = LocalDate.parse(dateStr);
          dtoList.add(
              new FederalFundsRateDto(date, getDoubleVal(interestVal))
          );
        }//wh

        // 날짜 오름차순 정렬.
        dtoList.sort(Comparator.comparing(b -> b.time));

        Map<Integer, List<FederalFundsRateDto>> yearMap = new TreeMap<>();
        for (FederalFundsRateDto dto : dtoList) {
          int year = dto.time.getYear();
          yearMap.computeIfAbsent(year, y -> new ArrayList<>()).add(dto);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for( Map.Entry<Integer, List<FederalFundsRateDto>> entry : yearMap.entrySet() ) {
          int year = entry.getKey();
          List<FederalFundsRateDto> yearData = entry.getValue();
          List<FederalFundsRateDtoForJson> yearJsonData = new ArrayList<>();

          for (FederalFundsRateDto dto : yearData) {
            yearJsonData.add(
                new FederalFundsRateDtoForJson(dto.time.toString(), dto.value)
            );
          }

          Path outputFile = outputDir.resolve(
              year + US_FEDERAL_FUNDS_RATE_JSON_SUFFIX + JSON_EXTENSION
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


























