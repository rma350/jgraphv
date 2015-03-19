package com.github.rma350.jgraphv.core;

public class Circles implements Drawable{

  private Camera camera;
  private CirclesShader shader;
  private CirclesBuffer buffer;
  private float r;
  private float b;
  private float g;
  private float a;
  
  
  public Circles(Camera camera, CirclesShader shader, CirclesBuffer buffer, float r, float g, float b, float a){
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
