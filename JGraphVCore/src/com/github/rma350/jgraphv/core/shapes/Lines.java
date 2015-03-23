package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.Camera;


public class Lines implements Drawable{
  
  private Camera camera;
  private LinesShader shader;
  private LinesBuffer buffer;
  private float r;
  private float b;
  private float g;
  private float a;
  private float width;
  
  
  public Lines(Camera camera, LinesShader shader, LinesBuffer buffer, float r, float g, float b, float a, float width){
    this.camera = camera;
    this.shader = shader;
    this.buffer = buffer;
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = a;
    this.width = width;
  }

  @Override
  public void draw() {
    
    shader.use(r, g, b, a, camera, width);
    shader.draw(buffer);
    shader.unuse();
  }

}
