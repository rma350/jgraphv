package com.github.rma350.jgraphv.core.graph.structures;


public interface UndirectedAdjacency<N, E> extends Adjacency<N,E> {

  public N opposite(N node, E edge);
  public boolean hasEdge(N m, N n);
  public E getEdgeIfExists(N m, N n);
  
  public void addNode(N node);
  public void addEdge(N m, N n, E edge);
  
  public Iterable<N> neighborsOf(N n);  
  
}
