package com.github.rma350.jgraphv.android.coredeps;

import android.util.Log;

import com.github.rma350.jgraphv.core.portable.Log.Logger;

public class AndroidLog implements Logger {

  @Override
  public void d(String tag, String message) {
    Log.d(tag, message);
  }

  @Override
  public void e(String tag, String message) {
    Log.e(tag, message);
  }

  @Override
  public void i(String tag, String message) {
    Log.i(tag, message);
  }

  @Override
  public void v(String tag, String message) {
    Log.v(tag, message);
  }

  @Override
  public void w(String tag, String message) {
    Log.w(tag, message);
  }

}
