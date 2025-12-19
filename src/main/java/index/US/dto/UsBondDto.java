package index.US.dto;

import java.time.LocalDate;

public class UsBondDto {
  public LocalDate time;
  public double value;

  public UsBondDto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
