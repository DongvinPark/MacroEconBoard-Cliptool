package index;

import java.time.temporal.ChronoUnit;
import meta.GraphMeta;

public interface Index {

  boolean convertToJson(String csvPath, String jsonDirPath);

}
