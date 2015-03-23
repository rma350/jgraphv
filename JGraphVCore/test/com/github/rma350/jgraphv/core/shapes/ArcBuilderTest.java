package com.github.rma350.jgraphv.core.shapes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.impl.TestLinMath;

public class ArcBuilderTest {
  
  private static float TOLERANCE = .000001f;
  
  private LinMath math;

  @Before
  public void setup() {
    math = new TestLinMath();
  }

  @After
  public void tearDown() {
    math = null;
  }

  @Test
  public void testArcBuilder() {
    ArcBuilder builder = new ArcBuilder(math);
    builder.iFrom.set(300, 200);
    builder.iTo.set(400, 200);
    builder.iArcWidth = 20;    
    builder.iBendLeft = true;
    builder.iArcRadians = (float)Math.PI/2;
    
    builder.computeArc();
    
    // Check that the state of the inputs was unchanged.
    assertEquals(300, builder.iFrom.x(), TOLERANCE);
    assertEquals(200, builder.iFrom.y(), TOLERANCE);
    assertEquals(400, builder.iTo.x(), TOLERANCE);
    assertEquals(200, builder.iTo.y(), TOLERANCE);
    assertEquals(20, builder.iArcWidth, TOLERANCE);
    assertEquals((float)Math.PI/2, builder.iArcRadians, TOLERANCE);
    assertTrue(builder.iBendLeft);
    
    // Check the outputs.
    assertEquals(350,builder.oCircleCenter.x(), TOLERANCE);
    assertEquals(150,builder.oCircleCenter.y(), TOLERANCE);
    assertEquals((float)(50*Math.sqrt(2)), builder.oCircleRadius, TOLERANCE);
    
    // Height should be circle center + circle radius + half arc width.
    float expectedHeight = (float)(50*Math.sqrt(2) + 150 + 10); 
    assertEquals(expectedHeight, builder.oFrom.y(), TOLERANCE);
    assertEquals(expectedHeight, builder.oTo.y(), TOLERANCE);
    
    float aboveTriangle = expectedHeight - 200;
    // The triangle is 45 degrees, so the change in y equals the change in x.
    assertEquals(300 - aboveTriangle, builder.oFrom.x(), TOLERANCE);
    assertEquals(400 + aboveTriangle, builder.oTo.x(), TOLERANCE);
  }

}
