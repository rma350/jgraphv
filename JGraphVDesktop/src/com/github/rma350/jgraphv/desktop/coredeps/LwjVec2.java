package com.github.rma350.jgraphv.desktop.coredeps;

import org.lwjgl.util.vector.Vector2f;

import com.github.rma350.jgraphv.core.portable.Vec2;

public class LwjVec2 implements Vec2{
  
  private Vector2f vec2;
  
  public LwjVec2(){
    vec2 = new Vector2f();
  }
  
  public Vector2f getVec(){
    return vec2;
  }
  
  public LwjVec2(float x, float y){
    vec2 = new Vector2f(x,y);
  }

  @Override
  public float x() {
    return vec2.x;
  }

  @Override
  public float y() {
    return vec2.y;
  }

  @Override
  public void setX(float x) {
    vec2.x = x;
  }

  @Override
  public void setY(float y) {
    vec2.y = y;
  }

  @Override
  public void set(float x, float y) {
    vec2.x = x;
    vec2.y = y;
  }



}
