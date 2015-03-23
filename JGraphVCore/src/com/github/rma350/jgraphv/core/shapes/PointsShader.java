package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.Camera;
import com.github.rma350.jgraphv.core.portable.GL;

public class PointsShader {
  
  private static final String uModel = "uModel";
  private static final String uView = "uView";
  private static final String uProjection = "uProjection";
  private static final String aPosition = "aPosition";
  private static final String aSize = "aSize";
  
  private static final String vertexShaderCode = 
      "uniform mat4 " + uModel + ";\n" +
      "uniform mat4 "  + uView + ";\n" +
      "uniform mat4 " + uProjection + ";\n" +
      "attribute vec4 " + aPosition + ";\n" +
      "attribute float " + aSize + ";\n" + 
      "void main() {\n" + 
      "  gl_PointSize = (" + uView  + "* vec4(" + aSize + ", 0 , 0 , 0)).x;\n" +
      "  gl_Position = " + uProjection + "*" + uView  + "*" +  uModel + "*" + aPosition + ";\n" + 
      "}\n";
  
  private static final String uColor = "uColor"; 

  private static final String fragmentShaderCode = 
      "precision mediump float;\n" + 
      "uniform vec4 " + uColor + ";\n" + 
      "void main() {\n" + 
      "  gl_FragColor = " + uColor + ";\n" + 
      "}\n";
  
  private int mProgram;

  // Attributes
  private int mSize;
  private int mPosition;
  

  // Uniforms
  private int mColor;
  private int mModel;
  private int mView;
  private int mProjection;
  private GL gl;

  public PointsShader(GL gl) {
    this.gl = gl;
    mProgram = ShaderUtil.createProgram(gl, vertexShaderCode, fragmentShaderCode); 
    mSize = gl.glGetAttribLocation(mProgram, aSize);
    mPosition = gl.glGetAttribLocation(mProgram, aPosition);
    mColor = gl.glGetUniformLocation(mProgram, uColor);
    
    mModel = gl.glGetUniformLocation(mProgram, uModel);
    mView = gl.glGetUniformLocation(mProgram, uView);
    mProjection = gl.glGetUniformLocation(mProgram, uProjection);
  }
  
  public void use(float r, float g, float b, float a, Camera camera) {
    gl.glUseProgram(mProgram);
    gl.glEnableVertexAttribArray(mSize);
    gl.glEnableVertexAttribArray(mPosition);
    gl.glUniform4f(mColor, r, g, b, a);
    gl.glUniformMatrix4(mView, camera.getView());
    gl.glUniformMatrix4(mProjection, camera.getProjection());
  }

  private static int COORDS_PER_VERTEX = 3;

  static int BYTES_PER_FLOAT = 4;
  static int vertexStrideInBytes = BYTES_PER_FLOAT * COORDS_PER_VERTEX;

  public void draw(PointsBuffer points) {
    gl.glUniformMatrix4(mModel, points.getModelMatrix());
    points.bindBuffer();
    points.ensureOnGpu();
    gl.glVertexAttribPointer(mPosition, 2, false,
        vertexStrideInBytes, 0);
    gl.glVertexAttribPointer(mSize, 1, false,
        vertexStrideInBytes, 2*BYTES_PER_FLOAT);
    gl.glDrawArrays(gl.kGL_POINTS(), 0, points.getVertexCount());
    points.unBindBuffer();
  }

  public void unuse() {
    // GLES20.glDisableVertexAttribArray(mPositionAttr);
  }
  
  GL getGL(){
    return gl;
  }

}
