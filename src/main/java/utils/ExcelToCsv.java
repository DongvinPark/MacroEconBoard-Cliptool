package utils;

import static constants.Constant.EMPTY_STR;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelToCsv {

  public static boolean convertExcelToCSV(String excelPath, String csvPath) {
    try (
        InputStream inp = new FileInputStream(excelPath);
        Workbook workbook = WorkbookFactory.create(inp);
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvPath))
    ) {

      Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트
      for (Row row : sheet) {
        StringBuilder rowString = new StringBuilder();
        for (int i = 0; i < row.getLastCellNum(); i++) {
          Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

          String cellValue = getCellValueAsString(cell);
          rowString.append("\"").append(
              cellValue.replace("\"", "\"\"")
          ).append("\"");

          if (i < row.getLastCellNum() - 1) {
            rowString.append(",");
          }
        }// inner for

        writer.write(rowString.toString());
        writer.newLine();
      }// outer for

      return true;
    } catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }

  private static String getCellValueAsString(Cell cell) {
    return switch (cell.getCellType()) {
      case STRING -> cell.getStringCellValue();
      case NUMERIC ->
          DateUtil.isCellDateFormatted(cell) ? cell.getDateCellValue().toString()
              : Double.toString(cell.getNumericCellValue());
      case BOOLEAN -> Boolean.toString(cell.getBooleanCellValue());
      case FORMULA -> cell.getCellFormula();
      case BLANK -> EMPTY_STR;
      default -> EMPTY_STR;
    };
  }

}
