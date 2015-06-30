package com.github.rma350.jgraphv.core.multitasking;

public interface CooperativeTask<P,R> {
  
  /** Returns true when the task is complete.*/
  public boolean doWork();
  
  public void cancel();
  
  public P createProgessBuffer();
  
  /**
   * The returned P is a progress object holding the current progress of the
   * task. Ownership is NOT transferred to the caller, and it is an error to
   * hold a reference to the returned result outside the scope of the call site.
   */
  public void publishProgress(P progressOutputBuffer);
  
  /** It is an error to call this before work is done.*/
  public R result();
  
  /** Returns a number in [0,1] indicating how close the task is to done (1 == done), as measured by running time.*/
  public float runtimeProgress();
  
  /**
   * Returns a number in [0,1] indicating how close the task is to done (1 ==
   * done), as some approximation of (an upper bound on) the "distance" between
   * the current progress and the result. Implementations may be more specific
   * about the chosen metric for P and any mathematical guarentees provided.
   */
  public float convergenceProgress();
  
  

}
