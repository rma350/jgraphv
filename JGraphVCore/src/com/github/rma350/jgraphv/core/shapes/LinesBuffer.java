package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.portable.GL;

public class LinesBuffer  extends NativeShapeBuffer{
  
  private static int floatsPerVert = 2;
  private static int vertsPerLine = 2;

  public LinesBuffer(GL gl, int numLines, BufferUsage bufferUsage) {
    super(gl, floatsPerVert, vertsPerLine, numLines, bufferUsage);    
  }
  
  public static float[] makeBuffer(int numLines){
    return new float[numLines*vertsPerLine*floatsPerVert];
  }
  
  private static int floatIndex(int lineIndex){
    return lineIndex*vertsPerLine*floatsPerVert;
  }
  
  public static void setLine(float[] buffer, int lineIndex, float startX, float startY, float endX, float endY){
    int next = floatIndex(lineIndex);    
    buffer[next++] = startX;
    buffer[next++] = startY;    
    buffer[next++] = endX;
    buffer[next++] = endY;
  }
}
