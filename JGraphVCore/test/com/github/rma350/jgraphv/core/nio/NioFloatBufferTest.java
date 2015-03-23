package com.github.rma350.jgraphv.core.nio;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.rma350.jgraphv.core.nio.NioFloatBuffer;

public class NioFloatBufferTest {

  @Test
  public void testInit() {
    NioFloatBuffer buffer = new NioFloatBuffer(4);
    assertEquals(0,buffer.getPositionInBytes());
    assertEquals(0,buffer.getPositionInFloats());    
  }
  
  

}
