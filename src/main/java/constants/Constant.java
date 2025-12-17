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
  public static final String KOSPI_JSON_SUFFIX = "-kospi";

  // kosdaq
  public static final int KOSDAU_HEADER_LENGTH = 8;
  public static final String KOSDAQ_JSON_SUFFIX = "-kosdaq";

  // kr bond 3 yaers
  public static final int BOND_HEADER_LENGTH = 2;
  public static final String BOND_3_YAR_EXCEL_TO_CSV_SUFFIX = "-3y-bond";
  public static final String BOND_3_YEAR_JSON_SUFFIX = "-kr-bond-3y";

  // kr bond 10 years
  public static final String BOND_10_YAR_EXCEL_TO_CSV_SUFFIX = "-10y-bond";
  public static final String BOND_10_YEAR_JSON_SUFFIX = "-kr-bond-10y";

  // won dollar exchange rate
  public static final int WON_DOLLAR_EXCHANGE_RATE_HEADER_LENGTH = 2;
  public static final String WON_DOLLAR_EXCHANGE_RATE_EXCEL_TO_CSV_SUFFIX = "-won-dollar";
  public static final String WON_DOLLAR_EXCHANGE_RATE_JSON_SUFFIX = "-won-dollar";

  // Bank Of Korea base interest rate
  public static final int BASE_INTEREST_RATE_RECORD_CSV_HEADER_LENGTH = 3;
  public static final int BR_BASE_INTEREST_RATE_FIRST_RECORD_YEAR = 1999;
  public static final String BR_BASE_INTEREST_RATE_FIRST_RECORD_DATE = "1999-05-06";
  public static final String BANK_OF_KOREA_OPENAPI_KEY = "4T1SINIS1H67ISYV0RBI"; // TODO : 2027년 12월 11일 만기다.
  public static final String KR_BASE_INTEREST_RATE_JSON_SUFFIX = "-kr-base-interest-rate";

  // Original BTC Bitcoin
  public static final int BTC_CSV_HEADER_LENGTH = 8;
  public static final String BTC_JSON_SUFFIX = "-btc-original";
}
