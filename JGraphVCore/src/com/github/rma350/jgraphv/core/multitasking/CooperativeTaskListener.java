package com.github.rma350.jgraphv.core.multitasking;

public interface CooperativeTaskListener<P,R> {
  
  /**Listener may take ownership of result.*/
  public void onResult(R result);
  
  /**Listener may hold ownership of progress UNTIL next call to onProgess.*/
  public void onProgress(P progress);
  
  public void onPercentComplete(float runtimeProgress, float convergenceProgress);
  
  public void onCancelComplete();
  
  public void onError(Throwable error);

}
