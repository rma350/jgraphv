package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Vec2;

public class DirectedArcBuilder {
  
  private LinMath math;

  
  public Vec2 iFrom;
  public Vec2 iTo;
  public float iArcRadians;
  public float iArcWidth;
  public boolean iBendLeft;
  
  /**
   * The size in world coordinates of the buffer that should be left around
   * iFrom. Use: if the arc begins at a circle of radius r, set
   * iFromSpacing=r.
   */
  public float iFromSpacing;
  public float iToSpacing;
  
  public float iArrowHeadWidth;
  public float iArrowHeadLength;
  
  public boolean oArcIsVisible;
  
  public DirectedArcBuilder(LinMath math){
    this.math = math;
    reset();
  }
  
  public void reset(){ 
    
    iArcRadians = (float)Math.PI/2;
    iArcWidth = 1;
    iBendLeft = true;
    iFrom = math.createVec2();
    iTo = math.createVec2();
    
    
  }
  
  /** Updates output variables oCircleCenter, oFrom, oTo and oCircleRadius for fixed values of input */
  public void computeArc(){
    
  }

}
