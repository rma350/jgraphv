package com.github.rma350.jgraphv.android.coredeps;

import com.github.rma350.jgraphv.core.portable.Vec4;

public class AndroidVec4 implements Vec4 {
  
  private float[] mData;
  
  public AndroidVec4(){
    mData = new float[4];
  }
  
  public AndroidVec4(float x, float y, float z, float w){
    mData = new float[4];
    mData[0] = x;
    mData[1] = y;
    mData[2] = z;
    mData[3] = w;
  }
  
  public float[] getData(){
    return mData;
  }

  @Override
  public float w() {
    return mData[3];
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
  public float z() {
    return mData[2];
  }

}
