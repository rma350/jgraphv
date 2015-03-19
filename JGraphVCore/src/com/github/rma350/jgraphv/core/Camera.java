package com.github.rma350.jgraphv.core;

import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Mat4;
import com.github.rma350.jgraphv.core.portable.Vec4;

public class Camera {
  
  // screen to GL
  private Mat4 projection;
  
  // world to screen
  private Mat4 view;
  
  // world coordinates of the current view
  private float viewBLX;
  private float viewBLY;
  private float zoomLevel;
  
  private int screenWidthPx;
  private int screenHeightPx;
  
  private LinMath linMath;
  
  public Camera(LinMath gl){
    projection = gl.createMat4();
    gl.setIdentity(projection);
    view = gl.createMat4();
    gl.setIdentity(view);
    this.linMath = gl;
    viewBLX = 0;
    viewBLY = 0;
    zoomLevel = 1;
  }
  
  public float getViewBLX(){
    return viewBLX;
  }
  
  public float getViewBLY(){
    return viewBLY;
  }
  
  public void zoom(float scaleFactor, float zoomAboutX, float zoomAboutY, boolean invertY) {
    float newY = zoomAboutY;
    if(invertY){
      newY = this.screenHeightPx - newY;
    }
    zoom(scaleFactor,zoomAboutX, newY);
  }

  /** The point zoomAbout is in screen coordinates.*/
  public void zoom(float scaleFactor, float zoomAboutX, float zoomAboutY) {
    //System.out.println("Zooming about screen: " + zoomAboutX + ", " + zoomAboutY );
    Vec4 worldZoomPoint = this.screenToWorld(zoomAboutX, zoomAboutY);
    //System.out.println("Zooming about world: " + gl.x(worldZoomPoint) + ", " + gl.y(worldZoomPoint) );
    
    zoomLevel*=scaleFactor;
    
    float tx = zoomAboutX/zoomLevel - worldZoomPoint.x();
    float ty = zoomAboutY/zoomLevel - worldZoomPoint.y();
    moveWorldBLTo(-tx,-ty);    
    
    
    
  }
  
  /** Input is in word coordinates*/
  public void moveWorldBLTo(float x, float y){
    linMath.setIdentity(view);
    linMath.scale(view, this.zoomLevel, this.zoomLevel);
    linMath.translate(view, -x, -y);
        
    viewBLX = x;
    viewBLY = y;
  }
  
  public void translateBy(float screenDx, float screenDy){
    Vec4 worldDistMv = screenDistToWorldDist(screenDx, screenDy);
    moveWorldBLTo(viewBLX + worldDistMv.x(), viewBLY + worldDistMv.y());
  }
  
  public void moveTo(int screenCenterX, int screenCenterY){
    int newBLX = screenCenterX - this.screenWidthPx/2;
    int newBLY = screenCenterY - this.screenHeightPx/2;
    System.out.println("Moving to screen coords (x,y): "  + screenCenterX + ", " + screenCenterY);
    Vec4 worldNewCenter = screenToWorld(screenCenterX, screenCenterY);
    
    float newWorldCenterX = worldNewCenter.x();
    float newWorldCenterY = worldNewCenter.y();
    System.out.println("Moving to world coords center (x,y): "  + newWorldCenterX + ", " + newWorldCenterY);
    
    Vec4 worldNewBL = screenToWorld(newBLX, newBLY);
    float newWorldBLX = worldNewBL.x();
    float newWorldBLY = worldNewBL.y();
    System.out.println("Moving to world coords bottom left (x,y): "  + newWorldBLX + ", " + newWorldBLY);
    
    moveWorldBLTo(newWorldBLX, newWorldBLY);
  }
  
  public Vec4 screenDistToWorldDist(float screenX, float screenY){
    return linMath.mult(linMath.invert(view), linMath.createVec4(screenX, screenY, 0, 0));
  }
  
  public Vec4 screenToWorld(float screenX, float screenY){
    return linMath.mult(linMath.invert(view), linMath.createVec4(screenX, screenY, 0, 1));
  }
  
  public Vec4 worldToScreen(float worldX, float worldY){
    return linMath.mult(view, linMath.createVec4(worldX, worldY, 0, 1));
  }
  
  
  
  
  public Mat4 getProjection(){
    return projection;
  }
  
  public Mat4 getView(){
    return view;
  }
  
  // on a screen resize, updates the screen to gl coordiantes. 
  public void setViewport(int width, int height){
    linMath.setOrtho2D(projection, 0, width, 0, height);
    screenWidthPx = width;
    screenHeightPx = height;
  }

}
