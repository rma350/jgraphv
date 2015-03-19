package com.github.rma350.jgraphv.core.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import com.github.rma350.jgraphv.core.portable.NativeFloatBuffer;

public class NioFloatBuffer implements NativeFloatBuffer{
  
  private static final int BYTES_PER_FLOAT = 4;
  
  private FloatBuffer buffer;
  
  public NioFloatBuffer(int floats){
    ByteBuffer bb = ByteBuffer.allocateDirect(
        BYTES_PER_FLOAT * floats);
    bb.order(ByteOrder.nativeOrder());
    buffer = bb.asFloatBuffer();
  }

  @Override
  public float get(int index) {
    return buffer.get(index);
  }
  
  public FloatBuffer getBuffer(){
    return buffer;
  }

  @Override
  public void putAll(float[] values, int offsetInFloats) {
    this.setPositionInFloats(offsetInFloats);
    buffer.put(values);
  }

  @Override
  public void putAll(float[] values) {
    buffer.put(values);
  }

  @Override
  public void put(int index, float value) {
    buffer.put(index, value);
  }

  @Override
  public void setPositionInFloats(int index) {
    buffer.position(index*BYTES_PER_FLOAT);
  }

  @Override
  public int getPositionInFloats() {
    int positionInBytes = buffer.position();
    if(positionInBytes % BYTES_PER_FLOAT != 0){
      throw new RuntimeException();
    }
    return positionInBytes/BYTES_PER_FLOAT;
  }

  @Override
  public int getPositionInBytes() {
    return buffer.position();
  }

  @Override
  public int getSizeInFloats() {
    return buffer.capacity();
  }

  @Override
  public int getSizeInBytes() {
    return buffer.capacity()*BYTES_PER_FLOAT;
  }

}
