package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.Vec2;

public class TriangleBuffer extends NativeShapeBuffer {
  
  private static int floatsPerVert = 2;
  private static int vertsPerTriangle = 3;

  public TriangleBuffer(GL gl, int numTriangles) {
    super(gl, floatsPerVert, vertsPerTriangle, numTriangles);    
  }
  
  public static float[] makeBuffer(int numTriangles){
    return new float[numTriangles*vertsPerTriangle*floatsPerVert];
  }
  
  public static int floatIndex(int triangleIndex){
    return triangleIndex*vertsPerTriangle*floatsPerVert;
  }
  
  public static void setTriangle(float[] buffer, int triangleIndex, Vec2 v1, Vec2 v2, Vec2 v3){
    setTriangle(buffer,triangleIndex, v1.x(), v1.y(), v2.x(), v2.y(), v3.x(), v3.y());
  }
  
  public static void setTriangle(float[] buffer, int triangleIndex, float x1, float y1, float x2, float y2, float x3, float y3){
    int next = floatIndex(triangleIndex);    
    buffer[next++] = x1;
    buffer[next++] = y1;    
    buffer[next++] = x2;
    buffer[next++] = y2;
    buffer[next++] = x3;
    buffer[next++] = y3;
  }
}
