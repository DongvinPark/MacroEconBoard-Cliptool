package utils;

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

}
