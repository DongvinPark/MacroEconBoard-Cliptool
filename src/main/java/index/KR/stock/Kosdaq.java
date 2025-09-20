package index.KR.stock;

import static constants.Constant.JSON_EXTENSION;
import static constants.Constant.KOSDAQ_SUFFIX;
import static constants.Constant.KOSPI_HEADER_LENGTH;
import static constants.Constant.PATH_NOT_EXISTS;
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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import meta.GraphMeta;
import utils.Logger;

public class Kosdaq implements Index {

  private static class KosdaqData {
    public String time;
    public double open;
    public double high;
    public double low;
    public double close;
    public long volume;

    public KosdaqData(String time, double open, double high, double low, double close, long volume) {
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
        Logger.error(PATH_NOT_EXISTS);
        return false;
      }

      Map<Integer, List<KosdaqData>> yearMap = new TreeMap<>();

      try ( BufferedReader br = Files.newBufferedReader(inputCsv) ){

        // TODO : 이 헤더는 나중에 메타데이터 만들 때 활용한다.
        String header = br.readLine();
        String line;

        while( (line = br.readLine()) != null ){
          String[] tokens = line.split(",");

          if(tokens.length < KOSPI_HEADER_LENGTH){
            continue;
          }

          String dateStr = tokens[0];
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

        Path outputFile = outputDir.resolve(year + KOSDAQ_SUFFIX + JSON_EXTENSION);

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
