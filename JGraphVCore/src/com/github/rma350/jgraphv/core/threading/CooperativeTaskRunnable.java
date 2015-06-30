package com.github.rma350.jgraphv.core.threading;

import com.github.rma350.jgraphv.core.multitasking.CooperativeTask;
import com.github.rma350.jgraphv.core.threading.lockfree.LockFreeProducerConsumer.Producer;

class CooperativeTaskRunnable<P, R> implements Runnable {

  private CooperativeTask<P, R> mTask;
  private Producer<CooperativeTaskProgress<P>> producer;

  public CooperativeTaskRunnable(CooperativeTask<P, R> task,
      Producer<CooperativeTaskProgress<P>> producer) {
    mTask = task;
    this.producer = producer;
  }

  /** Background thread. */
  @Override
  public void run() {
    boolean complete = false;
    while (!Thread.currentThread().isInterrupted() && !complete) {
      complete = mTask.doWork();
      if (!complete){
        producer.publishIfBufferReady();
      }
    }
  }
  
  /**It is an error to call this until after run has completed and the task has succeeded.*/
  public R getResult(){
    return mTask.result();
  }
}