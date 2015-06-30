package com.github.rma350.jgraphv.core.graph.structures;

public interface Adjacency<N,E> {
  
  public int numNodes();
  public int numEdges();
  
  public Iterable<N> nodes();
  public Iterable<E> edges();
  
//Experimental, this may not stick around
 public InternalEdge<N,E> internalEdge(E edge);

}
