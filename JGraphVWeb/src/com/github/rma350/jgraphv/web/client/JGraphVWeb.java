package com.github.rma350.jgraphv.web.client;

import com.github.rma350.jgraphv.core.demo.AllDemos;
import com.github.rma350.jgraphv.core.demo.Demo;
import com.github.rma350.jgraphv.core.engine.Engine;
import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.Log;
import com.github.rma350.jgraphv.core.portable.impl.SynchronousTaskRunner;
import com.github.rma350.jgraphv.web.client.coredeps.WebGL;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArrayString;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class JGraphVWeb implements EntryPoint {
  
  private static final String TAG = JGraphVWeb.class.getSimpleName();
  
  
  private GL gl;
  private Engine engine;
  private int canvasWidth;
  private int canvasHeight;
  
  private int currentDemo = 0;
  
  public static JsArrayString getDemoNames() {
    JsArrayString ans = JsArrayString.createArray().cast();
    for(Demo demo: AllDemos.allDemos()){
      ans.push(demo.getName());
    }
    return ans;
  }
  
  public void setCurrentDemo(int demoIndex){
    engine.reset();
    currentDemo = demoIndex;
    AllDemos.allDemos().get(currentDemo).run(engine);
  }
 
  public void setupGL(){
    WebGL webGL = new WebGL("graph-canvas",true);
    gl = webGL;
    engine = new Engine(gl, new SynchronousTaskRunner());
    resize(webGL.getCanvasWidth(), webGL.getCanvasHeight());
    AllDemos.allDemos().get(currentDemo).run(engine);
  }
  
  public void resize(int widthPx, int heightPx) {
    Log.out.d(TAG, "width, height: " + widthPx + ", " + heightPx);
    engine.onResize(widthPx, heightPx);
    canvasWidth = widthPx;
    canvasHeight = heightPx;
  }
  
  public void drawScene(){
    engine.drawFrame();
    //engine.drawScene();
  }
  
  public void onClick(int screenX, int screenY){
    //Log.out.d(TAG, "Got input at: " + screenX + ", " + screenY);
    // The engine has (0,0) in the bottom left corner, but browser click
    // coordinates put (0,0) in the top right corner.
    engine.getCamera().moveTo(screenX, canvasHeight - screenY);
  }
  
  public void onMouseMove(int deltaX, int deltaY){
    //Log.out.d(TAG, "Got mouse move of: " + deltaX + ", " + deltaY);
    // The engine has (0,0) in the bottom left corner, but browser click
    // coordinates put (0,0) in the top right corner.
    engine.getCamera().translateBy(-deltaX, deltaY);
  }
  
  public void onMouseWheel(int screenX, int screenY, int delta){
    //Log.out.d(TAG, "Got input at: " + screenX + ", " + screenY + " delta " + delta);
    float zoom = 1;
    if(delta > 0) {
      zoom = 1.25f;
    }
    else if(delta < 0){
      zoom = .8f;
    }
    // The engine has (0,0) in the bottom left corner, but browser click
    // coordinates put (0,0) in the top right corner.
    engine.zoom(zoom, screenX, canvasHeight - screenY);
  }
  
  public void onZoomAbout(int screenX, int screenY, float scaleFactor){
    Log.out.d(TAG, "Zoom about input at: " + screenX + ", " + screenY + " delta " + scaleFactor);
    // The engine has (0,0) in the bottom left corner, but browser click
    // coordinates put (0,0) in the top right corner.
    engine.zoom(scaleFactor, screenX, canvasHeight - screenY);
  }
  
  public void update(double ellapsedTimeMs){
    engine.update((long)ellapsedTimeMs);
  }
  
  public native void publish() /*-{
    var that = this;
    $wnd.jSetupGL = $entry(function() {
      that.@com.github.rma350.jgraphv.web.client.JGraphVWeb::setupGL()();
    });
    $wnd.jResize = $entry(function(x,y) {
      that.@com.github.rma350.jgraphv.web.client.JGraphVWeb::resize(II)(x,y);
    });
    $wnd.jDrawScene = $entry(function() {
      that.@com.github.rma350.jgraphv.web.client.JGraphVWeb::drawScene()();
    });
    $wnd.jUpdate = $entry(function(x) {
      that.@com.github.rma350.jgraphv.web.client.JGraphVWeb::update(D)(x);
    });     
    $wnd.jOnClick = $entry(function(x,y) {
      that.@com.github.rma350.jgraphv.web.client.JGraphVWeb::onClick(II)(x,y);
    });
    $wnd.jOnMouseMove = $entry(function(x,y) {
      that.@com.github.rma350.jgraphv.web.client.JGraphVWeb::onMouseMove(II)(x,y);
    });
    $wnd.jOnMouseWheel = $entry(function(x,y,d) {
      that.@com.github.rma350.jgraphv.web.client.JGraphVWeb::onMouseWheel(III)(x,y,d);
    });
    $wnd.jOnZoomAbout = $entry(function(x,y,s) {
      that.@com.github.rma350.jgraphv.web.client.JGraphVWeb::onZoomAbout(IIF)(x,y,s);
    });
    $wnd.jGetDemoNames = $entry(@com.github.rma350.jgraphv.web.client.JGraphVWeb::getDemoNames());
    $wnd.jSetCurrentDemo = $entry(function(i) {
      that.@com.github.rma350.jgraphv.web.client.JGraphVWeb::setCurrentDemo(I)(i);
    });
  }-*/;
  
  public static native void startOnReady()/*-{
    $wnd.onGWTModuleLoaded();
  }-*/;
  

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    publish();
    startOnReady();
  }
}
