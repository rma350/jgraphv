package com.github.rma350.jgraphv.core.threading;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.github.rma350.jgraphv.core.multitasking.CooperativeTask;
import com.github.rma350.jgraphv.core.multitasking.CooperativeTaskListener;
import com.github.rma350.jgraphv.core.portable.CooperativeTaskRunner;
import com.github.rma350.jgraphv.core.threading.lockfree.LockFreeProducerConsumer;

public class ThreadedTaskRunner implements CooperativeTaskRunner {

  private ExecutorService executor;
  private Map<CooperativeTask<?, ?>, TaskInfo<?, ?>> pendingTasks;

  public ThreadedTaskRunner() {
    executor = Executors.newCachedThreadPool();
    pendingTasks = new HashMap<CooperativeTask<?,?>, TaskInfo<?, ?>>();
  }
  
  @Override
  public <P, R> void cancel(CooperativeTask<P, R> task){
    pendingTasks.get(task).cancelRequested = true;
  }
  
  

  /** GL thread. */
  @Override
  public <P, R> void submit(CooperativeTask<P, R> task,
      CooperativeTaskListener<P, R> listener) {

    LockFreeProducerConsumer<CooperativeTaskProgress<P>> producerConsumer = new LockFreeProducerConsumer<CooperativeTaskProgress<P>>(
        new CooperativeTaskProgress<P>(task.createProgessBuffer()),
        new CooperativeTaskProgress<P>(task.createProgessBuffer()),
        new CooperativeTaskProgress<P>(task.createProgessBuffer()),
        new CooperativeTaskProductionUpdater<P>(task));
    CooperativeTaskRunnable<P, R> aTask = new CooperativeTaskRunnable<P, R>(task,
        producerConsumer.getProducer());
    TaskInfo<P, R> info = new TaskInfo<P, R>(producerConsumer, aTask, listener,
        executor.submit(aTask));
    pendingTasks.put(task, info);
  }

  @Override
  public void update() {
    for (Iterator<Map.Entry<CooperativeTask<?, ?>, TaskInfo<?, ?>>> it = pendingTasks
        .entrySet().iterator(); it.hasNext();) {
      Map.Entry<CooperativeTask<?, ?>, TaskInfo<?, ?>> entry = it.next();
      TaskInfo<?, ?> taskInfo = entry.getValue();
      if (taskInfo.future.isDone()) {
        if (taskInfo.cancelRequested) {
          taskInfo.listener.onCancelComplete();
        } else {
          try {
            taskInfo.future.get();
          } catch (InterruptedException e) {
            /**
             * This case cannot occur, Future.get() cannot block when
             * Future.isDone().
             */
            throw new RuntimeException(e);
          } catch (ExecutionException e) {
            taskInfo.listener.onError(e.getCause());
          }
          entry.getValue().onCompletion();
        }
        it.remove();
      } else {
        entry.getValue().onUpdate();
      }
    }
  }
  
  @Override
  public void onStop(){
    executor.shutdown();
  }

  // GL thread only
  @Override
  public void cancelAll() {
    for (Iterator<Map.Entry<CooperativeTask<?, ?>, TaskInfo<?, ?>>> it = pendingTasks
        .entrySet().iterator(); it.hasNext();) {
      Map.Entry<CooperativeTask<?, ?>, TaskInfo<?, ?>> entry = it.next();
      this.cancel(entry.getKey());
      // TaskInfo<?, ?> taskInfo = entry.getValue();
    }
  }
}
