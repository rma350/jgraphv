package com.github.rma350.jgraphv.web.client.coredeps;

import com.github.rma350.jgraphv.core.portable.NativeFloatBuffer;
import com.google.gwt.typedarrays.client.Float32ArrayNative;

public class WebNativeFloatBuffer implements NativeFloatBuffer{
  
  private Float32ArrayNative nativeArray;
  
  public WebNativeFloatBuffer(int sizeInFloats){
    nativeArray = Float32ArrayNative.create(sizeInFloats); 
  }
  
  public Float32ArrayNative getNativeArray(){
    return nativeArray;
  }

  @Override
  public void putAll(float[] src, int srcOffset, int srcFloatCount) {
    float[] srcExact = new float[srcFloatCount];
    System.arraycopy(src, srcOffset, srcExact, 0, srcFloatCount);
    nativeArray.set(srcExact);
  }

  @Override
  public void putAll(float[] values) {
    nativeArray.set(values);
  }

  @Override
  public void put(int index, float value) {
    nativeArray.set(index, value);
  }

  @Override
  public float get(int index) {
    return nativeArray.get(index);
  }

  @Override
  public void setPositionInFloats(int index) {}

  @Override
  public int getPositionInFloats() {
    return 0;
  }

  @Override
  public int getPositionInBytes() {
    return 0;
  }

  @Override
  public int getSizeInFloats() {
    return nativeArray.length();
  }

  @Override
  public int getSizeInBytes() {
    return nativeArray.byteLength();
  }
  

}
