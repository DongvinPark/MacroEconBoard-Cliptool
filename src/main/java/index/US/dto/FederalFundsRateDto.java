package index.US.dto;

import java.time.LocalDate;

public class FederalFundsRateDto {
  public LocalDate time;
  public double value;

  public FederalFundsRateDto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
