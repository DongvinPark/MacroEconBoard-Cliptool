package index.US.nasdaq_compo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import index.Index;
import index.US.origin_bitcoin.BitcoinOriginal;
import utils.Logger;

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

import static constants.Constant.*;
import static constants.Constant.JSON_EXTENSION;
import static utils.General.getDoubleVal;
import static utils.General.getLongVal;

public class Nasdaq implements Index {

  private static class NasdaqData {
    public String time;
    public double open;
    public double high;
    public double low;
    public double close;
    public long volume;

    public NasdaqData(
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
  public boolean convertToJson(String inputCsvPath, String jsonDirPath) {
    try {
      Path inputCsv = Paths.get(inputCsvPath);
      Path outputDir = Paths.get(jsonDirPath);

      if (!Files.exists(outputDir)){
        Logger.error(PATH_NOT_EXISTS + ":" + jsonDirPath);
        return false;
      }

      Map<Integer, List<Nasdaq.NasdaqData>> yearMap = new TreeMap<>();

      try ( BufferedReader br = Files.newBufferedReader(inputCsv) ){
        String header = br.readLine();
        String line;

        while((line = br.readLine()) != null){
          String[] token = line.split(",");

          if(token.length < NASDAQ_CSV_HEADER_LENGTH){
            continue;
          }

          String dateStr = token[0].split(" ")[0];
          LocalDate date = LocalDate.parse(dateStr);
          int year = date.getYear();

          Nasdaq.NasdaqData data = new Nasdaq.NasdaqData(
              dateStr,
              getDoubleVal(token[1]),
              getDoubleVal(token[2]),
              getDoubleVal(token[3]),
              getDoubleVal(token[4]),
              getLongVal(token[5])
          );

          yearMap.computeIfAbsent(year, y -> new ArrayList<>()).add(data);
        }//wh
      }

      Gson gson = new GsonBuilder().setPrettyPrinting().create();

      for ( Map.Entry<Integer, List<Nasdaq.NasdaqData>> entry : yearMap.entrySet()){
        int year = entry.getKey();
        List<Nasdaq.NasdaqData> yearData = entry.getValue();

        // 날짜 오름차순 정렬.
        yearData.sort((a,b) -> {
          LocalDate before = LocalDate.parse(a.time);
          LocalDate after = LocalDate.parse(b.time);
          return before.compareTo(after);
        });

        Path outputFile = outputDir.resolve(year + NASDAQ_JSON_SUFFIX + JSON_EXTENSION);

        try(Writer writer = Files.newBufferedWriter(outputFile)){
          gson.toJson(yearData, writer);
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
