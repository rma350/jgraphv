package com.github.rma350.jgraphv.core.graph.layout;


import com.github.rma350.jgraphv.core.graph.layout.NodePositions.NodeData;
import com.github.rma350.jgraphv.core.graph.structures.UndirectedAdjacency;
import com.github.rma350.jgraphv.core.graph.structures.UndirectedMatrixAdjacency;
import com.github.rma350.jgraphv.core.multitasking.CooperativeTask;
import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Vec2;

/**
 * Inspired by
 * https://github.com/IainNZ/GraphLayout.jl/blob/master/src/spring.jl
 * See for references and a less abstract implementation.
 */
public class SpringLayout<N,E> implements CooperativeTask<NodePositions<N>,NodePositions<N>> {
  
  public static interface Input<NN,EE> {
    public float preferredLength(EE edge);
    public float preferredLengthNotNeighbors();
    public UndirectedAdjacency<NN,EE> adjacency();
    public Vec2 initialLocation(NN node);
  }
  
 
  
  private LinMath math;
  private Input<N,E> input;
  private NodePositions<N> nodes;
  private final float initTemp;
  private float currentTemp;
  private int currentIteration;
  private int maxIterations;
  
  // Local variable for computation, member to reduce allocs
  private Vec2 lDistToNode;
  
  private UndirectedMatrixAdjacency<N,E> matrix;
  
  public SpringLayout(Input<N,E> input, LinMath math){
    this.input = input;
    this.math = math;
    lDistToNode = math.createVec2();
    if(input.adjacency() instanceof UndirectedMatrixAdjacency){
      matrix = (UndirectedMatrixAdjacency<N,E>) input.adjacency();
    }
    else{
      matrix = new UndirectedMatrixAdjacency<N,E>(input.adjacency());
    }
    nodes = this.createEmptyProgress();
    initTemp = 2f* maxCoord(nodes);
    currentTemp = initTemp;
    maxIterations = 100;
    currentIteration = 0;
  }
  
  /** Returns true when done.*/
  @Override
  public boolean doWork(){
    int numNodes = nodes.nodeData.size();
    for(int i = 0 ; i < numNodes; i++){
      NodeData<N> node = nodes.nodeData.get(i); 
      node.force.set(0, 0);      
      // TODO(rma350) we should only interate though actual neighbors and nearby nodes...
      for(int j = 0; j < numNodes; j++){
        NodeData<N> potentialNeighbor = nodes.nodeData.get(j);
        if(node.node == potentialNeighbor.node){
          continue;
        }
        math.subtract(lDistToNode, potentialNeighbor.position, node.position);
        float dist = math.norm(lDistToNode);
        float distSq = dist*dist;
        E edge = matrix.getAdjacencyMatrix().get(i).get(j); //.getEdgeIfExists(node.node, potentialNeighbor.node);
        if(edge != null) {
          float preferredDist = input.preferredLength(edge); 
          float force = dist/preferredDist - (preferredDist*preferredDist)/distSq;
          math.scaleAndAdd(node.force, node.force, lDistToNode, force);
        }
        else{
          float force = -input.preferredLengthNotNeighbors()*input.preferredLengthNotNeighbors()/distSq;
          math.scaleAndAdd(node.force, node.force, lDistToNode, force);
        }
      }      
    }
    currentIteration++;
    currentTemp = initTemp/currentIteration;
    for(NodeData<N> node: nodes.nodeData){
      float forceMag = math.norm(node.force);
      float scale = Math.min(forceMag, currentTemp)/forceMag;
      math.scaleAndAdd(node.position, node.position, node.force, scale);
    }
    return currentIteration >= maxIterations;
  }


  @Override
  public void cancel() {}
  


  public NodePositions<N> createEmptyProgress() {
    NodePositions<N> ans = new NodePositions<N>();
    for(N n: input.adjacency().nodes()){
      NodeData<N> data = new NodeData<N>();
      data.node = n;
      data.position = math.createVec2(input.initialLocation(n));
      data.force = math.createVec2();
      ans.nodeData.add(data);
    }
    return ans;
  }

  @Override
  public NodePositions<N> result() {
    return this.nodes;
  }

  @Override
  public float runtimeProgress() {
    return currentIteration/(float)maxIterations;
  }

  @Override
  public float convergenceProgress() {
    // sum i=1:n = 1/i == log(n)
    double progress = Math.log(currentIteration+1);
    double end = Math.log(maxIterations+1);
    return (float)(progress/end);
  }
  
  private float maxCoord(NodePositions<N> nodePositions){
    float maxCoord = 0;
    for(NodeData<N> data: nodePositions.nodeData){
      maxCoord = Math.max(maxCoord, Math.abs(data.position.x()));
      maxCoord = Math.max(maxCoord, Math.abs(data.position.y()));
    }
    return maxCoord;
  }

  @Override
  public NodePositions<N> createProgessBuffer() {
    return createEmptyProgress();
  }

  @Override
  public void publishProgress(NodePositions<N> progressOutputBuffer) {
    for(int i = 0; i < nodes.nodeData.size(); i++){
      NodeData<N> outNode = progressOutputBuffer.nodeData.get(i);
      NodeData<N> selfNode = nodes.nodeData.get(i);
      outNode.force.copy(selfNode.force);
      outNode.position.copy(selfNode.position);
    }
  }
}
