package com.github.rma350.jgraphv.core.shapes;


import com.github.rma350.jgraphv.core.portable.GL;

public class PointsBuffer extends NativeShapeBuffer{

	public PointsBuffer(GL gl, float[] vertexData, BufferUsage bufferUsage) {
	  super(gl, 3, 1, vertexData.length/3, bufferUsage);
	  validateFloatAlignment(vertexData.length);
	  this.checkedPut(vertexData);
	}
	
}
