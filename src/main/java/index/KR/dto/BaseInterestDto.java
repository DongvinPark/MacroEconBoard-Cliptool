package index.KR.dto;

import java.time.LocalDate;

public class BaseInterestDto {
  public LocalDate time;
  public double value;

  public BaseInterestDto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
