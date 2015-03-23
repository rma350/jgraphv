package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.Camera;
import com.github.rma350.jgraphv.core.portable.GL;

public class CirclesShader {
  
  private static final String uModel = "uModel";
  private static final String uView = "uView";
  private static final String uProjection = "uProjection";
  private static final String aPosition = "aPosition";
  private static final String aCircleCoords = "aCircleCoords";
  private static final String vCircleCoords = "vCircleCoords";
  
  
  private static final String vertexShaderCode = 
      "uniform mat4 " + uModel + ";\n" +
      "uniform mat4 "  + uView + ";\n" +
      "uniform mat4 " + uProjection + ";\n" +
      "attribute vec4 " + aPosition + ";\n" +
      "attribute vec2 " + aCircleCoords + ";\n" +
      "varying vec2 " + vCircleCoords + ";\n" +
      "void main() {\n" + 
      "  " + vCircleCoords + " = " + aCircleCoords + ";\n" +
      "  gl_Position = " + uProjection + "*" + uView  + "*" +  uModel + "*" + aPosition + ";\n" + 
      "}\n";
  
  private static final String uColor = "uColor"; 

  private static final String fragmentShaderCode =     
      "precision mediump float;\n" +
      "uniform vec4 " + uColor + ";\n" + 
      "varying vec2 " + vCircleCoords + ";\n" +
      "void main() {\n" + 
      "  if(" + vCircleCoords + ".x * " + vCircleCoords + ".x + " + vCircleCoords + ".y * " + vCircleCoords +  ".y < 1.0 ) {\n" +
      "    gl_FragColor = " + uColor + ";\n" +
      "  } else {\n" +
      "    gl_FragColor = vec4(0,0,0,0);\n" + 
      "  }\n" +
      "}\n";
  
  private int mProgram;

  // Attributes
  private int mPosition;
  private int mCircleCoords;
  

  // Uniforms
  private int mColor;
  private int mModel;
  private int mView;
  private int mProjection;
  private GL gl;

  public CirclesShader(GL gl) {
    this.gl = gl;

    mProgram = ShaderUtil.createProgram(gl, vertexShaderCode, fragmentShaderCode);

    mCircleCoords = gl.glGetAttribLocation(mProgram, aCircleCoords);
    mPosition = gl.glGetAttribLocation(mProgram, aPosition);
    mColor = gl.glGetUniformLocation(mProgram, uColor);
    
    mModel = gl.glGetUniformLocation(mProgram, uModel);
    mView = gl.glGetUniformLocation(mProgram, uView);
    mProjection = gl.glGetUniformLocation(mProgram, uProjection);
  }
  
  public void use(float r, float g, float b, float a, Camera camera) {
    gl.glUseProgram(mProgram);
    gl.glEnableVertexAttribArray(mCircleCoords);
    gl.glEnableVertexAttribArray(mPosition);
    gl.glUniform4f(mColor, r, g, b, a);
    gl.glUniformMatrix4(mView, camera.getView());
    gl.glUniformMatrix4(mProjection, camera.getProjection());
  }

  private static int COORDS_PER_VERTEX = 4;

  static int BYTES_PER_FLOAT = 4;
  static int vertexStrideInBytes = BYTES_PER_FLOAT * COORDS_PER_VERTEX;

  public void draw(CirclesBuffer points) {
    
    gl.glUniformMatrix4(mModel, points.getModelMatrix());
    points.bindBuffer();
    points.ensureOnGpu();
    gl.glVertexAttribPointer(mPosition, 2, false,
        vertexStrideInBytes, 0);
    gl.glVertexAttribPointer(mCircleCoords, 2, false,
        vertexStrideInBytes, 2*BYTES_PER_FLOAT);
    gl.glDrawArrays(gl.kGL_TRIANGLES(), 0, points.getVertexCount());
    points.unBindBuffer();
  }

  public void unuse() {
    // GLES20.glDisableVertexAttribArray(mPositionAttr);
  }

}
