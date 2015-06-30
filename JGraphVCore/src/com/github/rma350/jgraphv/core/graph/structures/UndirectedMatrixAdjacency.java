package com.github.rma350.jgraphv.core.graph.structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UndirectedMatrixAdjacency<N, E> implements UndirectedAdjacency<N , E >  {
  
  private Map<N,Integer> nodeIndex;
  private List<N> nodesInOrder;
  private List<List<E>> adjacencyMatrix;
  
  public List<List<E>> getAdjacencyMatrix(){
    return adjacencyMatrix;
  }
  
  public List<N> getNodesInOrder(){
    return nodesInOrder;
  }
  
  public int getPositionOfNode(N node){
    return nodeIndex.get(node);
  }
  
  
  public UndirectedMatrixAdjacency(UndirectedAdjacency<N,E> other){
    int numNodes = other.numNodes();
    int nodeInd = 0;
    nodeIndex = new HashMap<N,Integer>();
    nodesInOrder = new ArrayList<N>();
    adjacencyMatrix = new ArrayList<List<E>>();
    for(N node: other.nodes()){
      nodeIndex.put(node, nodeInd++);
      nodesInOrder.add(node);
      adjacencyMatrix.add(new ArrayList<E>());
    }
    for(int i = 0; i < numNodes; i++){
      N ni = nodesInOrder.get(i);
      List<E> iNeighbors = adjacencyMatrix.get(i);
      for(int j = 0; j < numNodes; j++){
        N nj = nodesInOrder.get(j);
        if(i==j){
          iNeighbors.add(null);
        }
        else{
          iNeighbors.add(other.getEdgeIfExists(ni, nj));
        }
      }
    }
  }

  @Override
  public N opposite(N node, E edge) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean hasEdge(N m, N n) {
    return this.adjacencyMatrix.get(this.nodeIndex.get(m)).get(this.nodeIndex.get(n)) != null;
  }

  @Override
  public E getEdgeIfExists(N m, N n) {
    return this.adjacencyMatrix.get(this.nodeIndex.get(m)).get(this.nodeIndex.get(n));
  }

  @Override
  public void addNode(N node) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addEdge(N m, N n, E edge) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterable<N> nodes() {
    return nodesInOrder;
  }

  @Override
  public Iterable<N> neighborsOf(N n) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterable<E> edges() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int numNodes() {
    return this.nodesInOrder.size();
  }

  @Override
  public int numEdges() {
    throw new UnsupportedOperationException();
  }

  @Override
  public InternalEdge<N, E> internalEdge(E edge) {
    throw new UnsupportedOperationException();
  }

}
