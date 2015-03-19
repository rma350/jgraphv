package com.google.rma350.jgraphv.desktop.integration;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.github.rma350.jgraphv.core.CheckedGL;
import com.github.rma350.jgraphv.core.DirectedArrowBuffer;
import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.desktop.coredeps.LwjGL;

public class DirectedArrowBufferTest {
  
 private GL gl;
 private LinMath math;
  
  @Before
  public void setup() throws LWJGLException{
    Display.setDisplayMode(new DisplayMode(100,100));
    Display.create();
    gl = new CheckedGL(new LwjGL());
    math = gl.getLinMath();
  }
  
  @After
  public void tearDown(){
    gl = null;
    math = null;
    Display.destroy();
  }
  
  private static float tolerance = .000001f;

  @Test
  public void testSingleArrow() {
    DirectedArrowBuffer arrowBuffer = new DirectedArrowBuffer(gl, 1);
    float arrowWidth = 10;
    float arrowHeadWidth = 15;
    float arrowHeadLength = 30;
    arrowBuffer.setArrow(0, math.createVec2(100, 100),
        math.createVec2(300, 300), arrowWidth, arrowHeadWidth, arrowHeadLength);
    float[] buf = arrowBuffer.getBuffer();
    // num floats = floats per point * points per triangle * triangles per arrow
    //            = 2 * 3 * 3
    //            = 18
    assertEquals(18, buf.length);
    System.out.println(Arrays.toString(buf));
  }

}
