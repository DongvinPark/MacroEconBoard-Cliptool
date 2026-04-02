package script;

import index.KR.exchange_rate.WonDollar;
import index.KR.interest.BaseInterestRate;
import index.KR.interest.BondRate10Year;
import index.KR.interest.BondRate3Year;
import index.KR.raw_materials.KrxGold1KG;
import index.KR.stock.Kosdaq;
import index.KR.stock.Kospi;
import index.MARKET_CONDITION.liquid.dollar_index.DollarIndex;
import index.MARKET_CONDITION.liquid.m2_money.M2MoneySupply;
import index.UK.raw_materials.BrentCrudeOilFuture;
import index.US.interest.FederalFundsRate;
import index.US.interest.UsBondRate10Year;
import index.US.interest.UsBondRate3Year;
import index.US.nasdaq_compo.Nasdaq;
import index.US.origin_bitcoin.BitcoinOriginal;
import index.US.raw_materials.ComexGoldFuture;
import index.US.snp500.SnP500;
import utils.Logger;

import java.io.File;

import static constants.Constant.*;

public class JsonUpdator {
  // .xls 파일과 .xlsx 파일 같은 엑셀파일들은 .csv로 변환 후 처리한다.

  private static final String KR_THIS_YEAR_ROOT_PATH
      = "C:\\dev\\Macro-Economy-Board\\macro-econ-board-origin-storage\\this-year\\kr";
  private static final String US_THIS_YEAR_ROOT_PATH
      = "C:\\dev\\Macro-Economy-Board\\macro-econ-board-origin-storage\\this-year\\us";
  private static final String UK_THIS_YEAR_ROOT_PATH
      = "C:\\dev\\Macro-Economy-Board\\macro-econ-board-origin-storage\\this-year\\uk";
  private static final String MARKET_CONDITION_THIS_YEAR_ROOT_PATH
      = "C:\\dev\\Macro-Economy-Board\\macro-econ-board-origin-storage\\this-year\\market";

  public boolean buildThisYearJsonFiles(){
    try {
      Kospi kospi = new Kospi();
      boolean kospiResult = kospi.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\kospi_index_this_year.csv",
          KR_THIS_YEAR_ROOT_PATH + File.separator + KOSPI
      );
      Logger.warn("KOSPI result : " + kospiResult);

      Kosdaq kosdaq = new Kosdaq();
      boolean kosdaqResult = kosdaq.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\kosdaq_index_this_year.csv",
          KR_THIS_YEAR_ROOT_PATH + File.separator + KOSDAQ
      );
      Logger.warn("KOSDAQ result : " + kosdaqResult);

