import script.JsonUpdator;
import script.PythonScriptRunner;
import utils.Logger;

import java.util.List;

public class Main {
  public static void main(String[] args){

    PythonScriptRunner scriptRunner = new PythonScriptRunner(
        "C:\\dev\\Macro-Economy-Board\\raw-data",
        List.of(
            "01-kospi-test.py",
            "02-kosdaq-test.py",
            "03-btc-test.py",
            "04-snp500-test.py",
            "05-nasdaq-test.py",
            "06-fred-interest-rates.py",
            "07-comex-gold-future.py",
            "08-brent-oil-future.py",
            "09-m2.py",
            "10-dollar-index.py",
            "11-us-bank-all-credit.py"
        )
    );

    boolean dataFetchingResult
        = scriptRunner.runPythonDataFetchingScrips(2026);//TODO : 여기서 연도를 수동으로 바꿔준다.
    Logger.warn("API Fetching result : " + dataFetchingResult);

    JsonUpdator jsonUpdator = new JsonUpdator();
    boolean updateResult = jsonUpdator.buildThisYearJsonFiles();
    Logger.warn("JSON File Build result : " + updateResult);

  }// end of main
}// Main class