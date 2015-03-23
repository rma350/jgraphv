package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.Camera;
import com.github.rma350.jgraphv.core.portable.GL;

/**
 * A shader that draws fixed width curved lines that form the arc of a circle.
 **/
public class ArcShader {
  
  /**
   * Draws an arc about the circle with center "aCircleCenter" and radius
   * "aCircleRadius". The arc has line width given by "aArcWidth", centered on
   * the edge of the circle. The portion of the arc drawn is the intersection of
   * the arc with the triangle given by "aPosition".
   * 
   * Outputs to the *Circle Coordinate Space*, where "aCircleCenter" is (0,0)
   * and points "aCircleRadius" away in world coordinates are 1 away in Circle
   * Coordinates.
   * 
   * Attributes:
   * 
   * aPosition: A coordinate of a triangle, in object space.
   * 
   * aCircleCenter: The center of the circle in world coordinates.
   * 
   * aCircleRadius: The size of the circle in world coordiantes.
   * 
   * aArcWidth: The width of the arc drawn in world coordiantes.
   * 
   * Note that for each triangle, "aCircleCenter", "aCircleRadius", and
   * "aArcWidth" should be the same.
   * 
   * Varyings:
   * 
   * vPositionCC: The position of the vertex translated to Circle Coordinate
   * Space.
   * 
   * vArcHalfWidthCC: Half of the width of the line, translated into Circle
   * Coordinate Space.
   * 
   */
  // @formatter:off
  private static final String vertexShaderCode = 
      "uniform mat4 uModel;\n" + 
      "uniform mat4 uView;\n" + 
      "uniform mat4 uProjection;\n" + 
      "attribute vec4 aPosition;\n" + 
      "attribute vec2 aCircleCenter;\n" + 
      "attribute float aCircleRadius;\n" + 
      "attribute float aArcWidth;\n" + 
      "varying vec2 vPositionCC;\n" + 
      "varying float vArcHalfWidthCC;\n" + 
      "void main() {\n" + 
      "  vec4 worldPos4 = uModel*aPosition;\n" + 
      "  vPositionCC = (vec2(worldPos4) - aCircleCenter ) / aCircleRadius;\n" + 
      "  vArcHalfWidthCC = aArcWidth /(2.0*aCircleRadius);\n" + 
      "  gl_Position = uProjection * uView * worldPos4;\n" +
      "}\n";
//@formatter:on

  /**
   * For each pixel, draws to "uColor" if it lies within vArcHalfWidth of the arcs, i.e. if 
   * 
   * 1- vArcHalfWidthCC< ||vPositionCC||_2 < 1 + vArcHalfWidthCC
   * 
   *  <==>
   *  
   *  abs(||vPositionCC||_2^2 - 1 - vArcHalfWidthCC^2) < 2*vArcHalfWidthCC
   * 
   * 
   */
//@formatter:off
  private static final String fragmentShaderCode =
      "precision mediump float;\n" +
      "uniform vec4 uColor;\n" +
      "varying vec2 vPositionCC;\n" +
      "varying float vArcHalfWidthCC;\n" +
      "void main() {\n" +
      "  float distSquared = dot(vPositionCC, vPositionCC);\n" +
      "  if(abs(distSquared - 1.0 - vArcHalfWidthCC*vArcHalfWidthCC) > 2.0*vArcHalfWidthCC) {\n" +
      "    discard;\n" +
      "  } else {\n" +
      "  gl_FragColor = uColor;\n" + 
      "  }\n" +
      "}\n";
// @formatter:on
  
  private GL gl;

  private int program;

  // Attributes
  private int aPosition;
  private int aCircleCenter;
  private int aCircleRadius;
  private int aArcWidth;

  // Uniforms
  private int uColor;
  private int uModel;
  private int uView;
  private int uProjection;

  public ArcShader(GL gl) {
    this.gl = gl;

    program = ShaderUtil
        .createProgram(gl, vertexShaderCode, fragmentShaderCode);
    aPosition = gl.glGetAttribLocation(program, "aPosition");
    aCircleCenter = gl.glGetAttribLocation(program, "aCircleCenter");
    aCircleRadius = gl.glGetAttribLocation(program, "aCircleRadius");
    aArcWidth = gl.glGetAttribLocation(program, "aArcWidth");
    
    uModel = gl.glGetUniformLocation(program, "uModel");
    uView = gl.glGetUniformLocation(program, "uView");
    uProjection = gl.glGetUniformLocation(program, "uProjection");
    uColor = gl.glGetUniformLocation(program, "uColor");
  }

  public void use(float r, float g, float b, float a, Camera camera) {
    gl.glUseProgram(program);
    gl.glEnableVertexAttribArray(aPosition);
    gl.glEnableVertexAttribArray(aCircleCenter);
    gl.glEnableVertexAttribArray(aCircleRadius);
    gl.glEnableVertexAttribArray(aArcWidth);
    gl.glUniform4f(uColor, r, g, b, a);
    gl.glUniformMatrix4(uView, camera.getView());
    gl.glUniformMatrix4(uProjection, camera.getProjection());
  }

  public void draw(ArcBuffer points) {

    gl.glUniformMatrix4(uModel, points.getModelMatrix());
    points.bindBuffer();
    points.ensureOnGpu();
    bindAttribute(aPosition, ArcMemLayout.POSITION_SIZE,
        ArcMemLayout.POSITION_OFFSET);
    bindAttribute(aCircleCenter, ArcMemLayout.CIRCLE_CENTER_SIZE,
        ArcMemLayout.CIRCLE_CENTER_OFFSET);
    bindAttribute(aCircleRadius, ArcMemLayout.CIRCLE_RADIUS_SIZE,
        ArcMemLayout.CIRCLE_RADIUS_OFFSET);
    bindAttribute(aArcWidth, ArcMemLayout.ARC_WIDTH_SIZE,
        ArcMemLayout.ARC_WIDTH_OFFSET);

    gl.glDrawArrays(gl.kGL_TRIANGLES(), 0, points.getVertexCount());
    points.unBindBuffer();
  }

  public void unuse() {
    // GLES20.glDisableVertexAttribArray(mPositionAttr);
  }
  
  private void bindAttribute(int attrHandle, int attrSizeFloats,
      int attrOffsetFloats) {
    gl.glVertexAttribPointer(attrHandle, attrSizeFloats, false,
        toBytes(ArcMemLayout.VERTEX_SIZE), toBytes(attrOffsetFloats));
  }
  
  private static int toBytes(int numFloats) {
    return 4 * numFloats;
  }

}
