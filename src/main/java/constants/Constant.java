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
  public static final String KOSPI = "kospi";
  public static final int KOSPI_HEADER_LENGTH = 8;
  public static final String KOSPI_JSON_SUFFIX = "-kospi";

  // kosdaq
  public static final String KOSDAQ = "kosdaq";
  public static final int KOSDAU_HEADER_LENGTH = 8;
  public static final String KOSDAQ_JSON_SUFFIX = "-kosdaq";

  // kr bond 3 yaers
  public static final String KR_BOND_3Y = "kr-bond-3y";
  public static final int BOND_HEADER_LENGTH = 2;
  public static final String BOND_3_YAR_EXCEL_TO_CSV_SUFFIX = "-3y-bond";
  public static final String BOND_3_YEAR_JSON_SUFFIX = "-kr-bond-3y";

  // kr bond 10 years
  public static final String KR_BOND_10Y = "kr-bond-10y";
  public static final String BOND_10_YAR_EXCEL_TO_CSV_SUFFIX = "-10y-bond";
  public static final String BOND_10_YEAR_JSON_SUFFIX = "-kr-bond-10y";

  // won dollar exchange rate
  public static final String WON_DOLLAR_EXCHANGE_RATE = "won-dollar";
  public static final int WON_DOLLAR_EXCHANGE_RATE_HEADER_LENGTH = 2;
  public static final String WON_DOLLAR_EXCHANGE_RATE_EXCEL_TO_CSV_SUFFIX = "-won-dollar";
  public static final String WON_DOLLAR_EXCHANGE_RATE_JSON_SUFFIX = "-won-dollar";

  // KRX Gold 99.99 1KG
  public static final String KRX_GOLD_1KG_SPOT = "krx-gold-spot-1kg";
  public static final int KRX_GOLD_1KG_CSV_HEADER_LENGTH = 9;
  public static final String KRX_GOLD_1KG_JSON_SUFFIX = "-krx-gold-spot-1kg";

  // Bank Of Korea base interest rate
  public static final String KR_BASE_INTEREST_RATE = "kr-base-interest-rate";
  public static final int BASE_INTEREST_RATE_RECORD_CSV_HEADER_LENGTH = 3;
  public static final int BR_BASE_INTEREST_RATE_FIRST_RECORD_YEAR = 1999;
  public static final String BR_BASE_INTEREST_RATE_FIRST_RECORD_DATE = "1999-05-06";
  public static final String BANK_OF_KOREA_OPENAPI_KEY = "4T1SINIS1H67ISYV0RBI"; // TODO : 2027년 12월 11일 만기다.
  public static final String KR_BASE_INTEREST_RATE_JSON_SUFFIX = "-kr-base-interest-rate";

  // Original BTC Bitcoin
  public static final String BTC_ORIGINAL = "btc-original";
  public static final int BTC_CSV_HEADER_LENGTH = 8;
  public static final String BTC_JSON_SUFFIX = "-btc-original";

  // S&P 500
  public static final String SNP_500 = "snp-500";
  public static final int SNP500_CSV_HEADER_LENGTH = 8;
  public static final String SNP500_JSON_SUFFIX = "-snp-500";

  // NASDAQ Composite
  public static final String NASDAQ = "nasdaq";
  public static final int NASDAQ_CSV_HEADER_LENGTH = 8;
  public static final String NASDAQ_JSON_SUFFIX = "-nasdaq";

  // US Base Interest Rate
  public static final String US_FEDERAL_FUNDS_RATE = "us-base-interest-rate";
  public static final int US_FEDERAL_FUNDS_RATE_CSV_HEADER_LENGTH = 2;
  public static final String US_FEDERAL_FUNDS_RATE_JSON_SUFFIX = "-us-base-interest-rate";

  // US 3 year Treasury Rate
  public static final String US_BOND_3Y = "us-bond-3y";
  public static final int US_BOND_3Y_CSV_HEADER_LENGTH = 2;
  public static final String US_BOND_3Y_RATE_JSON_SUFFIX = "-us-bond-3y";

  // US 10 year Treasury Rate
  public static final String US_BOND_10Y = "us-bond-10y";
  public static final int US_BOND_10Y_CSV_HEADER_LENGTH = 2;
  public static final String US_BOND_10Y_RATE_JSON_SUFFIX = "-us-bond-10y";

  // COMEX GOLD FUTURE
  public static final String COMEX_GOLD_FUTURE = "gold-price-comex";
  public static final int GOLD_FUTURE_CSV_HEADER_LENGTH = 2;
  public static final String GOLD_FUTURE_JSON_SUFFIX = "-gold-price-comex";

  // UK Brent Crude Oil Future
  public static final String BRENT_OIL_FUTURE = "brent-crude-oil-futures";
  public static final int BRENT_OIL_FUTURE_CSV_HEADER_LENGTH = 2;
  public static final String BRENT_OIL_FUTURE_JSON_SUFFIX = "-brent-crude-oil-futures";

  // US M2 Money Supply
  public static final String US_M2_MONEY_SUPPLY = "m2-liquidity";
  public static final int US_M2_MONEY_SUPPLY_CSV_HEADER_LENGTH = 2;
  public static final String US_M2_MONEY_SUPPLY_JSON_SUFFIX = "-m2-liquidity";

  // US Dollar Index
  public static final String US_DOLLAR_INDEX = "dollar-index";
  public static final int US_DOLLAR_INDEX_CSV_HEADER_LENGTH = 2;
  public static final String US_DOLLAR_INDEX_JSON_SUFFIX = "-dollar-index";

  // US Commercial Back Total Credit
  public static final String US_BANK_TOTAL_CREDIT = "us-all-bank-credit";
  public static final int US_BANK_TOTAL_CREDIT_CSV_HEADER_LENGTH = 2;
  public static final String US_BANK_TOTAL_CREDIT_JSON_SUFFIX = "-us-all-bank-credit";

  // US 10Y  & 2Y bond yield difference
  public static final String US_10Y_MINUS_2Y_DIFF = "10y-2y-diff";
  public static final int US_10Y_MINUS_2Y_DIFF_CSV_HEADER_LENGTH = 2;
  public static final String US_10Y_MINUS_2Y_DIFF_JSON_SUFFIX = "-10y-2y-diff";

  // US High Yield Spread
  public static final String US_HIGH_YIELD_SPREAD = "high-yield-spread";
  public static final int US_HIGH_YIELD_SPREAD_CSV_HEADER_LENGTH = 2;
  public static final String US_HIGH_YIELD_SPREAD_JSON_SUFFIX = "-high-yield-spread";

  // US BAA-Grade Corporate Yield Spread
  public static final String US_BAA_CORPORATE_SPREAD = "corporate-bond-spread";
  public static final int US_BAA_CORPORATE_SPREAD_CSV_HEADER_LENGTH = 2;
  public static final String US_BAA_CORPORATE_SPREAD_JSON_SUFFIX = "-corporate-bond-spread";

  // US VIX Index
  public static final String US_VIX = "vix";
  public static final int US_VIX_CSV_HEADER_LENGTH = 2;
  public static final String US_VIX_JSON_SUFFIX = "-vix";
}






























