package index.MARKET_CONDITION.dto;

import java.time.LocalDate;

public class HighYieldSpreadDto {
  public LocalDate time;
  public double value;

  public HighYieldSpreadDto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
