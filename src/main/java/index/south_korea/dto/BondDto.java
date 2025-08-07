package index.south_korea.dto;

import java.time.LocalDate;

public class BondDto {
  public LocalDate time;
  public double value;

  public BondDto(LocalDate time, double value) {
    this.time = time;
    this.value = value;
  }
}
