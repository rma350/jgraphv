package com.github.rma350.jgraphv.desktop.coredeps;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.rma350.jgraphv.desktop.coredeps.LwjLinMath;
import com.github.rma350.jgraphv.desktop.coredeps.LwjVec2;

public class LwjLinMathTest {
  
  private static final float TOLERANCE = .000001f;

  @Test
  public void testVec2Add() {
    LwjLinMath math = new LwjLinMath();
    LwjVec2 a = math.createVec2(10,20);
    LwjVec2 b = math.createVec2(100,200);
    LwjVec2 out = math.createVec2();
    math.add(out, a, b);
    assertEquals(110,out.x(), TOLERANCE);
    assertEquals(220,out.y(), TOLERANCE);
  }
  
  @Test
  public void testVec2Subtract() {
    LwjLinMath math = new LwjLinMath();
    LwjVec2 a = math.createVec2(10,20);
    LwjVec2 b = math.createVec2(100,200);
    LwjVec2 out = math.createVec2();
    math.subtract(out, a, b);
    assertEquals(-90,out.x(), TOLERANCE);
    assertEquals(-180,out.y(), TOLERANCE);
  }
  
  @Test
  public void testVec2Normalize() {
    LwjLinMath math = new LwjLinMath();
    LwjVec2 a = math.createVec2(3,4);
    LwjVec2 out = math.createVec2();
    math.normalize(out, a);
    assertEquals(.6,out.x(), TOLERANCE);
    assertEquals(.8,out.y(), TOLERANCE);
  }
  
  @Test
  public void testVec2ScaleAndAdd() {
    LwjLinMath math = new LwjLinMath();
    LwjVec2 a = math.createVec2(10,20);
    LwjVec2 b = math.createVec2(100,200);
    LwjVec2 out = math.createVec2();
    math.scaleAndAdd(out, a, b, 3);
    assertEquals(310,out.x(), TOLERANCE);
    assertEquals(620,out.y(), TOLERANCE);
  }

}
