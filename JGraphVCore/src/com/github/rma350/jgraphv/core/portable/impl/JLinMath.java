package com.github.rma350.jgraphv.core.portable.impl;

import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Vec2;


public abstract class JLinMath implements LinMath{
  
  private static float[] data(Vec2 vec){
    return ((JVec2)vec).getData();
  }
  
  @Override
  public void add(Vec2 out, Vec2 a, Vec2 b) {
    float[] od = data(out);
    float[] ad = data(a);
    float[] bd = data(b);
    od[0] = ad[0] + bd[0];
    od[1] = ad[1] + bd[1];
  }

  @Override
  public Vec2 createVec2() {
    return new JVec2();
  }

  @Override
  public Vec2 createVec2(float x, float y) {
    return new JVec2(x,y);
  }

  @Override
  public float norm(Vec2 vec) {
    float[] v = data(vec);
    return (float)Math.sqrt(v[0]*v[0] + v[1]*v[1]);
  }

  @Override
  public void normalize(Vec2 out, Vec2 a) {
    float n = norm(a);
    float[] o = data(out);
    o[0] = a.x()/n;
    o[1] = a.y()/n;
  }

  @Override
  public void scaleAndAdd(Vec2 out, Vec2 a, Vec2 b, float scale) {
    float[] od = data(out);
    float[] ad = data(a);
    float[] bd = data(b);
    od[0] = ad[0] + scale*bd[0];
    od[1] = ad[1] + scale*bd[1];
  }

  @Override
  public void subtract(Vec2 out, Vec2 a, Vec2 b) {
    float[] od = data(out);
    float[] ad = data(a);
    float[] bd = data(b);
    od[0] = ad[0] - bd[0];
    od[1] = ad[1] - bd[1];
  }
  
  public void scale(Vec2 out, Vec2 a, float scale) {
    float[] od = data(out);
    float[] ad = data(a);
    od[0] = scale*ad[0];
    od[1] = scale*ad[1];
  }

}
