package index.US.dto;

import java.time.LocalDate;

public class GoldFutureDto {
  public LocalDate time;
  public double value;

  public GoldFutureDto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
