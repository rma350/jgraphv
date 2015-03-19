package com.github.rma350.jgraphv.android.coredeps;

import java.nio.FloatBuffer;

import com.github.rma350.jgraphv.core.nio.NioFloatBuffer;
import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.Mat4;
import com.github.rma350.jgraphv.core.portable.NativeFloatBuffer;

import android.opengl.GLES20;
import android.opengl.GLU;



public class AndroidGL implements GL {
  
  private AndroidLinMath mMath = new AndroidLinMath();

  @Override
  public void glClear(int mask) {
    GLES20.glClear(mask);
  }

  @Override
  public void glClearColor(float red, float green, float blue, float alpha) {
    GLES20.glClearColor(red, green, blue, alpha);
  }

  @Override
  public void glViewport(int x, int y, int width, int height) {
    GLES20.glViewport(x, y, width, height);
  }

  @Override
  public int kGL_COLOR_BUFFER_BIT() {
    return GLES20.GL_COLOR_BUFFER_BIT;
  }


  @Override
  public void glAttachShader(int program, int shader) {
    GLES20.glAttachShader(program, shader);
    
  }

  @Override
  public void glCompileShader(int shaderProgram) {
    GLES20.glCompileShader(shaderProgram);
  }

  @Override
  public int glCreateProgram() {
    return GLES20.glCreateProgram();
  }

  @Override
  public int glCreateShader(int shaderType) {
    return GLES20.glCreateShader(shaderType);
  }

  @Override
  public void glDrawArrays(int mode, int first, int count) {
    GLES20.glDrawArrays(mode, first, count);
  }

  @Override
  public void glEnable(int cap) {
    GLES20.glEnable(cap);
  }

  @Override
  public void glEnableVertexAttribArray(int glLocation) {
    GLES20.glEnableVertexAttribArray(glLocation);
  }

  @Override
  public int glGetAttribLocation(int program, String attribute) {
    return GLES20.glGetAttribLocation(program, attribute);
  }

  @Override
  public int glGetUniformLocation(int program, String uniform) {
    return GLES20.glGetUniformLocation(program, uniform);
  }

  @Override
  public void glLinkProgram(int program) {
    GLES20.glLinkProgram(program);
  }

  @Override
  public void glShaderSource(int shaderProgram, String shaderCode) {
    GLES20.glShaderSource(shaderProgram, shaderCode);
  }

  @Override
  public void glUniform4f(int glLocation, float x, float y, float z, float w) {
    GLES20.glUniform4f(glLocation, x, y, z, w);
  }

  @Override
  public void glUniformMatrix4(int glLocation, Mat4 mat) {
    GLES20.glUniformMatrix4fv(glLocation, 1, false, ((AndroidMat4)mat).getData(), 0);
  }

  @Override
  public void glUseProgram(int program) {
    GLES20.glUseProgram(program);
  }

  @Override
  public int kGL_FLOAT() {
    return GLES20.GL_FLOAT;
  }

  @Override
  public int kGL_FRAGMENT_SHADER() {
    return GLES20.GL_FRAGMENT_SHADER;
  }

  @Override
  public int kGL_POINTS() {
    return GLES20.GL_POINTS;
  }

  @Override
  public int kGL_VERTEX_SHADER() {
    return GLES20.GL_VERTEX_SHADER;
  }

  @Override
  public void glBlendFunc(int sfactor, int dfactor) {
    GLES20.glBlendFunc(sfactor, dfactor);
  }

  @Override
  public int kGL_BLEND() {
    return GLES20.GL_BLEND;
  }

  @Override
  public int kGL_ONE_MINUS_SRC_ALPHA() {
    return GLES20.GL_ONE_MINUS_SRC_ALPHA;
  }

  @Override
  public int kGL_SRC_ALPHA() {
    return GLES20.GL_SRC_ALPHA;
  }

  @Override
  public int kGL_TRIANGLES() {
    return GLES20.GL_TRIANGLES; 
  }

  @Override
  public void glLineWidth(float width) {
    GLES20.glLineWidth(width);
  }

  @Override
  public int kGL_LINES() {
    return GLES20.GL_LINES;
  }

  @Override
  public NativeFloatBuffer createFloatBuffer(int numFloats) {
    return new NioFloatBuffer(numFloats);
  }

  @Override
  public void glBindBuffer(int target, int buffer) {
    GLES20.glBindBuffer(target, buffer);
  }

  @Override
  public void glBufferData(int target, NativeFloatBuffer nativeArray, int usage) {
    FloatBuffer floats = ((NioFloatBuffer)nativeArray).getBuffer();
    GLES20.glBufferData(target, 
        //floats.capacity(),
        nativeArray.getSizeInBytes(),
        floats , usage);
  }

  @Override
  public int glCreateBuffer() {
    int[] ans = new int[1];
    GLES20.glGenBuffers(1, ans, 0 );
    return ans[0];
  }

  @Override
  public int glGetError() {
    return GLES20.glGetError();
  }

  @Override
  public String glGetProgramInfoLog(int program) {
    return GLES20.glGetProgramInfoLog(program);
  }

  @Override
  public void glGetProgramiv(int program, int pname, int[] params) {
    GLES20.glGetProgramiv(program, pname, params, 0);
  }

  @Override
  public String glGetShaderInfoLog(int shader) {
    return GLES20.glGetShaderInfoLog(shader);
  }

  @Override
  public void glGetShaderiv(int shader, int pname, int[] params) {
    GLES20.glGetShaderiv(shader, pname, params, 0);
  }

  @Override
  public void glValidateProgram(int program) {
    GLES20.glValidateProgram(program);
  }

  @Override
  public void glVertexAttribPointer(int index, int size, boolean normalized, int stride,
      int offset) {
    GLES20.glVertexAttribPointer(index, size, GLES20.GL_FLOAT, normalized, stride, offset);
  }

  @Override
  public String gluErrorString(int errorCode) {
    return GLU.gluErrorString(errorCode);
  }

  @Override
  public int kGL_ARRAY_BUFFER() {
    return GLES20.GL_ARRAY_BUFFER;
  }

  @Override
  public int kGL_COMPILE_STATUS() {
    return GLES20.GL_COMPILE_STATUS;
  }

  @Override
  public int kGL_DYNAMIC_DRAW() {
    return GLES20.GL_DYNAMIC_DRAW;
  }

  @Override
  public int kGL_LINK_STATUS() {
    return GLES20.GL_LINK_STATUS;
  }

  @Override
  public int kGL_NO_ERROR() {
    return GLES20.GL_NO_ERROR;
  }

  @Override
  public int kGL_STATIC_DRAW() {
    return GLES20.GL_STATIC_DRAW;
  }

  @Override
  public int kGL_VALIDATE_STATUS() {
    return GLES20.GL_VALIDATE_STATUS;
  }

  @Override
  public AndroidLinMath getLinMath() {
    return mMath;
  }

}
