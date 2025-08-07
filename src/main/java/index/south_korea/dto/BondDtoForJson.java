package index.south_korea.dto;

import java.time.LocalDate;

public class BondDtoForJson {
  public String time;
  public double value;

  public BondDtoForJson(String time, double value) {
    this.time = time;
    this.value = value;
  }
}
