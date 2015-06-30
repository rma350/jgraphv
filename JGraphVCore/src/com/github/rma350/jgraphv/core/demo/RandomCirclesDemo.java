package com.github.rma350.jgraphv.core.demo;

import com.github.rma350.jgraphv.core.engine.Engine;
import com.github.rma350.jgraphv.core.shapes.Circles;
import com.github.rma350.jgraphv.core.shapes.CirclesBuffer;
import com.github.rma350.jgraphv.core.shapes.NativeShapeBuffer.BufferUsage;

public class RandomCirclesDemo extends Demo {
  
  private IntParam numCirclesParam;

  public RandomCirclesDemo() {
    super("Random Circles");
    numCirclesParam = this.addIntParam("Number of circles", 1000);
  }

  @Override
  protected void setup(Engine engine) {
    int numCircles = numCirclesParam.getValue();
    CirclesBuffer circles = new CirclesBuffer(
        engine.getGL(), numCircles, BufferUsage.STATIC);
    float[] buffer = CirclesBuffer.makeBuffer(numCircles);
    for (int i = 0; i < numCircles; i++) {
      CirclesBuffer.setCircle(buffer, i, (float) Math.random() * 2000,
          (float) Math.random() * 2000, (float) Math.random() * 20);
    }

    circles.checkedPutShapes(buffer);

    Circles circs = new Circles(engine.getCamera(),
        engine.getCirclesShader(), circles, 0, 0, 1, 1);

    engine.getScene().addToScene(circs);
  }

}
