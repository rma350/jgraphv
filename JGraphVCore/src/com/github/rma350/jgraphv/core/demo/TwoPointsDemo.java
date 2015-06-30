package com.github.rma350.jgraphv.core.demo;

import com.github.rma350.jgraphv.core.engine.Engine;
import com.github.rma350.jgraphv.core.shapes.Points;
import com.github.rma350.jgraphv.core.shapes.PointsBuffer;
import com.github.rma350.jgraphv.core.shapes.NativeShapeBuffer.BufferUsage;

public class TwoPointsDemo extends Demo{

  public TwoPointsDemo() {
    super("Two points");
  }

  @Override
  protected void setup(Engine engine) {
    PointsBuffer points = new PointsBuffer(
        engine.getGL(), new float[] { 10, 20, 10, 400, 200, 15 }, BufferUsage.STATIC);
    engine.getScene().addToScene(
        new Points(engine.getCamera(), engine.getPointsShader(),
            points, 0, 1, 0, 1));
  }

}
