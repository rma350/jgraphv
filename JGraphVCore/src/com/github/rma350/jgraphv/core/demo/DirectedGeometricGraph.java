package com.github.rma350.jgraphv.core.demo;

import com.github.rma350.jgraphv.core.engine.Engine;
import com.github.rma350.jgraphv.core.factory.GraphViews;
import com.github.rma350.jgraphv.core.graph.random.Geometric;
import com.github.rma350.jgraphv.core.graph.rendering.GraphView;
import com.github.rma350.jgraphv.core.graph.structures.DirectedGraph;
import com.github.rma350.jgraphv.core.graph.typed.Edge;
import com.github.rma350.jgraphv.core.graph.typed.Node;
import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Vec2;
import com.github.rma350.jgraphv.core.shapes.NativeShapeBuffer.BufferUsage;

public class DirectedGeometricGraph extends Demo {
  
  private IntParam numNodes;
  private FloatParam nodeSize;
  private FloatParam minDistForEdge;

  public DirectedGeometricGraph() {
    super("Directed Geometric Graph");
    numNodes = new IntParam("Number of nodes", 500);
    nodeSize = new FloatParam("Node size", 10);
    minDistForEdge = new FloatParam("Minimum Distance for Edge",100);
  }

  @Override
  protected void setup(Engine engine) {
    LinMath math =  engine.getGL().getLinMath();
    Vec2 minPos = math.createVec2(0,0);
    Vec2 maxPos = math.createVec2(2000, 2000);
    Node[] nodes = Geometric.randomPositionNodes(numNodes.getValue(), minPos, maxPos, nodeSize.getValue(), math);
    DirectedGraph<Node,Edge> graph = Geometric.generateDirectedGeometricGraph(nodes, minDistForEdge.getValue(), false, math);
    GraphView view = GraphViews.directedView(graph, engine, BufferUsage.STATIC);
    engine.getScene().addToScene(view);
  }

}
