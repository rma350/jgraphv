package com.github.rma350.jgraphv.core.graph.rendering;

import com.github.rma350.jgraphv.core.animation.AnimFuncs;
import com.github.rma350.jgraphv.core.animation.Animation;
import com.github.rma350.jgraphv.core.graph.layout.NodePositions;
import com.github.rma350.jgraphv.core.graph.typed.Node;
import com.github.rma350.jgraphv.core.multitasking.CooperativeTaskListener;
import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Log;

public class AnimatedGraphLayout implements Animation {
  
  public static final String TAG = AnimatedGraphLayout.class.getSimpleName();
  
  private final long animationLengthMs;
  private long currentAnimationTimeMs;
  private LinMath math;
  private GraphView graphView;
  private NodePositions<Node> nodePositions;
  
  private CooperativeTaskListener<NodePositions<Node>, NodePositions<Node>> coopListener;
  
  /*
  private static void printNodePositions(UndirectedGraph<Node,Edge> graph){
    for(Node node : graph.getAdjacency().nodes()){
      Log.out.d(TAG, "x: " + node.position.x() + ", y: " + node.position.y());
    }
  }
  */
  
  public CooperativeTaskListener<NodePositions<Node>, NodePositions<Node>> getCooperativeTaskListener(){
    return coopListener;
  }
  
  public AnimatedGraphLayout(LinMath math, long animationLengthMs, GraphView graphView){
    this.animationLengthMs = animationLengthMs;
    this.graphView = graphView;
    currentAnimationTimeMs = 0;
    this.math = math;
    nodePositions = null;
    coopListener = new CooperativeTaskListener<NodePositions<Node>, NodePositions<Node>>(){

      @Override
      public void onResult(NodePositions<Node> result) {
        Log.out.e(TAG, "Result ready");
        updateNodePositions(result);
      }

      @Override
      public void onPercentComplete(float runtimeProgress,
          float convergenceProgress) {
        Log.out.e(TAG, "Animation progress update: " + convergenceProgress);
      }

      @Override
      public void onCancelComplete() {}

      @Override
      public void onError(Throwable error) {
        throw new RuntimeException(error);        
      }



      @Override
      public void onProgress(NodePositions<Node> progress) {
        updateNodePositions(progress);
      }
      
    };

  }
  
  private void updateNodePositions(NodePositions<Node> positions){
    this.nodePositions = positions;
  }
  

  @Override
  public boolean update(long ellapsedMs) {
    if(nodePositions == null){
      return false;
    }
    long timeRemaining = animationLengthMs - currentAnimationTimeMs;
    Log.out.e(TAG, "Time remaining: " + timeRemaining);
    ellapsedMs = Math.min(ellapsedMs, timeRemaining);
    float fracDistToMove = interpolateAnimationTime(ellapsedMs);
    //System.out.println(fracDistToMove);
    for(NodePositions.NodeData<Node> data: nodePositions.nodeData){
      //String logpos = "start: " + data.node.position.toString() + " end: " + data.position + " next: ";
      math.lerp(data.node.position, data.node.position, data.position, fracDistToMove);
      //logpos += data.node.position;
      //System.out.println(logpos);
    }
    //Log.out.d(TAG, "After update:");    
    //printNodePositions(graphView.getGraph());
    currentAnimationTimeMs += ellapsedMs;
    graphView.update();
    return currentAnimationTimeMs >= animationLengthMs;
  }
  
  // A function mapping [0,1] -> [0,1] with f(0) = 0 and f(1) = 1
  private float animationFunction(float progress){
    return (float)Math.sqrt(progress);
    //return progress;
  }
  
  private float normalizeRealTime(long timeMs){
    return timeMs/(float)animationLengthMs;
  }
  
  
  
  private float interpolateAnimationTime(long ellapsedMs){
    float animationTimeCurrent = animationFunction(normalizeRealTime(currentAnimationTimeMs));
    float animationTimeNext = animationFunction(normalizeRealTime(currentAnimationTimeMs + ellapsedMs));
    float animationTimeEnd = 1; // == animationFunction(normalizeRealTime(animationLengthMs));
    float fractionOfRemainingAnimationInNext = AnimFuncs.normalize(animationTimeCurrent, animationTimeEnd, animationTimeNext);
    return fractionOfRemainingAnimationInNext;
    
  }

}
