import index.KR.exchange_rate.WonDollar;
import index.KR.interest.BaseInterestRate;
import index.KR.interest.BondRate10Year;
import index.KR.interest.BondRate3Year;
import index.KR.raw_materials.KrxGold1KG;
import index.US.interest.FederalFundsRate;
import index.US.interest.UsBondRate10Year;
import index.US.interest.UsBondRate3Year;
import index.US.nasdaq_compo.Nasdaq;
import index.US.origin_bitcoin.BitcoinOriginal;
import index.US.raw_materials.ComexGoldFuture;
import index.US.snp500.SnP500;

public class Main {
  public static void main(String[] args){

    // .xls 파일과 .xlsx 파일 같은 엑셀파일들은 .csv로 변환 후 처리한다.
    /*
    Kospi kospi = new Kospi();
    kospi.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\kospi-to-20250624.csv",
        "C:\\dev\\Macro-Economy-Board"
    );*/

    /*
    Kosdaq kosdaq = new Kosdaq();
    kosdaq.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\kosdaq-to-20250624.csv",
        "C:\\dev\\Macro-Economy-Board"
    );*/

    /*
    BondRate3Year bondRate3Year = new BondRate3Year();
    bondRate3Year.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\국고채3년이자율2022-20250623.xls",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );*/

    /*
    BondRate10Year bondRate10Year = new BondRate10Year();
    bondRate10Year.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\국고채10년이자율2020-20250623.xls",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );*/

    /*
    WonDollar wonDollar = new WonDollar();
    wonDollar.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\원_달러 환율 및 원화 명목실효환율 지수.xlsx",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );*/

    /*
    BaseInterestRate baseInterestRate = new BaseInterestRate();
    baseInterestRate.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\data-batch-updater\\meboard-updater\\kr-base-interest-rate-record.csv",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );*/

    /*
    BitcoinOriginal bitcoinOriginal = new BitcoinOriginal();
    bitcoinOriginal.convertToJson(
            "C:\\dev\\Macro-Economy-Board\\raw-data\\btc_daily_data.csv",
            "C:\\dev\\Macro-Economy-Board\\raw-data"
    );*/

    /*
    SnP500 snP500 = new SnP500();
    snP500.convertToJson(
            "C:\\dev\\Macro-Economy-Board\\raw-data\\snp500_daily_data.csv",
            "C:\\dev\\Macro-Economy-Board\\raw-data"
    );*/

    /*
    Nasdaq nasdaq = new Nasdaq();
    nasdaq.convertToJson(
            "C:\\dev\\Macro-Economy-Board\\raw-data\\nasdaq_daily_data.csv",
            "C:\\dev\\Macro-Economy-Board\\raw-data"
    );*/

    /*
    FederalFundsRate federalFundsRate = new FederalFundsRate();
    federalFundsRate.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\us_fed_rate.csv",
        "C:\\dev\\Macro-Economy-Board\\raw-data\\"
    );*/

    /*
    UsBondRate3Year usBondRate3Year = new UsBondRate3Year();
    usBondRate3Year.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\us_treasury_3y.csv",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );*/

    /*
    UsBondRate10Year usBondRate10Year = new UsBondRate10Year();
    usBondRate10Year.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\us_treasury_10y.csv",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );*/

    /*
    ComexGoldFuture comexGoldFuture = new ComexGoldFuture();
    comexGoldFuture.convertToJson(
        "C:\\dev\\Macro-Economy-Board\\raw-data\\gold_future_comex.csv",
        "C:\\dev\\Macro-Economy-Board\\raw-data"
    );*/

    /*
    KrxGold1KG krxGold1KG = new KrxGold1KG();
    krxGold1KG.convertToJson(
        "/Users/dongvin99/Documents/Macro-Economy-Board/raw-data/krx-gold-2024-2025.csv",
        "/Users/dongvin99/Documents/Macro-Economy-Board/raw-data"
    );*/

  }// end of main
}// Main class