package com.github.rma350.jgraphv.core.demo;

import com.github.rma350.jgraphv.core.engine.Engine;
import com.github.rma350.jgraphv.core.shapes.ArcBuffer;
import com.github.rma350.jgraphv.core.shapes.ArcBufferBuilder;
import com.github.rma350.jgraphv.core.shapes.ArcBuilder;
import com.github.rma350.jgraphv.core.shapes.Arcs;
import com.github.rma350.jgraphv.core.shapes.Points;
import com.github.rma350.jgraphv.core.shapes.PointsBuffer;
import com.github.rma350.jgraphv.core.shapes.NativeShapeBuffer.BufferUsage;

public class ArcDemo extends Demo {

  private FloatParam arcWidth;

  public ArcDemo() {
    super("Arc demo");
    arcWidth = new FloatParam("Arc Width", 3);
  }
  
  @Override
  protected void setup(Engine engine) {
    ArcBuilder arcBuilder = new ArcBuilder(engine.getGL().getLinMath());
    arcBuilder.iFrom.set(10,20);
    arcBuilder.iTo.set(100,20);
    arcBuilder.iArcWidth = arcWidth.getValue();
    arcBuilder.computeArc();
    ArcBufferBuilder bufferBuilder = new ArcBufferBuilder(1);
    bufferBuilder.appendArc(arcBuilder);
    ArcBuffer arcsBuffer = new ArcBuffer(engine.getGL(), bufferBuilder, BufferUsage.STATIC);
    Arcs arcs = new Arcs(engine.getCamera(), engine.getArcShader(), arcsBuffer, 1, 0, 0, 1);
    engine.getScene().addToScene(arcs);
    
    PointsBuffer points = new PointsBuffer(
        engine.getGL(), new float[] { 10, 20, 10, 100, 20, 10 }, BufferUsage.STATIC);
    engine.getScene().addToScene(
        new Points(engine.getCamera(), engine.getPointsShader(),
            points, 0, 1, 0, 1));    
  }

}
