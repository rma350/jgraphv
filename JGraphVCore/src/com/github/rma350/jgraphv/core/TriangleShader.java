package com.github.rma350.jgraphv.core;

import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.Log;

public class TriangleShader {
  private static final String TAG = TriangleShader.class.getSimpleName();

  private static final String uModel = "uModel";
  private static final String uView = "uView";
  private static final String uProjection = "uProjection";
  private static final String aPosition = "aPosition";
  
  
  private static final String vertexShaderCode = 
      "uniform mat4 " + uModel + ";\n" +
      "uniform mat4 "  + uView + ";\n" +
      "uniform mat4 " + uProjection + ";\n" +
      "attribute vec4 " + aPosition + ";\n" +
      "void main() {\n" + 
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
  private int mPosition;

  // Uniforms
  private int mColor;
  private int mModel;
  private int mView;
  private int mProjection;
  private GL gl;

  public TriangleShader(GL gl) {
    this.gl = gl;

    mProgram = ShaderUtil.createProgram(gl, vertexShaderCode,
        fragmentShaderCode);

    mPosition = gl.glGetAttribLocation(mProgram, aPosition);
    Log.out.d(TAG, "Attribute Position: " + mPosition);
    mColor = gl.glGetUniformLocation(mProgram, uColor);
    Log.out.d(TAG, "Uniform Color: " + mColor);

    mModel = gl.glGetUniformLocation(mProgram, uModel);
    mView = gl.glGetUniformLocation(mProgram, uView);
    mProjection = gl.glGetUniformLocation(mProgram, uProjection);
  }

  public void use(float r, float g, float b, float a, Camera camera) {
    gl.glUseProgram(mProgram);
    gl.glEnableVertexAttribArray(mPosition);
    gl.glUniform4f(mColor, r, g, b, a);
    gl.glUniformMatrix4(mView, camera.getView());
    gl.glUniformMatrix4(mProjection, camera.getProjection());
  }

  private static int COORDS_PER_VERTEX = 2;

  static int BYTES_PER_FLOAT = 4;
  static int vertexStrideInBytes = BYTES_PER_FLOAT * COORDS_PER_VERTEX;

  public void draw(TriangleBuffer points) {

    gl.glUniformMatrix4(mModel, points.getModelMatrix());
    points.bindBuffer();
    points.ensureOnGpu();
    gl.glVertexAttribPointer(mPosition, 2, false, vertexStrideInBytes, 0);
    gl.glDrawArrays(gl.kGL_TRIANGLES(), 0, points.getVertexCount());
    points.unBindBuffer();
  }

  public void unuse() {
    // GLES20.glDisableVertexAttribArray(mPositionAttr);
  }
}
