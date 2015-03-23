package com.github.rma350.jgraphv.core.portable.impl;

import com.github.rma350.jgraphv.core.portable.Mat4;
import com.github.rma350.jgraphv.core.portable.Vec4;

public class TestLinMath extends JLinMath {

  @Override
  public Mat4 createMat4() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Vec4 createVec4(float x, float y, float z, float w) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setIdentity(Mat4 mat) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setOrtho2D(Mat4 mat, float left, float right, float bottom,
      float top) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void scale(Mat4 mat, float scaleX, float scaleY) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void translate(Mat4 mat, float dX, float dY) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mat4 invert(Mat4 mat) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Vec4 mult(Mat4 mat, Vec4 vec) {
    throw new UnsupportedOperationException();
  }

}
