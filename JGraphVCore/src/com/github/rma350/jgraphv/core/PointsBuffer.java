package com.github.rma350.jgraphv.core;


import com.github.rma350.jgraphv.core.portable.GL;

public class PointsBuffer extends NativeShapeBuffer{

	public PointsBuffer(GL gl, float[] vertexData) {
	  super(gl, 3, 1, vertexData.length/3);
	  validateFloatAlignment(vertexData.length);
	  this.checkedPut(vertexData, 0);
	}
	
}
