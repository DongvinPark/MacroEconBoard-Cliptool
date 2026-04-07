package index.MARKET_CONDITION.dto;

import java.time.LocalDate;

public class VixDto {
  public LocalDate time;
  public double value;

  public VixDto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
