package com.github.rma350.jgraphv.core.graph.structures;


public class UndirectedGraph<N, E> implements Graph<N,E> {

  private UndirectedAdjacency<N,E> adjacency;
  
  public UndirectedGraph(){
    adjacency = new UndirectedHashAdjacency<N,E>();
  }
  
  public UndirectedAdjacency<N,E> getAdjacency(){
    return adjacency;
  }
}
