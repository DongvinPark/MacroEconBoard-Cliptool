package index.KR.interest;

import index.Index;

public class BaseInterestRate implements Index {

  @Override
  public boolean convertToJson(String notInUse, String jsonDirPath) {
    try {
      // 첫 번째 인자는 사용하지 않는다.

      return true;
    } catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }

}
