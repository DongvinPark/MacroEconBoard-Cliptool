package index.UK.dto;

import java.time.LocalDate;

public class BrentOilFutureDto {
  public LocalDate time;
  public double value;

  public BrentOilFutureDto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
