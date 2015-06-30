package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.engine.Camera;

public class DirectedLines implements Drawable {
  
  private Camera camera;
  private TriangleShader shader;
  private TriangleBuffer buffer;
  private float r;
  private float b;
  private float g;
  private float a;
  
  public DirectedLines(Camera camera, TriangleShader shader, TriangleBuffer buffer, float r, float g, float b, float a){
    this.camera = camera;
    this.shader = shader;
    this.buffer = buffer;
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = a;
  }

  @Override
  public void draw() {    
    shader.use(r, g, b, a, camera);
    shader.draw(buffer);
    shader.unuse();
  }

}
