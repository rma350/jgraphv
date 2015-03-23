package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.portable.Vec2;

public class ArcBufferBuilder {

  private float[] buffer;
  private int sizeInFloats;

  public ArcBufferBuilder(int expectedArcTriangles) {
    buffer = new float[ArcMemLayout.FLOATS_PER_ARC_TRIANGLE
        * Math.max(1, expectedArcTriangles)];
    sizeInFloats = 0;
  }

  int capacity() {
    return buffer.length;
  }

  public int sizeInFloats() {
    return sizeInFloats;
  }
  
  public int sizeInTriangles() {
    return sizeInFloats/3;
  }
  
  public float[] getBuffer(){
    return buffer;
  }

  public void appendArc(ArcBuilder arcBuilder) {
    if (capacity() < sizeInFloats + ArcMemLayout.FLOATS_PER_ARC_TRIANGLE) {
      // Note that this is guarenteed to be enough space, as the minimum buffer
      // size is enough to fit one triangle, and we will double the buffer size.
      
      float[] replacement = new float[capacity()*2];
      for(int i = 0; i < this.sizeInFloats; i++){
        replacement[i] = buffer[i];
      }
      //System.arraycopy(buffer, 0, replacement, 0, this.sizeInFloats);
      buffer = replacement;
      
      // TODO(rma350): when upgrading to GWT 2.7, replace with
      // buffer = Arrays.copyOf(buffer, capacity() * 2);
    }
    writeArcTriangle(buffer, sizeInFloats, arcBuilder);
    sizeInFloats += ArcMemLayout.FLOATS_PER_ARC_TRIANGLE;
  }
  
  private static void writeVec2(float[] buffer, int offset, Vec2 vec2){
    buffer[offset] = vec2.x();
    buffer[offset+1] = vec2.y();
  }
  
  private static void writeVertex(float[] buffer, int offset, Vec2 position,
      Vec2 circleCenter, float circleRadius, float arcWidth) {
    writeVec2(buffer, offset + ArcMemLayout.POSITION_OFFSET, position);
    writeVec2(buffer, offset + ArcMemLayout.CIRCLE_CENTER_OFFSET, circleCenter);
    buffer[offset+ArcMemLayout.CIRCLE_RADIUS_OFFSET] = circleRadius;
    buffer[offset+ArcMemLayout.ARC_WIDTH_OFFSET] = arcWidth;
  }

  public static void writeArcTriangle(float[] buffer, int offset, Vec2 t1,
      Vec2 t2, Vec2 t3, Vec2 circleCenter, float circleRadius, float arcWidth) {
    
    writeVertex(buffer, offset, t1,circleCenter,circleRadius,arcWidth);
    offset+= ArcMemLayout.VERTEX_SIZE;
    
    writeVertex(buffer, offset, t2,circleCenter,circleRadius,arcWidth);
    offset+= ArcMemLayout.VERTEX_SIZE;
    
    writeVertex(buffer, offset, t3,circleCenter,circleRadius,arcWidth);
    offset+= ArcMemLayout.VERTEX_SIZE;
  }

  public static void writeArcTriangle(float[] buffer, int offset,
      ArcBuilder arcBuilder) {
    final Vec2 t1 = arcBuilder.oFrom;
    final Vec2 t2;
    final Vec2 t3;
    // triangle needs to be in counter clockwise order.
    if (arcBuilder.iBendLeft) {
      t2 = arcBuilder.oCircleCenter;
      t3 = arcBuilder.oTo;
    } else {
      t2 = arcBuilder.oTo;
      t3 = arcBuilder.oCircleCenter;
    }
    writeArcTriangle(buffer, offset, t1, t2, t3, arcBuilder.oCircleCenter,
        arcBuilder.oCircleRadius, arcBuilder.iArcWidth);
  }

}
