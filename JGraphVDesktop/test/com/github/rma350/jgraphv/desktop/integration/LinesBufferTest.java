package com.github.rma350.jgraphv.desktop.integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import com.github.rma350.jgraphv.core.engine.CheckedGL;
import com.github.rma350.jgraphv.core.nio.NioFloatBuffer;
import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.shapes.LinesBuffer;
import com.github.rma350.jgraphv.core.shapes.NativeShapeBuffer.BufferUsage;
import com.github.rma350.jgraphv.desktop.coredeps.LwjGL;

public class LinesBufferTest {
  
  
  private GL gl;
  
  @Before
  public void setup() throws LWJGLException{
    Display.setDisplayMode(new DisplayMode(100,100));
    Display.create();
    gl = new CheckedGL(new LwjGL());
    GL11.glEnable(GL20.GL_VERTEX_PROGRAM_POINT_SIZE);
  }
  
  @After
  public void tearDown(){
    gl = null;
    Display.destroy();
  }
  
  private static float tolerance = .000001f;
  
  private static float[] createTwoLines(){
    float[] lines = LinesBuffer.makeBuffer(2);
    LinesBuffer.setLine(lines, 0, 10, 11, 20, 31);
    LinesBuffer.setLine(lines, 1, 100, 101, 200, 301);
    return lines;
  }
  
  private static float[] expectedBufferTwoLines(){
    return new float[]{10, 11, 20,31,100,101,200,301};
  }

  @Test
  public void testJavaBufferCreation() {
    assertArrayEquals(expectedBufferTwoLines(), createTwoLines(), tolerance);
  }
  
  @Test
  public void testNativeBufferInit(){
    LinesBuffer buffer = new LinesBuffer(gl, 3, BufferUsage.STATIC);
    // 3 lines
    assertEquals(3, buffer.getShapeCount());
    // 3 lines each 2 verts
    assertEquals(3 * 2, buffer.getVertexCount());
    // 3 lines, each 2 verts, each 2 floats
    assertEquals(3 * 2 * 2, buffer.getFloatCount());
    // 3 lines, each 2 verts, each 2 floats, each 4 bytes
    assertEquals(3 * 2 * 2 * 4, buffer.getByteCount());
  }
  
  @Test
  public void testNativeBuffer(){
    LinesBuffer buffer = new LinesBuffer(gl, 2, BufferUsage.STATIC);
    buffer.checkedPutShapes(createTwoLines());
    assertEquals(10, buffer.getVertexBuffer().get(0), tolerance);
    assertEquals(11, buffer.getVertexBuffer().get(1), tolerance);
    assertEquals(20, buffer.getVertexBuffer().get(2), tolerance);
    assertEquals(31, buffer.getVertexBuffer().get(3), tolerance);
    assertEquals(100, buffer.getVertexBuffer().get(4), tolerance);
    assertEquals(101, buffer.getVertexBuffer().get(5), tolerance);
    assertEquals(200, buffer.getVertexBuffer().get(6), tolerance);
    assertEquals(301, buffer.getVertexBuffer().get(7), tolerance);
    assertEquals(8,buffer.getVertexBuffer().getSizeInFloats());
    assertEquals(32,buffer.getVertexBuffer().getSizeInBytes());
  }
  
  @Test
  public void testGpuTransfer(){
    LinesBuffer buffer = new LinesBuffer(gl, 2, BufferUsage.STATIC);
    buffer.checkedPutShapes(createTwoLines());
    buffer.bindBuffer();
    buffer.ensureOnGpu();    
    buffer.unBindBuffer();
    
    LinesBuffer readBuffer = new LinesBuffer(gl, 2, BufferUsage.STATIC);
    buffer.bindBuffer();
    GL15.glGetBufferSubData(gl.kGL_ARRAY_BUFFER(), 0, ((NioFloatBuffer)readBuffer.getVertexBuffer()).getBuffer());
    buffer.unBindBuffer();
    
    assertEquals(10, readBuffer.getVertexBuffer().get(0), tolerance);
    assertEquals(11, readBuffer.getVertexBuffer().get(1), tolerance);
    assertEquals(20, readBuffer.getVertexBuffer().get(2), tolerance);
    assertEquals(31, readBuffer.getVertexBuffer().get(3), tolerance);
    assertEquals(100, readBuffer.getVertexBuffer().get(4), tolerance);
    assertEquals(101, readBuffer.getVertexBuffer().get(5), tolerance);
    assertEquals(200, readBuffer.getVertexBuffer().get(6), tolerance);
    assertEquals(301, readBuffer.getVertexBuffer().get(7), tolerance);
  }
  

}
