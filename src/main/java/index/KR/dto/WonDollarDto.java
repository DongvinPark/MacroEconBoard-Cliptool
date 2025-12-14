package index.KR.dto;

import java.time.LocalDate;

public class WonDollarDto {
  public LocalDate time;
  public double value;

  public WonDollarDto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
