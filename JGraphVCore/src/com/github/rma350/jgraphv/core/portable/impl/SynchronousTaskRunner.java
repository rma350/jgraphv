package com.github.rma350.jgraphv.core.portable.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.github.rma350.jgraphv.core.multitasking.CooperativeTask;
import com.github.rma350.jgraphv.core.multitasking.CooperativeTaskListener;
import com.github.rma350.jgraphv.core.portable.CooperativeTaskRunner;

public class SynchronousTaskRunner implements CooperativeTaskRunner {
  
  
  private static class TaskEntry<P,R> {
    public final CooperativeTask<P,R> task;
    public final CooperativeTaskListener<P,R> listener;
    
    private P progress;
    
    public TaskEntry(CooperativeTask<P,R> task, CooperativeTaskListener<P,R> listener){
      this.task = task;
      this.listener = listener;
      this.progress =task.createProgessBuffer();
    }
    
    public void onComplete(){
      listener.onResult(task.result());
    }
    
    public void onUpdate(){
      listener.onPercentComplete(task.runtimeProgress(), task.convergenceProgress());
      task.publishProgress(progress);
      listener.onProgress(progress);
    }
  }
  
  
  private Map<CooperativeTask<?,?>, TaskEntry<?,?>> tasks;
  
  public SynchronousTaskRunner(){
    tasks = new HashMap<CooperativeTask<?,?>, TaskEntry<?,?>>();
  }

  @Override
  public <P, R> void submit(CooperativeTask<P, R> task,
      CooperativeTaskListener<P, R> listener) {
    tasks.put(task, new TaskEntry<P,R>(task,listener));
    
  }

  @Override
  public <P, R> void cancel(CooperativeTask<P, R> task) {
    TaskEntry<?,?> entry = tasks.remove(task);
    entry.listener.onCancelComplete();
  }

  @Override
  public void update() {
    for(Iterator<Map.Entry<CooperativeTask<?,?>, TaskEntry<?,?>>> it = tasks.entrySet().iterator(); it.hasNext();){
      Map.Entry<CooperativeTask<?,?>, TaskEntry<?,?>> mapEntry = it.next();
      boolean complete = mapEntry.getKey().doWork();
      if(complete){
        mapEntry.getValue().onComplete();
        it.remove();
      }
      else{
        mapEntry.getValue().onUpdate();
      }
    }
  }

  @Override
  public void onStop() {
    tasks.clear();
  }

  // GL thread only.
  @Override
  public void cancelAll() {
    for(CooperativeTask<?,?> task : this.tasks.keySet()){
      cancel(task);
    }
  }

}
