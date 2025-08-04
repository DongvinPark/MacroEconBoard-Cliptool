package index.south_korea;

import static constants.Constant.KOSPI_HEADER_LENGTH;
import static constants.Constant.PATH_NOT_EXISTS;

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

public class Kospi implements Index {

  private class KospiData {
    public String time;
    public double open;
    public double high;
    public double low;
    public double close;
    public long volume;

    public KospiData(String time, double open, double high, double low, double close, long volume) {
      this.time = time;
      this.open = open;
      this.high = high;
      this.low = low;
      this.close = close;
      this.volume = volume;
    }
  }

  @Override
  public boolean convertToJson(String csvPath, String jsonPath) {
    try {
      Path inputCsv  = Paths.get(csvPath);
      Path outputDir = Paths.get(jsonPath);

      if (!Files.exists(outputDir)){
        System.out.println(PATH_NOT_EXISTS);
        return false;
      }

      Map<Integer, List<KospiData>> yearMap = new TreeMap<>();

      try ( BufferedReader br = Files.newBufferedReader(inputCsv) ){
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

          KospiData data = new KospiData(
              dateStr,
              parseDouble(tokens[1]),
              parseDouble(tokens[2]),
              parseDouble(tokens[3]),
              parseDouble(tokens[4]),
              parseLong(tokens[5])
          );

          yearMap.computeIfAbsent(year, y -> new ArrayList<>()).add(data);
        }//wh
      }

      Gson gson = new GsonBuilder().setPrettyPrinting().create();

      for ( Map.Entry<Integer, List<KospiData>> entry : yearMap.entrySet()){
        int year = entry.getKey();
        List<KospiData> yearData = entry.getValue();

        Path outputFile = outputDir.resolve(year + "_kospi.json");

        try(Writer writer = Files.newBufferedWriter(outputFile)){
          gson.toJson(yearData, writer);
        }

      }

      System.out.println("csv to json 변환 완료. dir : " + outputDir);
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

  private static double parseDouble(String str) {
    try {
      return Double.parseDouble(str);
    } catch (Exception e) {
      return 0.0;
    }
  }

  private static long parseLong(String str) {
    try {
      return Long.parseLong(str);
    } catch (Exception e) {
      return 0L;
    }
  }
}
