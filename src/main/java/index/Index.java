package index;

import java.time.temporal.ChronoUnit;
import meta.GraphMeta;

public interface Index {

  boolean convertToJson(String csvPath, String jsonDirPath);
  GraphMeta buildGraphMeta(String graphName, String yAxisUnit, ChronoUnit timeUnit);
  boolean loadToOriginStorage(String dir);

}
