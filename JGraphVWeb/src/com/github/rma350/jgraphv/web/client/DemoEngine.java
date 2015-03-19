package com.github.rma350.jgraphv.web.client;

import com.github.rma350.jgraphv.core.CheckedGL;
import com.github.rma350.jgraphv.core.ShaderUtil;
import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.web.client.coredeps.WebGL;
import com.github.rma350.jgraphv.web.client.coredeps.WebMat4;
import com.github.rma350.jgraphv.web.client.coredeps.WebMath;
import com.github.rma350.jgraphv.web.client.coredeps.WebNativeFloatBuffer;

public class DemoEngine {
   
  private static final String vertexShader = 
      "attribute vec4 aVertexPosition;\n" +
      "uniform mat4 uPerspective;\n" +
      "void main(void) {\n" + 
      "  gl_Position = uPerspective * aVertexPosition;\n" + 
      "}\n";
  
  private static final String fragmentShader =
      "precision mediump float;\n" +
      "uniform vec4 uColor;\n" +
      "void main(void) {\n" + 
      "  gl_FragColor = uColor;\n" + 
      "}\n";
  /*
  public static int loadShader(WebGL gl, int type, String shaderCode) {
    // create a vertex shader type (gl.kGL_VERTEX_SHADER())
    // or a fragment shader type (gl.kGL_FRAGMENT_SHADER())
    int shader = gl.glCreateShader(type);
    // add the source code to the shader and compile it
    gl.glShaderSource(shader, shaderCode);
    gl.glCompileShader(shader);
    
    if(gl.glShaderParameter(shader, gl.kGL_COMPILE_STATUS()) == 0){
      Log.out.e(TAG, "Compile failed");
      Log.out.e(TAG, "Shader code:\n" + shaderCode);
      String shaderLog = gl.glShaderInfoLog(shader);
      Log.out.e(TAG, shaderLog);
      throw new RuntimeException(shaderLog);
    }
    return shader;
  }
  
  public static int createProgram(WebGL gl, String vertexShader, String fragmentShader){
    int vertexProgram = loadShader(gl, gl.kGL_VERTEX_SHADER(), vertexShader);
    int fragmentProgram = loadShader(gl, gl.kGL_FRAGMENT_SHADER(), fragmentShader);
    int shaderProgram = gl.glCreateProgram();
    gl.glAttachShader(shaderProgram, vertexProgram);
    gl.glAttachShader(shaderProgram, fragmentProgram);
    gl.glLinkProgram(shaderProgram);
    if(gl.glProgramParameter(shaderProgram, gl.kGL_LINK_STATUS()) == 0){
      Log.out.e(TAG, "Program link failed");
      Log.out.e(TAG, "Vertex code:\n" + vertexShader);
      Log.out.e(TAG, "Fragment code:\n" + fragmentShader);
      String programLog = gl.glProgramInfoLog(shaderProgram);
      Log.out.e(TAG, programLog);
      throw new RuntimeException(programLog);
    }
    gl.glUseProgram(shaderProgram);
    return shaderProgram;
  }
  */
  
  private GL gl;
  private WebGL webGL;
  private int program;
  private int posAttr;
  private int perspectiveUniform;
  private int colorUniform;
  private int buffer;
  private WebNativeFloatBuffer nativeArray;
  private WebMath math;
  
  private WebMat4 projection;
  
  
  public DemoEngine(){
    webGL = new WebGL("graph-canvas",true);
    gl = new CheckedGL(webGL);
    gl.glViewport(0, 0, webGL.getCanvasWidth(), webGL.getCanvasHeight());
    gl.glClearColor(1,0,0,1);
    gl.glClear(gl.kGL_COLOR_BUFFER_BIT());
    program = ShaderUtil.createProgram(gl,vertexShader, fragmentShader);
    posAttr = gl.glGetAttribLocation(program, "aVertexPosition");
    perspectiveUniform = gl.glGetUniformLocation(program, "uPerspective");
    colorUniform = gl.glGetUniformLocation(program, "uColor");
    buffer = gl.glCreateBuffer();
    gl.glBindBuffer(gl.kGL_ARRAY_BUFFER(), buffer);
    float[] data =  new float[]{10f,10f, 10f,100f, 200f, 10f};
    nativeArray = (WebNativeFloatBuffer)gl.createFloatBuffer(data.length);
    nativeArray.putAll(data);
    gl.glBufferData(gl.kGL_ARRAY_BUFFER(), nativeArray, gl.kGL_STATIC_DRAW());
    
    math = new WebMath();
    projection = math.createMat4();
  }
  
  private int alpha = 500;
  
  public void drawScene(){
    gl.glClearColor(1,0,0,1);
    gl.glClear(gl.kGL_COLOR_BUFFER_BIT());
    
    
    math.setOrtho2D(projection, 0, webGL.getCanvasWidth(), 0, webGL.getCanvasHeight());
    gl.glUniformMatrix4(perspectiveUniform, projection);
    gl.glUniform4f(colorUniform, 0, 1, 0, (alpha % 1000)/1000.0f);
    gl.glBindBuffer(gl.kGL_ARRAY_BUFFER(), buffer);
    int sizeOfFloatInBytes = 4;
    int sizeOfVertexInFloats = 2;
    int strideInBytes =sizeOfFloatInBytes *  sizeOfVertexInFloats;
    int sizeOfAttributeInFloats = 2;   
    gl.glEnableVertexAttribArray(posAttr);
    gl.glVertexAttribPointer(posAttr, sizeOfAttributeInFloats, false,strideInBytes , 0);
    gl.glDrawArrays(gl.kGL_TRIANGLES(), 0, 3);
  }
  
  public void update(){
    alpha++;
  }

}
