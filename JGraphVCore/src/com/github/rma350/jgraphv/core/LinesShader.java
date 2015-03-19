package com.github.rma350.jgraphv.core;

import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.Log;
import com.github.rma350.jgraphv.core.portable.Vec4;

public class LinesShader {
  
  private static final String TAG = LinesShader.class.getSimpleName();
  
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

  public LinesShader(GL gl) {
    this.gl = gl;
    mProgram = ShaderUtil.createProgram(gl, vertexShaderCode, fragmentShaderCode);

    
    mPosition = gl.glGetAttribLocation(mProgram, aPosition);
    Log.out.d(TAG, "Attribute Position: " + mPosition);
    mColor = gl.glGetUniformLocation(mProgram, uColor);
    Log.out.d(TAG, "Uniform Color: " + mColor);
    
    
    mModel = gl.glGetUniformLocation(mProgram, uModel);
    mView = gl.glGetUniformLocation(mProgram, uView);
    mProjection = gl.glGetUniformLocation(mProgram, uProjection);
  }
  
  public void use(float r, float g, float b, float a, Camera camera, float lineWidth) {
    gl.glUseProgram(mProgram);
    gl.glEnableVertexAttribArray(mPosition);
    gl.glUniform4f(mColor, r, g, b, a);
    gl.glUniformMatrix4(mView, camera.getView());
    gl.glUniformMatrix4(mProjection, camera.getProjection());
    Vec4 vec = gl.getLinMath().createVec4(lineWidth, 0, 0, 0);
    Vec4 out = gl.getLinMath().mult(camera.getView(), vec);
    float lineScreen =out.x();
    gl.glLineWidth(lineScreen);
  }

  private static int COORDS_PER_VERTEX = 2;

  static int floatSizeBytes = 4;
  static int vertexStrideInBytes = floatSizeBytes * COORDS_PER_VERTEX;

  public void draw(LinesBuffer lines) {
    gl.glUniformMatrix4(mModel, lines.getModelMatrix());
    lines.bindBuffer();
    lines.ensureOnGpu();
    gl.glVertexAttribPointer(mPosition, 2, false,
        vertexStrideInBytes, 0);
    gl.glDrawArrays(gl.kGL_LINES(), 0, lines.getVertexCount());
    lines.unBindBuffer();
  }

  public void unuse() {
    // GLES20.glDisableVertexAttribArray(mPositionAttr);
  }

}
