package com.github.rma350.jgraphv.core.portable;

import java.util.logging.Level;

public class Log {

  public static volatile Logger out = new JavaLogger();

  public static final int ERROR = 0;
  public static final int WARNING = 1;
  public static final int INFO = 2;
  public static final int DEBUG = 3;
  public static final int VERBOSE = 4;

  public static interface Logger {
    
    public void v(String tag, String message);

    public void d(String tag, String message);

    public void i(String tag, String message);

    public void w(String tag, String message);

    public void e(String tag, String message);

  }

  /*
  public static class SystemLogger implements Logger {

    private volatile int level = DEBUG;
    
    public void v(String tag, String message) {
      if (level >= VERBOSE) {
        System.out.println(tag + ":" + message);
      }
    }

    public void d(String tag, String message) {
      if (level >= DEBUG) {
        System.out.println(tag + ":" + message);
      }
    }

    public void i(String tag, String message) {
      if (level >= INFO) {
        System.out.println(tag + ":" + message);
      }
    }

    public void w(String tag, String message) {
      if (level >= WARNING) {
        System.out.println(tag + ":" + message);
      }
    }

    public void e(String tag, String message) {
      if (level >= ERROR) {
        System.out.println(tag + ":" + message);
      }
    }

    public void setLogLevel(int level) {
      this.level = level;
    }
  }
  */
  
  public static class JavaLogger implements Logger {
    
    @Override
    public void v(String tag, String message) {
      java.util.logging.Logger.getLogger(tag).log(Level.FINE, message);
    }

    @Override
    public void d(String tag, String message) {
      java.util.logging.Logger.getLogger(tag).log(Level.INFO, message);
    }

    @Override
    public void i(String tag, String message) {
      java.util.logging.Logger.getLogger(tag).log(Level.INFO, message);
    }

    @Override
    public void w(String tag, String message) {
      java.util.logging.Logger.getLogger(tag).log(Level.WARNING, message);
    }

    @Override
    public void e(String tag, String message) {
      java.util.logging.Logger.getLogger(tag).log(Level.SEVERE, message);
    }
    
  }

}
