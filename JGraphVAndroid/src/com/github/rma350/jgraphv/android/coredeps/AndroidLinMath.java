package com.github.rma350.jgraphv.android.coredeps;

import android.opengl.Matrix;

import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Mat4;
import com.github.rma350.jgraphv.core.portable.Vec4;
import com.github.rma350.jgraphv.core.portable.impl.JLinMath;

public class AndroidLinMath extends JLinMath implements LinMath {
  
  private static float[] data(Mat4 mat){
    return ((AndroidMat4)mat).getData();
  }
  
  private static float[] data(Vec4 vec){
    return ((AndroidVec4)vec).getData();
  }

  @Override
  public AndroidMat4 createMat4() {
    AndroidMat4 ans = new AndroidMat4();
    this.setIdentity(ans);
    return ans;
  }

  @Override
  public AndroidVec4 createVec4(float x, float y, float z, float w) {
    return new AndroidVec4(x,y,z,w);
  }

  @Override
  public AndroidMat4 invert(Mat4 mat) {
    AndroidMat4 ans = new AndroidMat4();
    Matrix.invertM(data(ans), 0, data(mat), 0);
    return ans;
  }

  @Override
  public AndroidVec4 mult(Mat4 mat, Vec4 vec) {
    AndroidVec4 ans = new AndroidVec4();
    Matrix.multiplyMV(ans.getData(), 0, data(mat), 0, data(vec), 0);
    return ans;
  }

  @Override
  public void scale(Mat4 mat, float scaleX, float scaleY) {
    Matrix.scaleM(data(mat), 0, scaleX, scaleY, 1);
  }

  @Override
  public void setIdentity(Mat4 mat) {
    Matrix.setIdentityM(data(mat), 0);
  }

  @Override
  public void setOrtho2D(Mat4 mat, float left, float right, float bottom, float top) {
    Matrix.orthoM(data(mat), 0, left, right, bottom, top, -1, 1);
  }

  @Override
  public void translate(Mat4 mat, float dX, float dY) {
    Matrix.translateM(data(mat), 0, dX, dY, 0);
  } 

}
