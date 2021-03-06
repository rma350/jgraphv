package com.github.rma350.jgraphv.core.portable;

public interface Vec2 {
  
  public float x();
  public float y();
  
  public void setX(float x);
  public void setY(float y);
  
  public void set(float x, float y);
  
  public void copy(Vec2 other);

}
