package com.github.rma350.jgraphv.core;

import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Mat4;
import com.github.rma350.jgraphv.core.portable.NativeFloatBuffer;

public class CheckedGL implements GL {

  private GL delegate;
  private boolean debugMode;
  
  public CheckedGL(GL delegate){
    this.delegate = delegate;
    this.debugMode = true;
  }
  
  public void setDebugModeOn(boolean debugModeIsOn){
    this.debugMode = debugModeIsOn;
  }
  
  public void assertGLState() {
    int errorCode = delegate.glGetError();
    if(errorCode != delegate.kGL_NO_ERROR()){
      throw new RuntimeException(delegate.gluErrorString(errorCode));
    }
  }
  
  public void debugAssertGLState(){
    if(debugMode){
      assertGLState();
    }
  }

  @Override
  public NativeFloatBuffer createFloatBuffer(int numFloats) {
    return delegate.createFloatBuffer(numFloats);
  }


  @Override
  public void glAttachShader(int program, int shader) {
    delegate.glAttachShader(program, shader);
    debugAssertGLState();
  }

  @Override
  public void glBlendFunc(int sfactor, int dfactor) {
    delegate.glBlendFunc(sfactor, dfactor);
    debugAssertGLState();
  }

  @Override
  public void glClear(int mask) {
    delegate.glClear(mask);
    debugAssertGLState();
  }

  @Override
  public void glClearColor(float red, float green, float blue, float alpha) {
    delegate.glClearColor(red, green, blue, alpha);
    debugAssertGLState();
  }

  @Override
  public void glCompileShader(int shaderProgram) {
    delegate.glCompileShader(shaderProgram);
    debugAssertGLState();
  }

  @Override
  public int glCreateProgram() {
    int ans = delegate.glCreateProgram();
    debugAssertGLState();
    return ans;
  }

  @Override
  public int glCreateShader(int shaderType) {
    int ans = delegate.glCreateShader(shaderType);
    debugAssertGLState();
    return ans;
  }

  @Override
  public void glDrawArrays(int mode, int first, int count) {
    delegate.glDrawArrays(mode, first, count);
    debugAssertGLState();
  }

  @Override
  public void glEnable(int cap) {
    delegate.glEnable(cap);
    debugAssertGLState();
  }

  @Override
  public void glEnableVertexAttribArray(int glLocation) {
    delegate.glEnableVertexAttribArray(glLocation);
    debugAssertGLState();
  }

  @Override
  public int glGetAttribLocation(int program, String attribute) {
    int ans = delegate.glGetAttribLocation(program, attribute);
    debugAssertGLState();
    return ans;
  }

  // Note, we do note want to debugAssertGLState here, because this method is already for error checking.
  @Override
  public int glGetError() {
    return delegate.glGetError();
  }

  @Override
  public int glGetUniformLocation(int program, String uniform) {
    int ans = delegate.glGetUniformLocation(program, uniform);
    debugAssertGLState();
    return ans;
  }

  @Override
  public void glLineWidth(float width) {
    delegate.glLineWidth(width);
    debugAssertGLState();
  }

  @Override
  public void glLinkProgram(int program) {
    delegate.glLinkProgram(program);
    debugAssertGLState();
  }

  @Override
  public void glShaderSource(int shaderProgram, String shaderCode) {
    delegate.glShaderSource(shaderProgram, shaderCode);
    debugAssertGLState();
  }

  // Note, we do note want to debugAssertGLState here, because this method is already for error checking.
  @Override
  public String gluErrorString(int errorCode) {
    return delegate.gluErrorString(errorCode);
  }

  @Override
  public void glUniform4f(int glLocation, float x, float y, float z, float w) {
    delegate.glUniform4f(glLocation, x, y, z, w);
    debugAssertGLState();
  }

  @Override
  public void glUniformMatrix4(int glLocation, Mat4 mat) {
    delegate.glUniformMatrix4(glLocation, mat);
    debugAssertGLState();    
  }