      BaseInterestRate baseInterestRate = new BaseInterestRate();
      boolean krBaseRateResult = baseInterestRate.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\data-batch-updater\\meboard-updater\\kr-base-interest-rate-record.csv",
          KR_THIS_YEAR_ROOT_PATH + File.separator + KR_BASE_INTEREST_RATE
      );
      Logger.warn("KR Base Rate result : " + krBaseRateResult);

      BondRate3Year bondRate3Year = new BondRate3Year();
      boolean krBond3yResult = bondRate3Year.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\최종호가 수익률.xls",
          KR_THIS_YEAR_ROOT_PATH + File.separator + KR_BOND_3Y
      );
      Logger.warn("KR Bond 3Y result : " + krBond3yResult);

      BondRate10Year bondRate10Year = new BondRate10Year();
      boolean krBond10yResult = bondRate10Year.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\최종호가 수익률 (1).xls",
          KR_THIS_YEAR_ROOT_PATH + File.separator + KR_BOND_10Y
      );
      Logger.warn("KR Bond 10Y result : " + krBond10yResult);

      WonDollar wonDollar = new WonDollar();
      boolean wonDollarResult = wonDollar.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\원_달러 환율 및 원화 명목실효환율 지수.xlsx",
          KR_THIS_YEAR_ROOT_PATH + File.separator + WON_DOLLAR_EXCHANGE_RATE
      );
      Logger.warn("KR Won/Dollar Exchange Rate result : " + wonDollarResult);

      KrxGold1KG krxGold1KG = new KrxGold1KG();
      boolean krGold1KgResult = krxGold1KG.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\krx-raw.csv",
          KR_THIS_YEAR_ROOT_PATH + File.separator + KRX_GOLD_1KG_SPOT
      );
      Logger.warn("KRX Gold 1KG Spot result : " + krGold1KgResult);

      SnP500 snP500 = new SnP500();
      boolean snP500Result = snP500.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\snp500_daily_data_this_year.csv",
          US_THIS_YEAR_ROOT_PATH + File.separator + SNP_500
      );
      Logger.warn("S&P 500 result : " + snP500Result);

      Nasdaq nasdaq = new Nasdaq();
      boolean nasdaqResult = nasdaq.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\nasdaq_daily_data_this_year.csv",
          US_THIS_YEAR_ROOT_PATH + File.separator + NASDAQ
      );
      Logger.warn("NASDAQ Composite result : " + nasdaqResult);

      FederalFundsRate federalFundsRate = new FederalFundsRate();
      boolean usBaseRateResult = federalFundsRate.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\us_fed_rate_this_year.csv",
          US_THIS_YEAR_ROOT_PATH + File.separator + US_FEDERAL_FUNDS_RATE
      );
      Logger.warn("US Base Rate result : " + usBaseRateResult);

      UsBondRate3Year usBondRate3Year = new UsBondRate3Year();
      boolean usBond3yResult = usBondRate3Year.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\us_treasury_3y_this_year.csv",
          US_THIS_YEAR_ROOT_PATH + File.separator + US_BOND_3Y
      );
      Logger.warn("US Bond 3Y result : " + usBond3yResult);

      UsBondRate10Year usBondRate10Year = new UsBondRate10Year();
      boolean usBond10yResult = usBondRate10Year.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\us_treasury_10y_this_year.csv",
          US_THIS_YEAR_ROOT_PATH + File.separator + US_BOND_10Y
      );
      Logger.warn("US Bond 10Y result : " + usBond10yResult);

      BitcoinOriginal bitcoinOriginal = new BitcoinOriginal();
      boolean btcResult = bitcoinOriginal.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\btc_daily_data_this_year.csv",
          US_THIS_YEAR_ROOT_PATH + File.separator + BTC_ORIGINAL
      );
      Logger.warn("BTC Bitcoin result : " + btcResult);

      ComexGoldFuture comexGoldFuture = new ComexGoldFuture();
      boolean comexGoldResult = comexGoldFuture.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\gold_future_comex_this_year.csv",
          US_THIS_YEAR_ROOT_PATH + File.separator + COMEX_GOLD_FUTURE
      );
      Logger.warn("COMEX Gold Future result : " + comexGoldResult);

      BrentCrudeOilFuture brentCrudeOilFuture = new BrentCrudeOilFuture();
      boolean brentCrudeOilFutureResult = brentCrudeOilFuture.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\brent_oil_future_this_year.csv",
          UK_THIS_YEAR_ROOT_PATH + File.separator + BRENT_OIL_FUTURE
      );
      Logger.warn("Brent Crude Oil Future result : " + brentCrudeOilFutureResult);

      M2MoneySupply m2MoneySupply = new M2MoneySupply();
      boolean m2MoneySupplyResult = m2MoneySupply.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\m2_money_this_year.csv",
          MARKET_CONDITION_THIS_YEAR_ROOT_PATH + File.separator + US_M2_MONEY_SUPPLY
      );
      Logger.warn("US M2 Money Supply result : " + m2MoneySupplyResult);

      DollarIndex dollarIndex = new DollarIndex();
      boolean dollarIndexResult = dollarIndex.convertToJson(
          "C:\\dev\\Macro-Economy-Board\\raw-data\\dollar_index_this_year.csv",
          MARKET_CONDITION_THIS_YEAR_ROOT_PATH + File.separator + US_DOLLAR_INDEX
      );
      Logger.warn("US Dollar Index result : " + dollarIndexResult);

      return true;
    } catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }

}
