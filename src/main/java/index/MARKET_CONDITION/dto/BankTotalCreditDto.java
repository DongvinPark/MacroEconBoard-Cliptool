package index.MARKET_CONDITION.dto;

import java.time.LocalDate;

public class BankTotalCreditDto {
  public LocalDate time;
  public double value;

  public BankTotalCreditDto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
