package com.github.rma350.jgraphv.core.animation;

public class AnimFuncs {
  
//when value  in [lowerBound, upperBound], returns a number in [0,1] where small means near lower bound and large means near upper bound.
 public static float normalize(float lowerBound, float upperBound, float value){
   return (value - lowerBound)/ (upperBound - lowerBound);
 }
 
 public static float lerp(float lowerBound, float upperBound, float t){
   return (1-t)*lowerBound + t*(upperBound);
 }
 
 public static interface AnimationFunction {
   public abstract float animationTime(float normalizedTime);
 }
 
 public static enum SimpleAnimationFunction implements AnimationFunction {
   LINEAR{
    @Override
    public float animationTime(float normalizedTime) {
      return normalizedTime;
    }     
   };
   
   
   
 }

}
