package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.engine.Camera;

public class DirectedArcs implements Drawable {
  
  private Camera camera;
  private ArcShader arcShader;
  private ArcBuffer arcBuffer;
  private TriangleShader triangleShader;
  private TriangleBuffer triangleBuffer;
  private float r;
  private float b;
  private float g;
  private float a;
  
  
  public DirectedArcs(Camera camera, ArcShader arcShader, ArcBuffer arcBuffer,
      TriangleShader triangleShader, TriangleBuffer triangleBuffer, float r,
      float g, float b, float a) {
    this.camera = camera;
    this.arcShader = arcShader;
    this.arcBuffer = arcBuffer;
    this.triangleShader = triangleShader;
    this.triangleBuffer = triangleBuffer;
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = a;
  }

  @Override
  public void draw() {
    triangleShader.use(r, g, b, a, camera);
    triangleShader.draw(triangleBuffer);
    triangleShader.unuse();
    
    arcShader.use(r, g, b, a, camera);
    arcShader.draw(arcBuffer);
    arcShader.unuse();
  }
}
