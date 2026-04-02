package index.MARKET_CONDITION.dto;

import java.time.LocalDate;

public class M2Dto {
  public LocalDate time;
  public double value;

  public M2Dto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
