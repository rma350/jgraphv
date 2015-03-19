package com.github.rma350.jgraphv.desktop.coredeps;

import org.lwjgl.util.vector.Matrix4f;

import com.github.rma350.jgraphv.core.portable.Mat4;

public class LwjMat4 implements Mat4{
  
  private Matrix4f matrix;
  
  public LwjMat4(){
    matrix = new Matrix4f();
  }
  
  public Matrix4f getMatrix(){
    return matrix;
  }

}
