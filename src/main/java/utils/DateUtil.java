package utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {

  public static List<LocalDate> getAllDatesOfYear(int year) {
    LocalDate start = LocalDate.of(year, 1, 1);
    LocalDate end   = LocalDate.of(year, 12, 31);

    List<LocalDate> dates = new ArrayList<>();

    LocalDate d = start;
    while (!d.isAfter(end)) {
      dates.add(d);
      d = d.plusDays(1);
    }

    return dates;
  }

  /**
   * startDate는 포함하지만, endDate는 포함하지 않는 [s, e) 반 개구간 LocalDate 리스트를 리턴한다.
   * */
  public static List<LocalDate> getRangeDate(LocalDate start, LocalDate end) {
    return start.datesUntil(end).toList();
  }

  /**
   * startDate와 endDate를 모두 포함하는 [s, e] 폐구간 LocalDate 리스트를 리턴한다.
   * */
  public static List<LocalDate> getRangeDateClosed(LocalDate start, LocalDate end){
    List<LocalDate> resultList = new ArrayList<>(start.datesUntil(end).toList());
    resultList.add(end);
    return resultList;
  }
}