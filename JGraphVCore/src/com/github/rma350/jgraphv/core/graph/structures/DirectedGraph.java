package com.github.rma350.jgraphv.core.graph.structures;

public class DirectedGraph<N, E> implements Graph<N,E>{

  private DirectedAdjacency<N, E> adjacency;

  public DirectedGraph() {
    adjacency = new DirectedHashAdjacency<N, E>();
  }

  public DirectedAdjacency<N, E> getAdjacency() {
    return adjacency;
  }
}
