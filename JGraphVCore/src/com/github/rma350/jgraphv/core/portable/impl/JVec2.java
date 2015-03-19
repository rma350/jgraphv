package com.github.rma350.jgraphv.core.portable.impl;

import com.github.rma350.jgraphv.core.portable.Vec2;

public class JVec2 implements Vec2 {
  
  private float[] mData;
  
  public JVec2(){
    mData = new float[2];
  }
  
  public JVec2(float x, float y) {
    mData = new float[4];
    mData[0] = x;
    mData[1] = y;
  }
  
  public float[] getData(){
    return mData;
  }


  @Override
  public float x() {
    return mData[0];
  }

  @Override
  public float y() {
    return mData[1];
  }

  @Override
  public void setX(float x) {
    mData[0] = x;
  }

  @Override
  public void setY(float y) {
    mData[1] = y;
  }

}