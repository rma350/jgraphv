package com.github.rma350.jgraphv.core.graph.layout;

import java.util.ArrayList;
import java.util.List;

import com.github.rma350.jgraphv.core.portable.Vec2;

public class NodePositions<N> {
  
  public final List<NodeData<N>> nodeData;
  
  public static class NodeData<N> {
    public N node;
    public Vec2 position;
    public Vec2 force;
  }
  
  public NodePositions(){
    nodeData = new ArrayList<NodeData<N>>();
  }
  
  public void overwritePositionForce(NodePositions<N> other){
    assert(this.nodeData.size() == other.nodeData.size());
    for(int i = 0; i < this.nodeData.size(); i++){
      NodeData<N> myData = this.nodeData.get(i);
      NodeData<N> otherData = other.nodeData.get(i);
      assert(myData.node == otherData.node);
      myData.position.copy(otherData.position);
      myData.force.copy(otherData.force);
    }
  }

}
