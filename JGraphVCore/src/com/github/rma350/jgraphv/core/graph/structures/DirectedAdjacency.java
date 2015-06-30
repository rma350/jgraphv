package com.github.rma350.jgraphv.core.graph.structures;

public interface DirectedAdjacency<N,E> extends Adjacency<N,E> {
  
  public N opposite(N node, E edge);
  public boolean hasEdge(N from, N to);
  public E getEdgeIfExists(N from, N to);
  
  public void addNode(N node);
  public void addEdge(N from, N to, E edge);
 
  public Iterable<N> outgoingNeighbors(N n);
  public Iterable<N> incomingNeighbors(N n);


}
