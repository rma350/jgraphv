package com.github.rma350.jgraphv.android;

import com.github.rma350.jgraphv.core.engine.Camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

public class GraphView extends GLSurfaceView implements OnGestureListener, OnScaleGestureListener {

  private GraphRenderer mRenderer;
  
  private GestureDetector mDetector;
  private ScaleGestureDetector mScaleDetector;
  

  public GraphView(Context context) {
    super(context);
    setEGLContextClientVersion(2);
    // setDebugFlags(DEBUG_CHECK_GL_ERROR | DEBUG_LOG_GL_CALLS);
    // Set the Renderer for drawing on the GLSurfaceView
    mRenderer = new GraphRenderer();
    setRenderer(mRenderer);
    mDetector = new GestureDetector(this.getContext(),this);
    mScaleDetector = new ScaleGestureDetector(this.getContext(), this);
  }

  @SuppressLint("ClickableViewAccessibility")
  @Override
  public boolean onTouchEvent(MotionEvent e) {
    boolean processed = mScaleDetector.onTouchEvent(e);
    processed = mDetector.onTouchEvent(e) || processed;
    return processed || super.onTouchEvent(e);
  }
  
  public GraphRenderer getGraphRenderer(){
    return mRenderer;
  }

  @Override
  public boolean onDown(MotionEvent arg0) {
    return true;
  }

  @Override
  public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
      float arg3) {
    return false;
  }

  @Override
  public void onLongPress(MotionEvent arg0) {
  }
  
  public void onDemoSelected(final int index){
    this.queueEvent(new Runnable(){

      @Override
      public void run() {
        getGraphRenderer().setDemo(index);
      }});
  }

  @Override
  public boolean onScroll(MotionEvent initMotion, MotionEvent latestMotion, final float dx,
      final float dy) {
    this.queueEvent(new Runnable(){

      @Override
      public void run() {
        Camera cam = mRenderer.getEngine().getCamera(); 
        cam.translateBy(dx, -dy);
      }});
    return true;
  }

  @Override
  public void onShowPress(MotionEvent arg0) {}

  @Override
  public boolean onSingleTapUp(MotionEvent arg0) {
    return false;
  }

  @Override
  public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
    final float x = scaleGestureDetector.getFocusX();
    final float y = scaleGestureDetector.getFocusY();
    final float zoom = scaleGestureDetector.getScaleFactor();
    //Log.d("ROSS", "Zooming about x: " + x + " y: " + y + " zoom level: " + zoom);
    this.queueEvent(new Runnable(){

      @Override
      public void run() {
        mRenderer.getEngine().zoom(zoom, x, y, true);
      }});
    return true;
  }

  @Override
  public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
    return true;
  }

  @Override
  public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {}

}
