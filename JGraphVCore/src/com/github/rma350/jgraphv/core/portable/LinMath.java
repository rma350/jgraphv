package com.github.rma350.jgraphv.core.portable;

public interface LinMath {
  
  public Mat4 createMat4();
  public Vec4 createVec4(float x, float y, float z, float w);
  
  /**Creates a vec2 (0,0)*/
  public Vec2 createVec2();
  public Vec2 createVec2(float x, float y);
  
  /** Adds a+b, stores the result in out.  Out can == a or b.*/
  public void add(Vec2 out, Vec2 a, Vec2 b);
  /** Computes a-b, stores the result in out.  Out can == a or b.*/
  public void subtract(Vec2 out, Vec2 a, Vec2 b);
  
  /** Computes a/|a|_2, stores the result in out.*/
  public void normalize(Vec2 out, Vec2 a);
  
  /** Computes a + b*scale and stores the result in out.  out can == a or b.*/
  public void scaleAndAdd(Vec2 out, Vec2 a, Vec2 b, float scale);
  
  /** Computes sqrt(vec.x^2 + vec.y^2)*/
  public float norm(Vec2 vec);
  
  
  public void setIdentity(Mat4 mat);
  
  public void setOrtho2D(Mat4 mat, float left, float right, float bottom, float top);
  
  public void scale(Mat4 mat, float scaleX, float scaleY);
  public void translate(Mat4 mat, float dX, float dY);
  
  
  public Mat4 invert(Mat4 mat);
  public Vec4 mult(Mat4 mat, Vec4 vec);
  
  
  
  

}
