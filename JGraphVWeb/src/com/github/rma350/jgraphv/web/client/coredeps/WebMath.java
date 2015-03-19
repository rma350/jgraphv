package com.github.rma350.jgraphv.web.client.coredeps;

import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Mat4;
import com.github.rma350.jgraphv.core.portable.Vec4;
import com.github.rma350.jgraphv.core.portable.impl.JLinMath;

public class WebMath extends JLinMath implements LinMath{
  
  @Override
  public WebMat4 createMat4() {
    return nCreateMat4();
  }
  
  private static native WebMat4 nCreateMat4()/*-{
    return $wnd.mat4.create();
  }-*/;
  
  @Override
  public void setIdentity(Mat4 mat) {
    nSetIdentity((WebMat4)mat);
  }
  
  private static native void nSetIdentity(WebMat4 mat)/*-{
    $wnd.mat4.identity(mat);
  }-*/;
  
  @Override
  public void setOrtho2D(Mat4 mat, float left, float right, float bottom, float top) {
    nSetOrtho2D((WebMat4)mat,left,right,bottom,top);
  }
  
  private static native void nSetOrtho2D(WebMat4 mat, float left, float right, float bottom, float top) /*-{
    $wnd.mat4.ortho(mat, left,right,bottom,top,-1,1);
  }-*/;
  
  @Override
  public void scale(Mat4 mat, float scaleX, float scaleY) {
    nScale((WebMat4)mat,scaleX, scaleY);
  }
  
  private static native void nScale(WebMat4 mat, float scaleX, float scaleY) /*-{
    $wnd.mat4.scale(mat, mat, [scaleX, scaleY, 1]);
  }-*/;
  
  @Override
  public void translate(Mat4 mat, float dX, float dY) {
    nTranslate((WebMat4)mat,dX, dY);
  }
  
  private static native void nTranslate(WebMat4 mat, float dX, float dY) /*-{
    $wnd.mat4.translate(mat, mat, [dX, dY, 0]);
  }-*/;
  
  @Override
  public WebMat4 invert(Mat4 mat) {
    return nInvert((WebMat4)mat);
  }
  
  private static native WebMat4 nInvert(WebMat4 mat) /*-{
    var ans = $wnd.mat4.create();
    $wnd.mat4.invert(ans,mat);
    return ans;
  }-*/;
  
  @Override
  public WebVec4 mult(Mat4 mat, Vec4 vec) {
    return nMult((WebMat4)mat, (WebVec4)vec);
  }
  
  private static native WebVec4 nMult(WebMat4 mat, WebVec4 vec) /*-{
    var ans = $wnd.vec4.create();
    $wnd.vec4.transformMat4(ans,vec,mat);
    return ans;
  }-*/;
  
  @Override
  public WebVec4 createVec4(float x, float y, float z, float w) {
    return nCreateVec4(x,y,z,w);
  }
  
  private static native WebVec4 nCreateVec4(float x, float y, float z, float w) /*-{
    var ans = $wnd.vec4.create();
    ans[0] = x;
    ans[1] = y;
    ans[2] = z;
    ans[3] = w;
    return ans;
  }-*/;

  /*
  @Override
  public WebVec2 createVec2() {
    return nCreateVec2();
  }
  
  private static native WebVec2 nCreateVec2() /*-{
    return $wnd.vec2.create();
  }-/;

  @Override
  public WebVec2 createVec2(float x, float y) {
    return nCreateVec2(x,y);
  }
  
  private static native WebVec2 nCreateVec2(float x, float y) /*-{
		var ans = $wnd.vec2.create();
		ans[0] = x;
		ans[1] = y;
		return ans;
  }-/;

  @Override
  public void add(Vec2 out, Vec2 a, Vec2 b) {
    nAdd((WebVec2)out,(WebVec2)a,(WebVec2)b);
  }
  
  private static native void nAdd(WebVec2 out, WebVec2 a, WebVec2 b) /*-{
    $wnd.vec2.add(out,a,b);
  }-/;

  @Override
  public void subtract(Vec2 out, Vec2 a, Vec2 b) {
    nSubtract((WebVec2)out,(WebVec2)a,(WebVec2)b);
  }
  
  private static native void nSubtract(WebVec2 out, WebVec2 a, WebVec2 b) /*-{
		$wnd.vec2.subtract(out, a, b);
  }-/;
  

  @Override
  public void normalize(Vec2 out, Vec2 a) {
    nNormalize((WebVec2)out,(WebVec2)a);
  }
  
  private static native void nNormalize(WebVec2 out, WebVec2 a) /*-{
		$wnd.vec2.normalize(out, a);
  }-/;

  @Override
  public void scaleAndAdd(Vec2 out, Vec2 a, Vec2 b, float scale) {
    nScaleAndAdd((WebVec2) out, (WebVec2) a, (WebVec2) b, scale);
  }

  private static native void nScaleAndAdd(WebVec2 out, WebVec2 a, WebVec2 b,
      float scale) /*-{
		$wnd.vec2.scaleAndAdd(out, a, b, scale);
  }-/;

  @Override
  public float norm(Vec2 vec) {
    return nNorm((WebVec2)vec);
  }  
  
  private static native float nNorm(WebVec2 a) /*-{
		return $wnd.vec2.length(a);
  }-/;
  */
  
}
