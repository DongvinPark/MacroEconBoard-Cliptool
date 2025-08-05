package utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;

  private enum Level {
    INFO, DEBUG, WARN, ERROR
  }

  public static void info(String msg) {
    log(Level.INFO, msg);
  }

  public static void debug(String msg) {
    log(Level.DEBUG, msg);
  }

  public static void warn(String msg) {
    log(Level.WARN, msg);
  }

  public static void error(String msg) {
    log(Level.ERROR, msg);
  }

  private static void log(Level level, String message) {
    String time = dateFormat.format(new Date());
    String formatted = String.format("[%s] [%s] %s", time, level, message);
    System.out.println(formatted);
  }
}

