package com.github.rma350.jgraphv.core.graph.structures;


public class InternalEdge <N, E> {
  
  public N start;
  public N end;
  public E edge;
  
  public InternalEdge(N start, N end, E edge){
    this.start = start;
    this.end = end;
    this.edge = edge;
  }
  
  public N opposite(N node){
    if(node == start){
      return end;
    }
    else if(node == end){
      return start;
    }
    else{
      throw new RuntimeException("Node: " + node + " is neither start nor end of internal edge: " + this);
    }
  }
  
  public String toString(){
    return "start: " + start + " end: " + end + " edge: " + edge;
  }

}
