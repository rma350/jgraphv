package com.github.rma350.jgraphv.core.graph.typed;

import com.github.rma350.jgraphv.core.graph.layout.SpringLayout;
import com.github.rma350.jgraphv.core.graph.structures.UndirectedAdjacency;
import com.github.rma350.jgraphv.core.portable.Vec2;

public class LayoutInput implements SpringLayout.Input<Node,Edge>{
  
  private float preferredLengthNotNeihbors;
  private UndirectedAdjacency<Node, Edge> adjacency;
  
  public LayoutInput(UndirectedAdjacency<Node, Edge> adjacency,
      float preferredLengthNotNeihbors) {
    this.adjacency = adjacency;
    this.preferredLengthNotNeihbors = preferredLengthNotNeihbors;
  }

  @Override
  public float preferredLength(Edge edge) {
    return edge.preferredLength;
  }

  @Override
  public float preferredLengthNotNeighbors() {
    return preferredLengthNotNeihbors;
  }

  @Override
  public UndirectedAdjacency<Node, Edge> adjacency() {
    return adjacency;
  }

  @Override
  public Vec2 initialLocation(Node node) {
    return node.position;
  }
  
}
