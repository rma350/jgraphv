package com.github.rma350.jgraphv.core;

import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.shapes.ArcShader;
import com.github.rma350.jgraphv.core.shapes.CirclesShader;
import com.github.rma350.jgraphv.core.shapes.LinesShader;
import com.github.rma350.jgraphv.core.shapes.PointsShader;
import com.github.rma350.jgraphv.core.shapes.TriangleShader;

public class Engine {

  private GL gl;
  private Camera camera;

  private ArcShader arcShader;
  private PointsShader pointsShader;
  private LinesShader linesShader;
  private CirclesShader circlesShader;
  private TriangleShader triangleShader;

  private Scene scene;
  
  public ArcShader getArcShader() {
    return arcShader;
  }

  public PointsShader getPointsShader() {
    return pointsShader;
  }

  public LinesShader getLinesShader() {
    return linesShader;
  }

  public CirclesShader getCirclesShader() {
    return circlesShader;
  }
  
  public TriangleShader getTriangleShader(){
    return triangleShader;
  }

  public Scene getScene() {
    return scene;
  }

  public GL getGL() {
    return gl;
  }

  public Engine(GL gl) {
    this.gl = gl;
    gl.glClearColor(1, 1, 1, 1);
    gl.glEnable(gl.kGL_BLEND());
    gl.glBlendFunc(gl.kGL_SRC_ALPHA(), gl.kGL_ONE_MINUS_SRC_ALPHA());
    camera = new Camera(gl.getLinMath());

    
    pointsShader = new PointsShader(gl);
    circlesShader = new CirclesShader(gl);
    linesShader = new LinesShader(gl);
    triangleShader = new TriangleShader(gl);
    arcShader = new ArcShader(gl);
    scene = new Scene();
    
  }

  public void drawFrame() {
    gl.glClear(gl.kGL_COLOR_BUFFER_BIT());
    scene.draw();
  }

  public void onResize(int width, int height) {
    gl.glViewport(0, 0, width, height);
    camera.setViewport(width, height);
  }

  /** Zooms in when scaleFactor > 1, zooms out when scaleFactor < 1. */
  public void zoom(float scaleFactor, float screenCenterX, float screenCenterY) {
    camera.zoom(scaleFactor, screenCenterX, screenCenterY);
  }

  public void zoom(float scaleFactor, float screenCenterX, float screenCenterY,
      boolean invertY) {
    camera.zoom(scaleFactor, screenCenterX, screenCenterY, invertY);
  }

  public Camera getCamera() {
    return camera;
  }

}
