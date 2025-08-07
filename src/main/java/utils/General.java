package utils;

import static constants.Constant.EMPTY_STR;

public class General {

  public static double getDoubleVal(String str) {
    try {
      return Double.parseDouble(str);
    } catch (Exception e) {
      return 0.0;
    }
  }

  public static long getLongVal(String str) {
    try {
      return Long.parseLong(str);
    } catch (Exception e) {
      return 0L;
    }
  }

  public static String removeQuotationMarks(String quoted){
    if(quoted.equals(EMPTY_STR)) return EMPTY_STR;
    if (quoted.startsWith("\"") && quoted.endsWith("\"")) {
      quoted = quoted.substring(1, quoted.length() - 1);
    }
    return quoted;
  }

}
