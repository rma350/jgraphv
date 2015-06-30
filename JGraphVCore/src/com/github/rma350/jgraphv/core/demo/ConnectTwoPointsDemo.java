package com.github.rma350.jgraphv.core.demo;

import com.github.rma350.jgraphv.core.engine.Engine;
import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.shapes.Circles;
import com.github.rma350.jgraphv.core.shapes.CirclesBuffer;
import com.github.rma350.jgraphv.core.shapes.DirectedArrowBuffer;
import com.github.rma350.jgraphv.core.shapes.DirectedLines;
import com.github.rma350.jgraphv.core.shapes.Lines;
import com.github.rma350.jgraphv.core.shapes.LinesBuffer;
import com.github.rma350.jgraphv.core.shapes.NativeShapeBuffer.BufferUsage;

public class ConnectTwoPointsDemo extends Demo {

  private BooleanParam arrowHeads;

  public ConnectTwoPointsDemo() {
    super("Connect two points");
    arrowHeads = new BooleanParam("Use arrow heads", true);
  }

  @Override
  protected void setup(Engine engine) {
    LinMath math = engine.getGL().getLinMath();
    if (arrowHeads.getValue()) {
      DirectedArrowBuffer arrowBuffer = new DirectedArrowBuffer(engine.getGL(),
          2, BufferUsage.STATIC);
      float arrowWidth = 5;
      float arrowHeadWidth = 15;
      float arrowHeadLength = 30;
      arrowBuffer.setArrow(0, math.createVec2(10, 20),
          math.createVec2(400, 200), arrowWidth, arrowHeadWidth,
          arrowHeadLength, 10, 30);
      arrowBuffer.setArrow(1, math.createVec2(200, 400),
          math.createVec2(400, 200), arrowWidth, arrowHeadWidth,
          arrowHeadLength, 15, 30);
      arrowBuffer.writeToNative();
      DirectedLines directedLines = new DirectedLines(engine.getCamera(),
          engine.getTriangleShader(), arrowBuffer.getTriangles(), 1, 0, 0, 1);
      engine.getScene().addToScene(directedLines);
    } else {
      LinesBuffer lineData = new LinesBuffer(engine.getGL(), 2,
          BufferUsage.STATIC);
      float[] buffer = LinesBuffer.makeBuffer(2);
      LinesBuffer.setLine(buffer, 0, 10, 20, 400, 200);
      LinesBuffer.setLine(buffer, 1, 200, 400, 400, 200);
      lineData.checkedPutShapes(buffer);
      Lines lines = new Lines(engine.getCamera(), engine.getLinesShader(),
          lineData, 1, 0, 0, 1, 10);
      engine.getScene().addToScene(lines);
    }

    CirclesBuffer circles = new CirclesBuffer(engine.getGL(), 3,
        BufferUsage.STATIC);
    float[] circleBuffer = CirclesBuffer.makeBuffer(3);
    CirclesBuffer.setCircle(circleBuffer, 0, 10, 20, 10);
    CirclesBuffer.setCircle(circleBuffer, 1, 200, 400, 15);
    CirclesBuffer.setCircle(circleBuffer, 2, 400, 200, 30);
    circles.checkedPutShapes(circleBuffer);

    Circles circs = new Circles(engine.getCamera(), engine.getCirclesShader(),
        circles, 0, 0, 1, 1);
    engine.getScene().addToScene(circs);

  }

}
