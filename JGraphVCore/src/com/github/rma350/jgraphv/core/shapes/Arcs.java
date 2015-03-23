package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.Camera;

public class Arcs implements Drawable {
  
  private Camera camera;
  private ArcShader shader;
  private ArcBuffer buffer;
  private float r;
  private float b;
  private float g;
  private float a;
  
  
  public Arcs(Camera camera, ArcShader shader, ArcBuffer buffer, float r, float g, float b, float a){
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
