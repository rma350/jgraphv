package com.github.rma350.jgraphv.core;

import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.Log;

public class ShaderUtil {
  
  private static final String TAG = ShaderUtil.class.getSimpleName();

  
  public static int loadShader(GL gl, int type, String shaderCode) {
    // create a vertex shader type (gl.kGL_VERTEX_SHADER())
    // or a fragment shader type (gl.kGL_FRAGMENT_SHADER())
    int shader = gl.glCreateShader(type);
    // add the source code to the shader and compile it
    gl.glShaderSource(shader, shaderCode);
    gl.glCompileShader(shader);
    int[] status = new int[1];
    gl.glGetShaderiv(shader, gl.kGL_COMPILE_STATUS(), status);
    if(status[0] == 0){
      Log.out.e(TAG, "Compile failed");
      Log.out.e(TAG, "Shader code:\n" + shaderCode);
      String shaderLog = gl.glGetShaderInfoLog(shader);
      Log.out.e(TAG, shaderLog);
      throw new RuntimeException(shaderLog);
    }
    return shader;
  }
  
  public static int createProgram(GL gl, String vertexShader, String fragmentShader){
    int vertexProgram = loadShader(gl, gl.kGL_VERTEX_SHADER(), vertexShader);
    int fragmentProgram = loadShader(gl, gl.kGL_FRAGMENT_SHADER(), fragmentShader);
    int shaderProgram = gl.glCreateProgram();
    gl.glAttachShader(shaderProgram, vertexProgram);
    gl.glAttachShader(shaderProgram, fragmentProgram);
    gl.glLinkProgram(shaderProgram);
    
    int[] linkStatus = new int[1];
    gl.glGetProgramiv(shaderProgram, gl.kGL_LINK_STATUS(),linkStatus);
    if(linkStatus[0] == 0){
      Log.out.e(TAG, "Program link failed");
      Log.out.e(TAG, "Vertex code:\n" + vertexShader);
      Log.out.e(TAG, "Fragment code:\n" + fragmentShader);
      String programLog = gl.glGetProgramInfoLog(shaderProgram);
      Log.out.e(TAG, programLog);
      throw new RuntimeException(programLog);
    }
    gl.glValidateProgram(shaderProgram);
    int[] validationStatus = new int[1];
    gl.glGetProgramiv(shaderProgram, gl.kGL_VALIDATE_STATUS(),validationStatus);
    if(validationStatus[0] == 0){
      Log.out.e(TAG, "Program not valid");
      Log.out.e(TAG, "Vertex code:\n" + vertexShader);
      Log.out.e(TAG, "Fragment code:\n" + fragmentShader);
      String programLog = gl.glGetProgramInfoLog(shaderProgram);
      Log.out.e(TAG, programLog);
      throw new RuntimeException(programLog);
    }
    gl.glUseProgram(shaderProgram);
    return shaderProgram;
  }

}
