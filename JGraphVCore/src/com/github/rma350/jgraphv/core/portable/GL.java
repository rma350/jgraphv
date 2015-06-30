package com.github.rma350.jgraphv.core.portable;


public interface GL {
 
  public NativeFloatBuffer createFloatBuffer(int numFloats);
  public LinMath getLinMath();

	public void glAttachShader(int program, int shader);
	public void glBlendFunc(int sfactor, int dfactor);
	public void glClear(int mask);
	public void glClearColor(float red, float green, float blue, float alpha);
	
	
	public void glCompileShader(int shaderProgram);
	public int glCreateProgram();
	
	public int glCreateShader(int shaderType);
	public void glDrawArrays(int mode, int first, int count);
	public void glEnable(int cap);
	public void glEnableVertexAttribArray(int glLocation);
	
	
	public int glGetAttribLocation(int program, String attribute);
	public String glGetProgramInfoLog(int program);
	public void glGetProgramiv(int program, int pname, int[] params);
	public void glGetShaderiv(int shader, int pname, int[] params);
	public String glGetShaderInfoLog(int shader);
	public int glGetError();
	public int glGetUniformLocation(int program, String uniform);
	public void glLineWidth(float width);
	public void glLinkProgram(int program);
	
	public void glShaderSource(int shaderProgram, String shaderCode);
	
	public void glValidateProgram(int program);
	
	public String gluErrorString(int errorCode);
	
	public void glUniform4f(int glLocation, float x, float y, float z, float w);
	public void glUniformMatrix4(int glLocation, Mat4 mat);
	
	public void glUseProgram(int program);
	
	// Call glVertexAttribPointer with type=GL_FLOAT
	public void glVertexAttribPointer(int glLocation, int size, boolean normalized, int stride, int offset);
	
	public int glCreateBuffer();
	public void glBufferData(int target, NativeFloatBuffer nativeArray, int usage);
	public void glBufferSubData(int target, long offset, NativeFloatBuffer nativeArray);
	public void glBindBuffer(int target, int buffer);
	
	public int kGL_ARRAY_BUFFER();
	
	public void glViewport(int x, int y, int width, int height);
	
	public int kGL_BLEND();
	public int kGL_COMPILE_STATUS();
	public int kGL_DYNAMIC_DRAW();
	public int kGL_SRC_ALPHA();
	public int kGL_ONE_MINUS_SRC_ALPHA();
	
	public int kGL_COLOR_BUFFER_BIT();
	
	public int kGL_FLOAT();
	public int kGL_FRAGMENT_SHADER();
	
	public int kGL_LINES();
	public int kGL_LINK_STATUS();
	public int kGL_NO_ERROR();
	public int kGL_POINTS();
	public int kGL_STATIC_DRAW();
	public int kGL_STREAM_DRAW();
	public int kGL_TRIANGLES();
	public int kGL_VALIDATE_STATUS();
	public int kGL_VERTEX_SHADER();

	
	
	
}
