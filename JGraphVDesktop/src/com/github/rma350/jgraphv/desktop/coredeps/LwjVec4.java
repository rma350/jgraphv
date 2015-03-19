package com.github.rma350.jgraphv.desktop.coredeps;

import org.lwjgl.util.vector.Vector4f;

import com.github.rma350.jgraphv.core.portable.Vec4;

public class LwjVec4 implements Vec4{
  
  private Vector4f vec4;
  
  public LwjVec4(){
    vec4 = new Vector4f();
  }
  
  public Vector4f getVec(){
    return vec4;
  }
  
  public LwjVec4(float x, float y, float z, float w){
    vec4 = new Vector4f(x,y,z,w);
  }

  @Override
  public float x() {
    return vec4.x;
  }

  @Override
  public float y() {
    return vec4.y;
  }

  @Override
  public float z() {
    return vec4.z;
  }

  @Override
  public float w() {
    return vec4.w;
  }

}
