package com.github.rma350.jgraphv.desktop.coredeps;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.BufferUtils;

import com.github.rma350.jgraphv.core.nio.NioFloatBuffer;
import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Mat4;
import com.github.rma350.jgraphv.core.portable.NativeFloatBuffer;

public class LwjGL implements GL{
  

  private FloatBuffer mat4buffer = BufferUtils.createFloatBuffer(16);
  private LinMath linMath = new LwjLinMath();
  
  

	@Override
  public NativeFloatBuffer createFloatBuffer(int numFloats) {
    return new NioFloatBuffer(numFloats);
  }


	@Override
  public void glAttachShader(int program, int shader) {
    GL20.glAttachShader(program, shader);
  }
	

  @Override
  public void glBindBuffer(int target, int buffer) {
    GL15.glBindBuffer(target, buffer);
  }

  @Override
  public void glBlendFunc(int sfactor, int dfactor) {
    GL11.glBlendFunc(sfactor, dfactor);
  }

  @Override
  public void glBufferData(int target, NativeFloatBuffer nativeArray, int usage) {
    GL15.glBufferData(target, ((NioFloatBuffer)nativeArray).getBuffer(), usage);
  }
  
  @Override
  public void glBufferSubData(int target, long offset, NativeFloatBuffer nativeArray){
    GL15.glBufferSubData(target, offset, ((NioFloatBuffer)nativeArray).getBuffer());
  }

  @Override
	public void glClear(int mask) {
		GL11.glClear(mask);
	}
  
  @Override
	public void glClearColor(float red, float green, float blue, float alpha) {
		GL11.glClearColor(red, green, blue, alpha);
	}

  @Override
  public void glCompileShader(int shaderProgram) {
    GL20.glCompileShader(shaderProgram);
  }

  @Override
  public int glCreateBuffer() {
    return GL15.glGenBuffers();
  }

  

  @Override
  public int glCreateProgram() {
    return GL20.glCreateProgram();
  }

  @Override
  public int glCreateShader(int shaderType) {
    return GL20.glCreateShader(shaderType);
  }

  @Override
  public void glDrawArrays(int mode, int first, int count) {
    GL11.glDrawArrays(mode, first, count);
  }

  @Override
  public void glEnable(int cap) {
    GL11.glEnable(cap);
  }

  @Override
  public void glEnableVertexAttribArray(int glLocation) {
    GL20.glEnableVertexAttribArray(glLocation);
  }

  @Override
  public int glGetAttribLocation(int program, String attribute) {
    return GL20.glGetAttribLocation(program, attribute);
  }

  @Override
  public int glGetError() {
    return GL11.glGetError();
  }

  @Override
  public String glGetProgramInfoLog(int program) {
    int length = GL20.glGetProgrami(program, GL20.GL_INFO_LOG_LENGTH);
    return GL20.glGetProgramInfoLog(program, length);
  }

  @Override
  public void glGetProgramiv(int program, int pname, int[] params) {
    params[0] = GL20.glGetProgrami(program, pname);
  }

  @Override
  public String glGetShaderInfoLog(int shader) {
    int length = GL20.glGetShaderi(shader, GL20.GL_INFO_LOG_LENGTH);
    return GL20.glGetProgramInfoLog(shader, length);
  }

  @Override
  public void glGetShaderiv(int shader, int pname, int[] params) {
    params[0] = GL20.glGetShaderi(shader, GL20.GL_INFO_LOG_LENGTH);
    
  }
  
  @Override
  public int glGetUniformLocation(int program, String uniform) {
    return GL20.glGetUniformLocation(program, uniform);
  }

  @Override
  public void glLineWidth(float width) {
    GL11.glLineWidth(width);
  }

  @Override
  public void glLinkProgram(int program) {
    GL20.glLinkProgram(program);
    GL20.glValidateProgram(program);
  }

  @Override
  public void glShaderSource(int shaderProgram, String shaderCode) {
    GL20.glShaderSource(shaderProgram, shaderCode);
    
  }

  @Override
  public String gluErrorString(int errorCode) {
    return GLU.gluErrorString(errorCode);
  }  
  
  @Override
  public void glUniform4f(int glLocation, float x, float y, float z, float w) {
    GL20.glUniform4f(glLocation, x, y, z, w);
  }

  @Override
  public void glUniformMatrix4(int glLocation, Mat4 mat) {
    ((LwjMat4)mat).getMatrix().store(mat4buffer);
    mat4buffer.flip();
    GL20.glUniformMatrix4(glLocation, false, mat4buffer);
  }

  @Override
  public void glUseProgram(int program) {
    GL20.glUseProgram(program);
  }

  @Override
  public void glValidateProgram(int program) {
    GL20.glValidateProgram(program);
  }

  @Override
  public void glVertexAttribPointer(int glLocation, int size,
      boolean normalized, int stride, int offset) {
    GL20.glVertexAttribPointer(glLocation, size, GL11.GL_FLOAT, normalized, stride, offset);
  }

  @Override
	public void glViewport(int x, int y, int width, int height) {
		GL11.glViewport(x, y, width, height);
	}

  @Override
  public int kGL_ARRAY_BUFFER() {
    return GL15.GL_ARRAY_BUFFER;
  }

  @Override
  public int kGL_BLEND(){
    return GL11.GL_BLEND;
  }

  @Override
	public int kGL_COLOR_BUFFER_BIT() {
		return GL11.GL_COLOR_BUFFER_BIT;
	}

  @Override
  public int kGL_COMPILE_STATUS() {
    return GL20.GL_COMPILE_STATUS;
  }
  
  @Override
  public int kGL_DYNAMIC_DRAW() {
    return GL15.GL_DYNAMIC_DRAW;
  }

  @Override
  public int kGL_FLOAT() {
    return GL11.GL_FLOAT;
  }

  @Override
  public int kGL_FRAGMENT_SHADER() {
    return GL20.GL_FRAGMENT_SHADER;
  }

  @Override
  public int kGL_LINES() {
    return GL11.GL_LINES;
  }

  @Override
  public int kGL_LINK_STATUS() {
    return GL20.GL_LINK_STATUS;
  }

  @Override
  public int kGL_NO_ERROR() {
    return GL11.GL_NO_ERROR;
  }

  @Override
  public int kGL_ONE_MINUS_SRC_ALPHA() {
    return GL11.GL_ONE_MINUS_SRC_ALPHA;
  }

  @Override
  public int kGL_POINTS() {
    return GL11.GL_POINTS;
  }

  @Override
  public int kGL_SRC_ALPHA() {
    return GL11.GL_SRC_ALPHA;
  }

  @Override
  public int kGL_STATIC_DRAW() {
    return GL15.GL_STATIC_DRAW;
  }
  
  @Override
  public int kGL_STREAM_DRAW() {
    return GL15.GL_STREAM_DRAW;
  }

  @Override
  public int kGL_TRIANGLES() {
    return GL11.GL_TRIANGLES;
  }

  @Override
  public int kGL_VALIDATE_STATUS() {
    return GL20.GL_VALIDATE_STATUS;
  }

  @Override
  public int kGL_VERTEX_SHADER() {
    return GL20.GL_VERTEX_SHADER;
  }


  @Override
  public LinMath getLinMath() {
    return linMath;
  }

}
