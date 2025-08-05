package index.south_korea;

import static constants.Constant.BOND_3YAR__EXCEL_TO_CSV_SUFFIX;
import static constants.Constant.CSV_EXTENSION;

import index.Index;
import java.time.temporal.ChronoUnit;
import meta.GraphMeta;
import utils.ExcelToCsv;
import utils.Logger;

public class BondRate3Year implements Index {

  @Override
  public boolean convertToJson(String excelPath, String jsonPath) {

    String csvPath = jsonPath + "\\" + BOND_3YAR__EXCEL_TO_CSV_SUFFIX + CSV_EXTENSION;
    boolean translateResult = ExcelToCsv.convertExcelToCSV(
        excelPath, csvPath
    );

    if(!translateResult){
      Logger.error("failed to convert excel to csv! excel path : " + excelPath);
      return false;
    }

    // TODO : implement later

    return false;
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
