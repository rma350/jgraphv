package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.Mat4;
import com.github.rma350.jgraphv.core.portable.NativeFloatBuffer;

public class NativeShapeBuffer {
  
  private static final int BYTES_PER_FLOAT = 4;

  private final int vertexSizeFloats;
  private final int vertsPerShape;
  
  private NativeFloatBuffer vertexBuffer;
  private Mat4 modelMatrix;
  
  protected GL gl;
  
  private final int numShapes;
  
  private int glBuffer;
  
  private boolean isOnGpu = false;
  
  public NativeShapeBuffer(GL gl, int vertexSizeFloats, int vertsPerShape, int numShapes) {
    this.gl = gl;
    this.numShapes = numShapes;
    this.vertexSizeFloats = vertexSizeFloats;
    this.vertsPerShape = vertsPerShape;
    vertexBuffer = gl.createFloatBuffer(vertexSizeFloats* vertsPerShape * numShapes);
    glBuffer = gl.glCreateBuffer();
    modelMatrix = gl.getLinMath().createMat4();
    gl.getLinMath().setIdentity(modelMatrix);
  }
  
  public int getShapeCount(){
    return numShapes;
  }
  
  public int getVertexCount(){
    return numShapes*vertsPerShape;
  }
  
  public int getFloatCount(){
    return numShapes*vertsPerShape*vertexSizeFloats;
  }
  
  public int getByteCount(){
    return numShapes*vertsPerShape*vertexSizeFloats * BYTES_PER_FLOAT;
  }
  
  public void put(int index, float value){
    vertexBuffer.put(index, value);
  }
  
  public void put(float[] vertexData){
    vertexBuffer.putAll(vertexData);
    vertexBuffer.setPositionInFloats(0);
  }
  
  protected void validateFloatAlignment(int numFloats) {
    if (numFloats % (vertexSizeFloats * vertsPerShape) != 0) {
      throw new RuntimeException("Expected num floats to be divisible by "
          + (vertexSizeFloats * vertsPerShape) + " but was " + numFloats);
    }
  }
  
  public void checkedPut(float[] vertexData){
    validateFloatAlignment(vertexData.length);
    put(vertexData);
  }
  
  public void checkedPutShapes(float[] vertexData){
    validateFloatAlignment(vertexData.length);
    put(vertexData);
  }
  
  public void checkedPut(float[] vertexData, int srcOffset, int numFloats){
    validateFloatAlignment(numFloats);
    put(vertexData);
  }
    
  public Mat4 getModelMatrix() {
    return modelMatrix;
  }

  public NativeFloatBuffer getCpuBufferData() {
    return vertexBuffer;
  }
  
  public void bindBuffer(){
    gl.glBindBuffer(gl.kGL_ARRAY_BUFFER(), glBuffer);
  }
  
  public void unBindBuffer(){
    gl.glBindBuffer(gl.kGL_ARRAY_BUFFER(), 0);
  }
  
  public void ensureOnGpu(){
    if(!isOnGpu){
      bufferData();
      isOnGpu = true;
    }
  }
  
  public void bufferData(){
    vertexBuffer.setPositionInFloats(0);
    gl.glBufferData(gl.kGL_ARRAY_BUFFER(), vertexBuffer, gl.kGL_STATIC_DRAW());
  }
  
  /** Visible for testing */
  public NativeFloatBuffer getVertexBuffer(){
    return vertexBuffer;
  }

}
