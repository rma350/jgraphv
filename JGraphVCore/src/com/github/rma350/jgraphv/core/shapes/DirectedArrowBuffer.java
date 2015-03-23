package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Vec2;

public class DirectedArrowBuffer {
  
  private static final int trianglesPerArrow = 3;
  
  private LinMath linMath;
  
  
  private float[] buffer;
  
  private TriangleBuffer triangleBuffer;
  
  public DirectedArrowBuffer(GL gl, int numArrows){
    this.linMath = gl.getLinMath();
    int numTriangles = numArrows*trianglesPerArrow; 
    buffer=TriangleBuffer.makeBuffer(numTriangles);
    triangleBuffer = new TriangleBuffer(gl,numTriangles);
    initSetArrowWithOffsetLocals();
    initSetArrowLocals();
  }
  
  
  public float[] getBuffer(){
    return buffer;
  }
  
  
  
  private void initSetArrowWithOffsetLocals(){
    lDiffUnit = linMath.createVec2();
    lRealStart = linMath.createVec2();
    lRealEnd = linMath.createVec2();
  }
  private Vec2 lDiffUnit;
  private Vec2 lRealStart;
  private Vec2 lRealEnd;
  
  public void setArrow(int arrowIndex, Vec2 start, Vec2 end, float arrowWidth, float arrowHeadWidth, float arrowHeadLength, float startSpacing, float endSpacing){
    
    
    linMath.subtract(lDiffUnit, end, start);
    float distance = linMath.norm(lDiffUnit);
    linMath.normalize(lDiffUnit, lDiffUnit);
    
    linMath.scaleAndAdd(lRealStart, start, lDiffUnit, Math.min(startSpacing, distance/3));
    linMath.scaleAndAdd(lRealEnd, end, lDiffUnit, -Math.min(endSpacing, distance/3));
    
    
    setArrow(arrowIndex, lRealStart, lRealEnd, arrowWidth, arrowHeadWidth, arrowHeadLength);
  }
  
  
  private void initSetArrowLocals(){
    l2DiffUnit = linMath.createVec2();
    lPerp = linMath.createVec2();
    lTopStart = linMath.createVec2();
    lBottomStart = linMath.createVec2();
    lTopEnd = linMath.createVec2();
    lBottomEnd = linMath.createVec2();
    lArrowStart = linMath.createVec2();
    lArrowEnd = linMath.createVec2();
    lLineEnd = linMath.createVec2();
  }
  private Vec2 l2DiffUnit;
  private Vec2 lPerp;
  private Vec2 lTopStart;
  private Vec2 lBottomStart;
  private Vec2 lTopEnd;
  private Vec2 lBottomEnd;
  private Vec2 lArrowStart;
  private Vec2 lArrowEnd;
  private Vec2 lLineEnd;
  
  public void setArrow(int arrowIndex, Vec2 start, Vec2 end, float arrowWidth, float arrowHeadWidth, float arrowHeadLength){
    
    
    linMath.subtract(l2DiffUnit, end, start);
    float distance = linMath.norm(l2DiffUnit);
    linMath.normalize(l2DiffUnit, l2DiffUnit);
    
    lPerp.set(-l2DiffUnit.y(), l2DiffUnit.x());
    
    linMath.scaleAndAdd(lTopStart, start, lPerp, arrowWidth/2);
    linMath.scaleAndAdd(lBottomStart, start, lPerp, -arrowWidth/2);
    
    
    float actualArrowHeadLength = Math.min(distance/2, arrowHeadLength);
    linMath.scaleAndAdd(lLineEnd, end, l2DiffUnit, -actualArrowHeadLength);
    
    
    linMath.scaleAndAdd(lTopEnd, lLineEnd, lPerp, arrowWidth/2);
    
    linMath.scaleAndAdd(lBottomEnd, lLineEnd, lPerp, -arrowWidth/2);
    
    
    linMath.scaleAndAdd(lArrowStart, lLineEnd, lPerp, arrowHeadWidth/2);
    
    linMath.scaleAndAdd(lArrowEnd, lLineEnd, lPerp, -arrowHeadWidth/2);
    
    
    
    
    int triangleIndex = arrowIndex*trianglesPerArrow;
    
    TriangleBuffer.setTriangle(buffer, triangleIndex++, lTopStart, lBottomStart, lTopEnd);
    TriangleBuffer.setTriangle(buffer, triangleIndex++, lBottomStart, lTopEnd, lBottomEnd);
    TriangleBuffer.setTriangle(buffer, triangleIndex++, lArrowStart, lArrowEnd, end);
    
  }
  
  public void writeToNative(){
    triangleBuffer.checkedPutShapes(buffer);
  }
  
  public TriangleBuffer getTriangles(){
    return triangleBuffer;
  }

}
