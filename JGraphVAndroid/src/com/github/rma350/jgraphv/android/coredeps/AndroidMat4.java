package com.github.rma350.jgraphv.android.coredeps;

import com.github.rma350.jgraphv.core.portable.Mat4;

public class AndroidMat4 implements Mat4 {
  
  private float[] mData;
  
  AndroidMat4(){
    mData = new float[16];
  }
  
  float[] getData(){
    return mData;
  }

}
