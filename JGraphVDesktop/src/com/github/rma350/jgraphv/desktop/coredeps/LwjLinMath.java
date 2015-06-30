package com.github.rma350.jgraphv.desktop.coredeps;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Mat4;
import com.github.rma350.jgraphv.core.portable.Vec2;
import com.github.rma350.jgraphv.core.portable.Vec4;

public class LwjLinMath implements LinMath{

  @Override
  public LwjMat4 createMat4() {
    return new LwjMat4();
  }

  @Override
  public LwjVec4 createVec4(float x, float y, float z, float w) {
    return new LwjVec4(x,y,z,w);
  }

  @Override
  public void setIdentity(Mat4 mat) {
    Matrix4f.setIdentity(nMat(mat));
  }

  @Override
  public void setOrtho2D(Mat4 mat, float left, float right, float bottom,
      float top) {
    float width = right - left;
    float height = top - bottom;
    Matrix4f matrix = nMat(mat);
    Matrix4f.setIdentity(matrix);
    matrix.scale(new Vector3f(2/width, 2/height, 1));
    matrix.translate(new Vector3f(-(left + right)/2, -(top+bottom)/2, 1));
  }

  @Override
  public void scale(Mat4 mat, float scaleX, float scaleY) {
    nMat(mat).scale(new Vector3f(scaleX, scaleY, 1));
  }

  @Override
  public void translate(Mat4 mat, float dX, float dY) {
    nMat(mat).translate(new Vector3f(dX, dY, 0));
  }

  @Override
  public Mat4 invert(Mat4 mat) {
    LwjMat4 ans = new LwjMat4();
    Matrix4f.invert(nMat(mat), ans.getMatrix());
    return ans;
  }

  @Override
  public Vec4 mult(Mat4 mat, Vec4 vec) {
    LwjVec4 ans = new LwjVec4();
    Matrix4f.transform(nMat(mat), nVec4(vec), ans.getVec());
    return ans;
  }
  
  
  private static Matrix4f nMat(Mat4 mat){
    return ((LwjMat4)mat).getMatrix();
  }
  
  private static Vector4f nVec4(Vec4 vec){
    return ((LwjVec4)vec).getVec();
  }
  
  private static Vector2f nVec2(Vec2 vec){
    return ((LwjVec2)vec).getVec();
  }

  @Override
  public LwjVec2 createVec2() {
    return new LwjVec2();
  }

  @Override
  public LwjVec2 createVec2(float x, float y) {
    return new LwjVec2(x,y);
  }
  
  @Override
  public LwjVec2 createVec2(Vec2 other) {
    return new LwjVec2(nVec2(other));
  }

  @Override
  public void add(Vec2 out, Vec2 a, Vec2 b) {
    Vector2f.add(nVec2(a), nVec2(b), nVec2(out));
  }

  @Override
  public void subtract(Vec2 out, Vec2 a, Vec2 b) {
    Vector2f.sub(nVec2(a), nVec2(b), nVec2(out));
  }

  @Override
  public void normalize(Vec2 out, Vec2 a) {
    nVec2(a).normalise(nVec2(out));
  }

  @Override
  public void scaleAndAdd(Vec2 out, Vec2 a, Vec2 b, float scale) {
    Vector2f nOut = nVec2(out);
    nOut.x = a.x() + scale*b.x();
    nOut.y = a.y() + scale*b.y();
  }

  @Override
  public float norm(Vec2 vec) {
    return nVec2(vec).length();
  }

  @Override
  public void scale(Vec2 out, Vec2 a, float scale) {
    Vector2f nOut = nVec2(out);
    Vector2f nA = nVec2(a);
    nOut.x = scale*nA.x;
    nOut.y = scale*nA.y;
  }

  @Override
  public void lerp(Vec2 out, Vec2 a, Vec2 b, float t) {
    scale(out,a,1-t);
    scaleAndAdd(out,out,b,t);
  }

  @Override
  public float distSquared(Vec2 a, Vec2 b) {
    Vector2f nA = nVec2(a);
    Vector2f nB = nVec2(b);
    float dx = nB.x - nA.x;
    float dy = nB.y - nA.y;
    return dx*dx + dy*dy;
  }

  @Override
  public float dist(Vec2 a, Vec2 b) {
    return (float)Math.sqrt(distSquared(a,b));
  }

  

}
