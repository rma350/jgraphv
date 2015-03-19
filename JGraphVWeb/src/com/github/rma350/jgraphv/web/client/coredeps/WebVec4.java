package com.github.rma350.jgraphv.web.client.coredeps;

import com.github.rma350.jgraphv.core.portable.Vec4;
import com.google.gwt.core.client.JavaScriptObject;

public final class WebVec4 extends JavaScriptObject implements Vec4{

  protected WebVec4(){}
  
  @Override
  public final float x() {
    return nX(this);
  }

  private static native float nX(WebVec4 vec)/*-{
    return vec[0];
  }-*/;

  @Override
  public final float y() {
    return nY(this);
  }

  private static native float nY(WebVec4 vec)/*-{
    return vec[1];
  }-*/;

  @Override
  public final float z() {
    return nZ(this);
  }

  private static native float nZ(WebVec4 vec)/*-{
    return vec[2];
  }-*/;

  @Override
  public final float w() {
    return nW(this);
  }

  private static native float nW(WebVec4 vec)/*-{
    return vec[3];
  }-*/;
  
}
