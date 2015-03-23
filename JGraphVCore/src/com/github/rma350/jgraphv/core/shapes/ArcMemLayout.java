package com.github.rma350.jgraphv.core.shapes;

/** Memory layout information for binding attribute data to the arc shader.*/
public class ArcMemLayout {
  
  // Keep order in sync with offsets.
  /**The size in floats of the position attribute.*/
  public static final int POSITION_SIZE = 2;
  /**The size in floats of the circle center attribute.*/
  public static final int CIRCLE_CENTER_SIZE = 2;
  /**The size in floats of the circle radius attribute.*/
  public static final int CIRCLE_RADIUS_SIZE = 1;
  /**The size in floats of the arc width attribute.*/
  public static final int ARC_WIDTH_SIZE = 1;
  
  // Keep order in sync with sizes.
  /** The offset in floats of the position attribute with a vertex.*/
  public static final int POSITION_OFFSET;
  /** The offset in floats of the circle center attribute with a vertex.*/
  public static final int CIRCLE_CENTER_OFFSET;
  /** The offset in floats of the circle radius attribute with a vertex.*/
  public static final int CIRCLE_RADIUS_OFFSET;
  /** The offset in floats of the arc width attribute with a vertex.*/
  public static final int ARC_WIDTH_OFFSET;
  
  /** The size in floats of each vertex for the arc shader.*/
  public static final int VERTEX_SIZE;
  static{
    int offset = 0;
    POSITION_OFFSET = offset;
    offset += POSITION_SIZE;
    CIRCLE_CENTER_OFFSET = offset;
    offset += CIRCLE_CENTER_SIZE;
    CIRCLE_RADIUS_OFFSET = offset;
    offset += CIRCLE_RADIUS_SIZE;
    ARC_WIDTH_OFFSET = offset;
    offset += ARC_WIDTH_SIZE;
    VERTEX_SIZE = offset;
  }
  
  /** The size in floats of each triangle for the arc shader.*/
  public static final int FLOATS_PER_ARC_TRIANGLE = 3 * VERTEX_SIZE;

}
