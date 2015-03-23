package com.github.rma350.jgraphv.web.client.coredeps;

import com.github.rma350.jgraphv.core.portable.Vec2;
import com.google.gwt.core.client.JavaScriptObject;

public class WebVec2 extends JavaScriptObject implements Vec2{
  
  protected WebVec2() {}
  
  @Override
  public final float x() {
    return nX(this);
  }

  private static native float nX(WebVec2 vec)/*-{
    return vec[0];
  }-*/;

  @Override
  public final float y() {
    return nY(this);
  }

  private static native float nY(WebVec2 vec)/*-{
    return vec[1];
  }-*/;

  @Override
  public final void setX(float x) {
    nSetX(this,x);
  }
  
  private static native void nSetX(WebVec2 vec, float x)/*-{
		vec[0] = x;
  }-*/;

  @Override
  public final void setY(float y) {
    nSetY(this,y);
  }
  
  private static native void nSetY(WebVec2 vec, float y)/*-{
		vec[1] = y;
  }-*/;

  @Override
  public void set(float x, float y) {
    nSet(this, x, y);
  }

  private static native void nSet(WebVec2 vec, float x, float y)/*-{
		vec[0] = x;
		vec[1] = y;
  }-*/;

  
}
