package index.KR.interest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import index.Index;
import index.KR.dto.BaseInterestDto;
import index.KR.dto.BaseInterestDtoForJson;
import utils.Logger;

import java.io.BufferedReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static constants.Constant.*;
import static utils.DateUtil.getRangeDate;
import static utils.General.getDoubleVal;
import static utils.General.removeQuotationMarks;

public class BaseInterestRate implements Index {

  @Override
  public boolean convertToJson(String inputCsvRecordPath, String jsonDirPath) {
    try {
      List<BaseInterestDto> interestChangeRecordList = new ArrayList<>();

      Path csvPath = Paths.get(inputCsvRecordPath);
      Path outputDir = Paths.get(jsonDirPath);

      try ( BufferedReader br = Files.newBufferedReader(csvPath) ) {
        String header = br.readLine();
        System.out.println("header = " + header);

        String line;

        while( (line=br.readLine()) != null ){
          String[] tokens = line.split(",");
          if(tokens.length < BASE_INTEREST_RATE_RECORD_CSV_HEADER_LENGTH){
            continue;
          }

          String yearStr = removeQuotationMarks(tokens[0]);
          String monthAndDateStr = removeQuotationMarks(tokens[1]);
          String interestVal = removeQuotationMarks(tokens[2]);
          LocalDate date = LocalDate.parse(yearStr + "-" + monthAndDateStr);

          interestChangeRecordList.add(
              new BaseInterestDto(date, getDoubleVal(interestVal))
          );
        }//wh

        // 날짜 오름차순 정렬
        interestChangeRecordList.sort(Comparator.comparing(a -> a.time));

        List<BaseInterestDto> allDtoList
            = getBaseInterestDtoList(interestChangeRecordList);

        Map<Integer, List<BaseInterestDto>> yearMap = new TreeMap<>();
        for (BaseInterestDto dto : allDtoList) {
          int year = dto.time.getYear();
          yearMap.computeIfAbsent(year, y -> new ArrayList<>()).add(dto);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for( Map.Entry<Integer, List<BaseInterestDto>> entry : yearMap.entrySet() ){
          int year = entry.getKey();
          List<BaseInterestDto> yearData = entry.getValue();
          List<BaseInterestDtoForJson> yearJsonData = new ArrayList<>();

          for(BaseInterestDto dto : yearData){
            yearJsonData.add(
                new BaseInterestDtoForJson(dto.time.toString(), dto.value)
            );
          }

          Path outputFile = outputDir.resolve(
              year + KR_BASE_INTEREST_RATE_JSON_SUFFIX + JSON_EXTENSION
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

  private List<BaseInterestDto> getBaseInterestDtoList(
      List<BaseInterestDto> changeRecordList
  ) {
    // 작년의 기록이 올해의 기록에도 영향을 줄 수 있기 때문에 연도별로 따로 끊어서 만들면 안 된다.
    // 전체를 기록을 한 번에 만들어야 한다.
    List<BaseInterestDto> resultList = new ArrayList<>();

    for (int i = 0; i < changeRecordList.size(); i++) {
      List<LocalDate> targetDates = null;
      double value;
      if(i < (changeRecordList.size()-1)){
        value = changeRecordList.get(i).value;
        LocalDate start = changeRecordList.get(i).time;
        LocalDate end = changeRecordList.get(i+1).time;
        targetDates = getRangeDate(start, end);
      } else {
        value = changeRecordList.get(i).value;
        LocalDate start = changeRecordList.get(i).time;
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
        targetDates = getRangeDate(start, today);
      }

      for(LocalDate date : targetDates){
        resultList.add(new BaseInterestDto(date, value));
      }
    }
    return resultList;
  }

}






















