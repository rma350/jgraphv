package com.github.rma350.jgraphv.core.graph.structures;

import java.util.HashMap;
import java.util.Map;

public class DirectedHashAdjacency<N, E> implements DirectedAdjacency<N, E> {

  // For an edge e going from m to n, we keep an entry in both maps.
  // outgoingEdges[m][n] = e and incomingEdges[n][m] = e.
  // The class must keep these map sync.
  private Map<N, Map<N, InternalEdge<N, E>>> outgoingEdges;
  private Map<N, Map<N, InternalEdge<N, E>>> incomingEdges;
  private Map<E, InternalEdge<N, E>> edgeLookup;

  public DirectedHashAdjacency() {
    outgoingEdges = new HashMap<N, Map<N, InternalEdge<N, E>>>();
    incomingEdges = new HashMap<N, Map<N, InternalEdge<N, E>>>();
    edgeLookup = new HashMap<E, InternalEdge<N, E>>();
  }

  @Override
  public N opposite(N node, E edge) {
    return edgeLookup.get(edge).opposite(node);
  }

  @Override
  public boolean hasEdge(N from, N to) {
    return outgoingEdges.get(from).containsKey(to);
  }

  @Override
  public E getEdgeIfExists(N from, N to) {
    return hasEdge(from, to) ? outgoingEdges.get(from).get(to).edge : null;
  }

  private void assertContainsNode(N n, boolean contains) {
    if (outgoingEdges.containsKey(n) != contains
        || incomingEdges.containsKey(n) != contains) {
      throw new RuntimeException("Expected graph containment of node: " + n
          + " to be: " + contains + " but was: " + (!contains));
    }
  }

  public void addNode(N n) {
    assertContainsNode(n, false);
    outgoingEdges.put(n, new HashMap<N, InternalEdge<N, E>>());
    incomingEdges.put(n, new HashMap<N, InternalEdge<N, E>>());
  }

  public void addEdge(N m, N n, E edge) {
    assertContainsNode(m, true);
    assertContainsNode(n, true);
    InternalEdge<N, E> iEdge = new InternalEdge<N, E>(m, n, edge);
    outgoingEdges.get(m).put(n, iEdge);
    incomingEdges.get(n).put(m, iEdge);
    edgeLookup.put(edge, iEdge);
  }

  @Override
  public Iterable<N> nodes() {
    return outgoingEdges.keySet();
  }

  @Override
  public int numNodes() {
    return outgoingEdges.size();
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

  @Override
  public Iterable<N> outgoingNeighbors(N n) {
    return outgoingEdges.get(n).keySet();
  }

  @Override
  public Iterable<N> incomingNeighbors(N n) {
    return incomingEdges.get(n).keySet();
  }

}
