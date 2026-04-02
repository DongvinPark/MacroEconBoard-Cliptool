package index.MARKET_CONDITION.dto;

import java.time.LocalDate;

public class DollarIndexDto {
  public LocalDate time;
  public double value;

  public DollarIndexDto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
