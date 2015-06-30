package com.github.rma350.jgraphv.core.graph.structures;

import java.util.HashMap;

import java.util.Map;


public class UndirectedHashAdjacency<N, E> implements UndirectedAdjacency<N , E > {
  
  
  
  // For an edge e between nodes between m and n, keeps to entries in map,
  // adjacency[m][n] = e and adjacency[n][m] = e.
  private Map<N,Map<N,InternalEdge<N,E>>> adjacency;
  private Map<E,InternalEdge<N,E>> edgeLookup;
  
  public UndirectedHashAdjacency(){
    adjacency = new HashMap<N,Map<N,InternalEdge<N,E>>>();
    edgeLookup = new HashMap<E,InternalEdge<N,E>>();
  }

  @Override
  public N opposite(N node, E edge) {
    return edgeLookup.get(edge).opposite(node);
  }

  @Override
  public boolean hasEdge(N m, N n) {
    return adjacency.get(m).containsKey(n);
  }

  @Override
  public E getEdgeIfExists(N m, N n) {
    return hasEdge(m,n) ? adjacency.get(m).get(n).edge : null;
  }
  
  private void assertContainsNode(N n, boolean contains) {
    if (adjacency.containsKey(n) != contains) {
      throw new RuntimeException("Expected graph containment of node: " + n
          + " to be: " + contains + " but was: " + (!contains));
    }
  }
  
  public void addNode(N n){
    assertContainsNode(n,false);
    adjacency.put(n, new HashMap<N,InternalEdge<N,E>>());
  }
  
  public void addEdge(N m, N n, E edge){
    assertContainsNode(m, true);
    assertContainsNode(n, true);
    InternalEdge<N,E> iEdge = new InternalEdge<N,E>(m,n,edge);
    adjacency.get(m).put(n, iEdge);
    adjacency.get(n).put(m, iEdge);
    edgeLookup.put(edge, iEdge);
  }

  @Override
  public Iterable<N> nodes() {
    return adjacency.keySet();
  }

  @Override
  public Iterable<N> neighborsOf(N n) {
    return adjacency.get(n).keySet();
  }

  @Override
  public int numNodes() {
    return adjacency.size();
  }

  @Override
  public int numEdges() {
    return edgeLookup.size();
  }

  @Override
  public Iterable<E> edges() {
    return edgeLookup.keySet();
  }

  @Override
  public InternalEdge<N, E> internalEdge(E edge) {
    return edgeLookup.get(edge);
  }
  
  
  
  

}
