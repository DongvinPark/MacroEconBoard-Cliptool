package index.KR.stock;

import static constants.Constant.*;
import static utils.General.getDoubleVal;
import static utils.General.getLongVal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import index.Index;
import java.io.BufferedReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import utils.Logger;

public class Kosdaq implements Index {

  private static class KosdaqData {
    public String time;
    public double open;
    public double high;
    public double low;
    public double close;
    public long volume;

    public KosdaqData(
        String time, double open, double high, double low, double close, long volume
    ) {
      this.time = time;
      this.open = open;
      this.high = high;
      this.low = low;
      this.close = close;
      this.volume = volume;
    }
  }

  @Override
  public boolean convertToJson(String csvPath, String jsonDirPath) {
    try {
      Path inputCsv  = Paths.get(csvPath);
      Path outputDir = Paths.get(jsonDirPath);

      if (!Files.exists(outputDir)){
        Logger.error(PATH_NOT_EXISTS + ":" + jsonDirPath);
        return false;
      }

      Map<Integer, List<KosdaqData>> yearMap = new TreeMap<>();

      try ( BufferedReader br = Files.newBufferedReader(inputCsv) ){

        String header = br.readLine();
        String line;

        while( (line = br.readLine()) != null ){
          String[] tokens = line.split(",");

          if(tokens.length < KOSDAU_HEADER_LENGTH){
            continue;
          }

          String dateStr = tokens[0].split(" ")[0];
          LocalDate date = LocalDate.parse(dateStr);
          int year = date.getYear();

          KosdaqData data = new KosdaqData(
              dateStr,
              getDoubleVal(tokens[1]),
              getDoubleVal(tokens[2]),
              getDoubleVal(tokens[3]),
              getDoubleVal(tokens[4]),
              getLongVal(tokens[5])
          );

          yearMap.computeIfAbsent(year, y -> new ArrayList<>()).add(data);
        }//wh
      }

      Gson gson = new GsonBuilder().setPrettyPrinting().create();

      for ( Map.Entry<Integer, List<KosdaqData>> entry : yearMap.entrySet()){
        int year = entry.getKey();
        List<KosdaqData> yearData = entry.getValue();

        // 날짜 오름차순 정렬.
        yearData.sort((a,b) -> {
          LocalDate before = LocalDate.parse(a.time);
          LocalDate after = LocalDate.parse(b.time);
          return before.compareTo(after);
        });

        Path outputFile = outputDir.resolve(year + KOSDAQ_JSON_SUFFIX + JSON_EXTENSION);

        try(Writer writer = Files.newBufferedWriter(outputFile)){
          gson.toJson(yearData, writer);
        }

      }

      Logger.info("csv to json translation completed. dir : " + outputDir);
      return true;
    } catch(Exception e){
      e.printStackTrace();
      return false;
    }
  }

}
