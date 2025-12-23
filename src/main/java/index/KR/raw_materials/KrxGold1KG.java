package index.KR.raw_materials;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import index.Index;
import utils.Logger;

import java.io.BufferedReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

import static constants.Constant.*;
import static constants.Constant.JSON_EXTENSION;
import static utils.General.*;

public class KrxGold1KG implements Index {

  private static class KrxGold1KgData {
    public String time;
    public double open;
    public double high;
    public double low;
    public double close;
    public long volume;

    public KrxGold1KgData(
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

      Map<Integer, List<KrxGold1KgData>> yearMap = new TreeMap<>();

      try ( BufferedReader br = Files.newBufferedReader(inputCsv, Charset.forName("EUC-KR")) ){
        String header = br.readLine();
        String line;

        while( (line = br.readLine()) != null ){
          String[] token = line.split(",");

          if(token.length < KRX_GOLD_1KG_CSV_HEADER_LENGTH){
            continue;
          }

          String originDateStr = removeQuotationMarks(token[0]);
          String[] dateToken = originDateStr.split("/");
          String dateStr = dateToken[0] + "-" + dateToken[1] + "-" + dateToken[2];
          LocalDate date = LocalDate.parse(dateStr);
          int year = date.getYear();

          KrxGold1KgData data = new KrxGold1KgData(
              dateStr,
              getDoubleVal(removeQuotationMarks(token[4])),
              getDoubleVal(removeQuotationMarks(token[5])),
              getDoubleVal(removeQuotationMarks(token[6])),
              getDoubleVal(removeQuotationMarks(token[1])),
              getLongVal(removeQuotationMarks(token[7]))
          );

          yearMap.computeIfAbsent(year, y -> new ArrayList<>()).add(data);
        }//wh
      }

      Gson gson = new GsonBuilder().setPrettyPrinting().create();

      for ( Map.Entry<Integer, List<KrxGold1KG.KrxGold1KgData>> entry : yearMap.entrySet()){
        int year = entry.getKey();
        List<KrxGold1KG.KrxGold1KgData> yearData = entry.getValue();

        // 날짜 오름차순 정렬
        yearData.sort((a,b) -> {
          LocalDate before = LocalDate.parse(a.time);
          LocalDate after = LocalDate.parse(b.time);
          return before.compareTo(after);
        });

        Path outputFile = outputDir.resolve(year + KRX_GOLD_1KG_JSON_SUFFIX + JSON_EXTENSION);

        try(Writer writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)){
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



































