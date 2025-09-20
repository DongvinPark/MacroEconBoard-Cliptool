package index.KR.interest;

import index.Index;
import java.time.temporal.ChronoUnit;
import meta.GraphMeta;

public class BaseInterestRate implements Index {

  @Override
  public boolean convertToJson(String csvPath, String jsonDirPath) {
    return false;
  }

  @Override
  public GraphMeta buildGraphMeta(String graphName, String yAxisUnit, ChronoUnit timeUnit) {
    return null;
  }

  @Override
  public boolean loadToOriginStorage(String dir) {
    return false;
  }
}
