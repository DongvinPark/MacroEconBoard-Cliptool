package constants;

public class Constant {

  public static final String EMPTY_STR = "";

  public static final String CSV_EXTENSION = ".csv";
  public static final String JSON_EXTENSION = ".json";
  public static final String XLS_EXTENSION = ".xls";
  public static final String XLSX_EXTENSION = ".xlsx";
  public static final String TXT_EXTENSION = ".txt";

  // runtime exception & error codes
  public static final String PATH_NOT_EXISTS = "directory not exist!";

  // kospi
  public static final int KOSPI_HEADER_LENGTH = 8;
  public static final String KOSPI_SUFFIX = "-kospi";

  // kosdaq
  public static final int KOSDAU_HEADER_LENGTH = 8;
  public static final String KOSDAQ_SUFFIX = "-kosdaq";

  // kr bond 3 yaers
  public static final int BOND_HEADER_LENGTH = 2;
  public static final String BOND_3_YAR_EXCEL_TO_CSV_SUFFIX = "-3y-bond";
  public static final String BOND_3_YEAR_JSON_SUFFIX = "-kr-bond-3y";

  // kr bond 10 years
  public static final String BOND_10_YAR_EXCEL_TO_CSV_SUFFIX = "-10y-bond";
  public static final String BOND_10_YEAR_JSON_SUFFIX = "-kr-bond-10y";

  public static final int WON_DOLLAR_EXCHANGE_RATE_HEADER_LENGTH = 2;
  public static final String WON_DOLLAR_EXCHANGE_RATE_EXCEL_TO_CSV_SUFFIX = "-won-dollar";
  public static final String WON_DOLLAR_EXCHANGE_RATE_JSON_SUFFIX = "-won-dollar";
}
