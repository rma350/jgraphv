package com.github.rma350.jgraphv.core.threading;

import com.github.rma350.jgraphv.core.multitasking.CooperativeTask;
import com.github.rma350.jgraphv.core.threading.lockfree.LockFreeProducerConsumer.ProductionUpdater;

public class CooperativeTaskProductionUpdater<P> implements ProductionUpdater<CooperativeTaskProgress<P>> {
  
  private CooperativeTask<P,?> task;
  
  public CooperativeTaskProductionUpdater(CooperativeTask<P,?> task) {
    this.task = task;
  }

  @Override
  public void updateData(CooperativeTaskProgress<P> taskProgress) {
    taskProgress.runtimeProgress = task.runtimeProgress();
    taskProgress.convergenceProgress = task.convergenceProgress();
    task.publishProgress(taskProgress.progressData);
  }

}
