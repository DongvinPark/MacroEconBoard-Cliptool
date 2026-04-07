package index.MARKET_CONDITION.dto;

import java.time.LocalDate;

public class TenYearTwoYearDiffDto {
  public LocalDate time;
  public double value;

  public TenYearTwoYearDiffDto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
