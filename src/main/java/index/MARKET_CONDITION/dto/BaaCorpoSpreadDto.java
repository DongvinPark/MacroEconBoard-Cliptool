package index.MARKET_CONDITION.dto;

import java.time.LocalDate;

public class BaaCorpoSpreadDto {
  public LocalDate time;
  public double value;

  public BaaCorpoSpreadDto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
