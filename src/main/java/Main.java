import index.KR.exchange_rate.WonDollar;
import index.KR.interest.BaseInterestRate;
import index.KR.interest.BondRate10Year;
import index.KR.interest.BondRate3Year;
import index.KR.raw_materials.KrxGold1KG;
import index.KR.stock.Kosdaq;
import index.KR.stock.Kospi;
import index.US.interest.FederalFundsRate;
import index.US.interest.UsBondRate10Year;
import index.US.interest.UsBondRate3Year;
import index.US.nasdaq_compo.Nasdaq;
import index.US.origin_bitcoin.BitcoinOriginal;
import index.US.raw_materials.ComexGoldFuture;
import index.US.snp500.SnP500;
import script_for_raw_data_api.PythonScriptRunner;
import utils.Logger;

import java.util.List;

// .xls 파일과 .xlsx 파일 같은 엑셀파일들은 .csv로 변환 후 처리한다.
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
            "07-comex-gold-future.py"
        )
    );
    boolean dataFetchingResult = scriptRunner.runPythonDataFetchingScrips();
    Logger.warn("API Fetching result : " + dataFetchingResult);

    Kospi kospi = new Kospi();
    boolean kospiResult = kospi.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\kospi-to-20250624.csv",
        "C:\\dev\\Macro-Economy-Board"
    );
    Logger.warn("KOSPI result : " + kospiResult);

    Kosdaq kosdaq = new Kosdaq();
    boolean kosdaqResult = kosdaq.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\kosdaq-to-20250624.csv",
        "C:\\dev\\Macro-Economy-Board"
    );
    Logger.warn("KOSDAQ result : " + kosdaqResult);

    BaseInterestRate baseInterestRate = new BaseInterestRate();
    boolean krBaseRateResult = baseInterestRate.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\data-batch-updater\\meboard-updater\\kr-base-interest-rate-record.csv",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );
    Logger.warn("KR Base Rate result : " + krBaseRateResult);

    BondRate3Year bondRate3Year = new BondRate3Year();
    boolean krBond3yResult = bondRate3Year.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\국고채3년이자율2022-20250623.xls",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );
    Logger.warn("KR Bond 3Y result : " + krBond3yResult);

    BondRate10Year bondRate10Year = new BondRate10Year();
    boolean krBond10yResult = bondRate10Year.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\국고채10년이자율2020-20250623.xls",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );
    Logger.warn("KR Bond 10Y result : " + krBond10yResult);

    WonDollar wonDollar = new WonDollar();
    boolean wonDollarResult = wonDollar.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\원_달러 환율 및 원화 명목실효환율 지수.xlsx",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );
    Logger.warn("KR Won/Dollar Exchange Rate result : " + wonDollarResult);

    KrxGold1KG krxGold1KG = new KrxGold1KG();
    boolean krGold1KgResult = krxGold1KG.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\krx-gold-2024-2025.csv",
        "C:\\dev\\Macro-Economy-Board\\raw-data\\"
    );
    Logger.warn("KRX Gold 1KG Spot result : " + krGold1KgResult);

    SnP500 snP500 = new SnP500();
    boolean snP500Result = snP500.convertToJson(
            "C:\\dev\\Macro-Economy-Board\\raw-data\\snp500_daily_data.csv",
            "C:\\dev\\Macro-Economy-Board\\raw-data"
    );
    Logger.warn("S&P 500 result : " + snP500Result);

    Nasdaq nasdaq = new Nasdaq();
    boolean nasdaqResult = nasdaq.convertToJson(
            "C:\\dev\\Macro-Economy-Board\\raw-data\\nasdaq_daily_data.csv",
            "C:\\dev\\Macro-Economy-Board\\raw-data"
    );
    Logger.warn("NASDAQ Composite result : " + nasdaqResult);

    FederalFundsRate federalFundsRate = new FederalFundsRate();
    boolean usBaseRateResult = federalFundsRate.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\us_fed_rate.csv",
        "C:\\dev\\Macro-Economy-Board\\raw-data\\"
    );
    Logger.warn("US Base Rate result : " + usBaseRateResult);

    UsBondRate3Year usBondRate3Year = new UsBondRate3Year();
    boolean usBond3yResult = usBondRate3Year.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\us_treasury_3y.csv",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );
    Logger.warn("US Bond 3Y result : " + usBond3yResult);

    UsBondRate10Year usBondRate10Year = new UsBondRate10Year();
    boolean usBond10yResult = usBondRate10Year.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\us_treasury_10y.csv",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );
    Logger.warn("US Bond 10Y result : " + usBond10yResult);

    BitcoinOriginal bitcoinOriginal = new BitcoinOriginal();
    boolean btcResult = bitcoinOriginal.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\btc_daily_data.csv",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );
    Logger.warn("BTC Bitcoin result : " + btcResult);

    ComexGoldFuture comexGoldFuture = new ComexGoldFuture();
    boolean comexGoldResult = comexGoldFuture.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\gold_future_comex.csv",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );
    Logger.warn("COMEX Gold Future result : " + comexGoldResult);

  }// end of main
}// Main class