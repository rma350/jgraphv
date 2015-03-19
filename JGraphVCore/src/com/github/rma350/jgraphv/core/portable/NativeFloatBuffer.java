package com.github.rma350.jgraphv.core.portable;

/** Use method names to allow extending existing classes without naming conflicts.*/
public interface NativeFloatBuffer {
  
  /**Leaves the position at offset + values.length*/
  public void putAll(float[] values, int offset);
  
  /** Writes values starting at the buffers current position.  Leaves the position at init position + values.length.*/
  public void putAll(float[] values);
  
  /**Does not change the position.*/
  public void put(int index, float value);
  
  /**Does not change the position.*/
  public float get(int index);
  
  /**Sets the position to index.*/
  public void setPositionInFloats(int index);
  
  public int getPositionInFloats();
  public int getPositionInBytes();
  
  public int getSizeInFloats();
  public int getSizeInBytes();
  
}
