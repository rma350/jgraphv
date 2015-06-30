package com.github.rma350.jgraphv.core.threading;

import java.util.concurrent.Future;

import com.github.rma350.jgraphv.core.multitasking.CooperativeTaskListener;
import com.github.rma350.jgraphv.core.threading.lockfree.LockFreeProducerConsumer;

class TaskInfo<P, R> {
  public final LockFreeProducerConsumer<CooperativeTaskProgress<P>> producerConsumer;
  public final CooperativeTaskRunnable<P, R> task;
  public final CooperativeTaskListener<P, R> listener;
  public final Future<?> future;
  /** Access from GL thread only.*/
  public boolean cancelRequested;

  public TaskInfo(LockFreeProducerConsumer<CooperativeTaskProgress<P>> producerConsumer,
      CooperativeTaskRunnable<P, R> task,
      CooperativeTaskListener<P, R> listener, Future<?> future) {
    this.producerConsumer = producerConsumer;
    this.task = task;
    this.listener = listener;
    this.future = future;
    cancelRequested = false;
  }

  /** Call from the GL thread. */
  public void onCompletion() {
    // We have already established a memory barrier as the caller has called future.get().
    listener.onResult(task.getResult());
  }

  public void onUpdate() {
    boolean didConsume = producerConsumer.getConsumer().consumeIfBufferReady();
    if(didConsume){
      CooperativeTaskProgress<P> progress = producerConsumer.getConsumer().getData();
      this.listener.onPercentComplete(progress.runtimeProgress,
          progress.convergenceProgress);
      this.listener.onProgress(progress.progressData);
    }
  }
}