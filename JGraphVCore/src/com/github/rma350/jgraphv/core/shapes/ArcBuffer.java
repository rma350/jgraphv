package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.portable.GL;

public class ArcBuffer extends NativeShapeBuffer {
  
  public ArcBuffer(GL gl, int numTriangles, BufferUsage bufferUsage){
    super(gl, ArcMemLayout.VERTEX_SIZE, 3, numTriangles, bufferUsage);
  }

  public ArcBuffer(GL gl, ArcBufferBuilder arcBufferBuilder, BufferUsage bufferUsage) {
    super(gl, ArcMemLayout.VERTEX_SIZE, 3, arcBufferBuilder.sizeInTriangles(), bufferUsage);
    this.checkedPut(arcBufferBuilder.getBuffer(), 0,
        arcBufferBuilder.sizeInFloats());
  }

}

