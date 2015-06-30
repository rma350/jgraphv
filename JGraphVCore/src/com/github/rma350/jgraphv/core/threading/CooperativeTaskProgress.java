package com.github.rma350.jgraphv.core.threading;

public class CooperativeTaskProgress<P> {
  
  public P progressData;
  public float runtimeProgress;
  public float convergenceProgress;
  
  public CooperativeTaskProgress(P initProgress){
    progressData = initProgress;
    runtimeProgress = 0;
    convergenceProgress = 0;
  }
  
}
