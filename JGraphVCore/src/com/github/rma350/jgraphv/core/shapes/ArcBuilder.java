package com.github.rma350.jgraphv.core.shapes;

import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Vec2;

/**
 * Given two points on a circle and a line width, generates a triangle
 * containing the arc. The circle is specified by the radians of arc and whether
 * the line should bend to the left or right, from the perspective of the
 * starting point. The center of the circle and its radius are additionally
 * generated as output.
 * 
 * <p>
 * Warning: This class is not threadsafe.
 * */
public class ArcBuilder {
  
  private LinMath math;
  
  private static final float PI_OVER_TWO = (float)Math.PI/2;
  
  // Inputs  
  public Vec2 iFrom;
  public Vec2 iTo;
  public float iArcRadians;
  public float iArcWidth;
  public boolean iBendLeft;
  
  
  // Compute variables
  private Vec2 lMid;
  private Vec2 lUnitParallel;
  private Vec2 lUnitPerp;  
  private Vec2 lArcFarMid;
  
  // Output variables
  public float oCircleRadius;
  public Vec2 oCircleCenter;
  public Vec2 oFrom;
  public Vec2 oTo;
  
  
  public ArcBuilder(LinMath math){
    this.math = math;
    reset();
  }
  
  public void reset(){ 
    
    iArcRadians = PI_OVER_TWO;
    iArcWidth = 1;
    iBendLeft = true;
    iFrom = math.createVec2();
    iTo = math.createVec2();
    
    
    lMid  = math.createVec2();
    lUnitParallel = math.createVec2();
    lUnitPerp  = math.createVec2();
    lArcFarMid  = math.createVec2();
    
    oCircleCenter  = math.createVec2();
    oFrom = math.createVec2();
    oTo = math.createVec2();
    oCircleRadius = 0;
  }
  
  /** Updates output variables oCircleCenter, oFrom, oTo and oCircleRadius for fixed values of input */
  public void computeArc(){
    if(iArcRadians >= Math.PI || iArcRadians < 0){
      throw new RuntimeException("Arc radians should be between pi and 0 but was: " + iArcRadians);
    }
    math.add(lMid, iFrom, iTo);
    math.scale(lMid, lMid, .5f);
    
    math.subtract(lUnitParallel, iTo, iFrom);
    float fromToDist = math.norm(lUnitParallel);
    float fromAngle = PI_OVER_TWO - iArcRadians/2;
    oCircleRadius = fromToDist/(2*(float)Math.cos(fromAngle));
    float perpLength = (float)Math.cos(iArcRadians/2)*oCircleRadius;
    
    math.normalize(lUnitParallel, lUnitParallel);
    if(iBendLeft){
      // If the arc bends to the left, the center is to the right, i.e. we
      // must rotate parallel clockwise (a negative rotation)
      lUnitPerp.set(lUnitParallel.y(), -lUnitParallel.x());
    }
    else{
      // If the arc bends to the right, the center is to the left, i.e. we
      // must rotate parallel counter clockwise (a positive rotation)
      lUnitPerp.set(-lUnitParallel.y(), lUnitParallel.x());
    }
    math.scaleAndAdd(oCircleCenter, lMid, lUnitPerp, perpLength);
    float extendedRadius = oCircleRadius + iArcWidth/2;
    math.scaleAndAdd(lArcFarMid, oCircleCenter, lUnitPerp, -extendedRadius);
    // use similar triangles to compute the width of the top bound
    float topWidth = fromToDist *(extendedRadius/perpLength);
    math.scaleAndAdd(oFrom, lArcFarMid, lUnitParallel, -topWidth/2);
    math.scaleAndAdd(oTo, lArcFarMid, lUnitParallel, topWidth/2);
  }
}

