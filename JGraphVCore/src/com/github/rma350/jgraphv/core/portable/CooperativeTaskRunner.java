package com.github.rma350.jgraphv.core.portable;

import com.github.rma350.jgraphv.core.multitasking.CooperativeTask;
import com.github.rma350.jgraphv.core.multitasking.CooperativeTaskListener;

public interface CooperativeTaskRunner {
  
  /** Task may be run on a background thread, listener will always run on the GL thread.*/
  public <P,R> void submit(CooperativeTask<P,R> task, CooperativeTaskListener<P,R> listener);
  
  public <P,R> void cancel(CooperativeTask<P,R> task);
  
  /**
   * For each task listener pair, notify the listener of progress made by the
   * task. Called from the GL thread, once per frame.
   */
  public void update();
  
  /** Cancels all pending tasks, shuts down any extra threads.*/
  public void onStop();
  
  /** Cancels all pending tasks*/
  public void cancelAll();

}
