package script;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import static constants.Constant.KOSDAQ_JSON_SUFFIX;
import static constants.Constant.KOSPI_JSON_SUFFIX;

public class PythonScriptRunner {

  private final String scriptRootDir;
  private final List<String> scriptFileNameList;

  public PythonScriptRunner(
      String scriptRootDir,
      List<String> fileNameList
  ) {
    this.scriptRootDir = scriptRootDir;
    this.scriptFileNameList = fileNameList;
  }

  public boolean runPythonDataFetchingScrips(int year){
    try {
      String pythonVersion = "python";
      for(String scriptName : scriptFileNameList){

        String scriptFileDir = scriptRootDir + File.separator + scriptName;
        ProcessBuilder pb = new ProcessBuilder(
            pythonVersion,
            scriptFileDir,
            String.valueOf(year)
        );

        pb.directory(new File(this.scriptRootDir));

        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (BufferedReader reader =
                 new BufferedReader(new InputStreamReader(process.getInputStream()))) {
          String line;
          while ((line = reader.readLine()) != null) {
            System.out.println(line);
          }
        }

        int exitCode = process.waitFor();
        System.out.println("Exit code for " + scriptName + " : " + exitCode);
      }//for

      return true;
    } catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }

}
