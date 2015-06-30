package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.portable.GL;

public class CirclesBuffer extends NativeShapeBuffer {
  
  private static int floatsPerVert = 4;
  private static int vertsPerCircle = 6;

  public CirclesBuffer(GL gl, int numCircles, BufferUsage bufferUsage) {
    super(gl, floatsPerVert, vertsPerCircle, numCircles, bufferUsage);
  }
  
  public static float[] makeBuffer(int numCircles){
    return new float[numCircles*vertsPerCircle*floatsPerVert];
  }
  
  private static int floatIndex(int circleIndex){
    return circleIndex*vertsPerCircle*floatsPerVert;
  }
  
  
  
  public static void setCircle(float[] buffer, int circleIndex, float centerX, float centerY, float radius){
    int next = floatIndex(circleIndex);
    float top = centerY + radius;
    float bottom = centerY - radius;
    float left = centerX - radius;
    float right = centerX + radius;
    
    
    buffer[next++] = left;
    buffer[next++] = top;    
    buffer[next++] = -1;
    buffer[next++] = 1;
    
    buffer[next++] = left;
    buffer[next++] = bottom;    
    buffer[next++] = -1;
    buffer[next++] = -1;
    
    buffer[next++] = right;
    buffer[next++] = bottom;    
    buffer[next++] = 1;
    buffer[next++] = -1;
    
    buffer[next++] = left;
    buffer[next++] = top;
    buffer[next++] = -1;
    buffer[next++] = 1;
    
    buffer[next++] = right;
    buffer[next++] = bottom;
    buffer[next++] = 1;
    buffer[next++] = -1;
    
    buffer[next++] = right;
    buffer[next++] = top;
    buffer[next++] = 1;
    buffer[next++] = 1;
    
  }
}
