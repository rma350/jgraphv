package com.github.rma350.jgraphv.core.portable;

/**
 * Use method names to allow extending existing classes without naming
 * conflicts.
 */
public interface NativeFloatBuffer {

  /**
   * Writes values from src into this. Copies srcFloatCount values starting at
   * src[srcOffset]. The values are written to this starting at the current
   * position. At the end of operation, the current position increased by
   * floatCount.
   */
  public void putAll(float[] src, int srcOffset, int srcFloatCount);

  /**
   * Writes values starting at the buffers current position. Leaves the position
   * at init position + values.length.
   */
  public void putAll(float[] values);

  /** Does not change the position. */
  public void put(int index, float value);

  /** Does not change the position. */
  public float get(int index);

  /** Sets the position to index. */
  public void setPositionInFloats(int index);

  public int getPositionInFloats();

  public int getPositionInBytes();

  public int getSizeInFloats();

  public int getSizeInBytes();

}
