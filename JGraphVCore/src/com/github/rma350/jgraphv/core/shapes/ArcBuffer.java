package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.portable.GL;

public class ArcBuffer extends NativeShapeBuffer {

  public ArcBuffer(GL gl, ArcBufferBuilder arcBufferBuilder) {
    super(gl, ArcMemLayout.VERTEX_SIZE, 3, arcBufferBuilder.sizeInTriangles());
    this.checkedPut(arcBufferBuilder.getBuffer(), 0,
        arcBufferBuilder.sizeInFloats());
  }

}