  @Override
  public void glUseProgram(int program) {
    delegate.glUseProgram(program);
    debugAssertGLState();
  }

  @Override
  public void glVertexAttribPointer(int glLocation, int size,
      boolean normalized, int stride, int offset) {
    delegate.glVertexAttribPointer(glLocation, size, normalized, stride, offset);
    debugAssertGLState();
  }

  @Override
  public int glCreateBuffer() {
    int ans = delegate.glCreateBuffer();
    debugAssertGLState();
    return ans;
  }

  @Override
  public void glBufferData(int target, NativeFloatBuffer nativeArray, int usage) {
    delegate.glBufferData(target, nativeArray, usage);
    debugAssertGLState();
  }

  @Override
  public void glBindBuffer(int target, int buffer) {
    delegate.glBindBuffer(target, buffer);
    debugAssertGLState();
  }

  @Override
  public int kGL_ARRAY_BUFFER() {
    return delegate.kGL_ARRAY_BUFFER();
  }

  @Override
  public void glViewport(int x, int y, int width, int height) {
    delegate.glViewport(x, y, width, height);
    debugAssertGLState();
  }

  @Override
  public int kGL_BLEND() {
    return delegate.kGL_BLEND();
  }

  @Override
  public int kGL_DYNAMIC_DRAW() {
    return delegate.kGL_DYNAMIC_DRAW();
  }

  @Override
  public int kGL_SRC_ALPHA() {
    return delegate.kGL_SRC_ALPHA();
  }

  @Override
  public int kGL_ONE_MINUS_SRC_ALPHA() {
    return delegate.kGL_ONE_MINUS_SRC_ALPHA();
  }

  @Override
  public int kGL_COLOR_BUFFER_BIT() {
    return delegate.kGL_COLOR_BUFFER_BIT();
  }

  @Override
  public int kGL_FLOAT() {
    return delegate.kGL_FLOAT();
  }

  @Override
  public int kGL_FRAGMENT_SHADER() {
    return delegate.kGL_FRAGMENT_SHADER();
  }

  @Override
  public int kGL_LINES() {
    return delegate.kGL_LINES();
  }

  @Override
  public int kGL_NO_ERROR() {
    return delegate.kGL_NO_ERROR();
  }

  @Override
  public int kGL_POINTS() {
    return delegate.kGL_POINTS();
  }

  @Override
  public int kGL_STATIC_DRAW() {
    return delegate.kGL_STATIC_DRAW();
  }

  @Override
  public int kGL_TRIANGLES() {
    return delegate.kGL_TRIANGLES();
  }

  @Override
  public int kGL_VERTEX_SHADER() {
    return delegate.kGL_VERTEX_SHADER();
  }

 

  @Override
  public String glGetProgramInfoLog(int program) {
    return delegate.glGetProgramInfoLog(program);
  }

  @Override
  public void glGetProgramiv(int program, int pname, int[] params) {
    delegate.glGetProgramiv(program, pname, params);
    debugAssertGLState();
  }

  @Override
  public void glGetShaderiv(int shader, int pname, int[] params) {
    delegate.glGetShaderiv(shader, pname, params);
    debugAssertGLState();
  }

  @Override
  public String glGetShaderInfoLog(int shader) {
    String ans = delegate.glGetShaderInfoLog(shader);
    debugAssertGLState();
    return ans;
  }

  @Override
  public void glValidateProgram(int program) {
    delegate.glValidateProgram(program);
    debugAssertGLState();
  }

  @Override
  public int kGL_COMPILE_STATUS() {
    return delegate.kGL_COMPILE_STATUS();
  }

  @Override
  public int kGL_LINK_STATUS() {
    return delegate.kGL_LINK_STATUS();
  }

  @Override
  public int kGL_VALIDATE_STATUS() {
    return delegate.kGL_VALIDATE_STATUS();
  }

  @Override
  public LinMath getLinMath() {
    return delegate.getLinMath();
  }

}
