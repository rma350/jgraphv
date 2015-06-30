package com.github.rma350.jgraphv.core.factory;

import com.github.rma350.jgraphv.core.engine.Engine;
import com.github.rma350.jgraphv.core.graph.rendering.EdgeArcView;
import com.github.rma350.jgraphv.core.graph.rendering.EdgeArrowView;
import com.github.rma350.jgraphv.core.graph.rendering.EdgeLineView;
import com.github.rma350.jgraphv.core.graph.rendering.EdgeView;
import com.github.rma350.jgraphv.core.graph.rendering.GraphView;
import com.github.rma350.jgraphv.core.graph.rendering.NodeCircleView;
import com.github.rma350.jgraphv.core.graph.rendering.NodeView;
import com.github.rma350.jgraphv.core.graph.structures.DirectedGraph;
import com.github.rma350.jgraphv.core.graph.structures.Graph;
import com.github.rma350.jgraphv.core.graph.typed.Edge;
import com.github.rma350.jgraphv.core.graph.typed.Node;
import com.github.rma350.jgraphv.core.shapes.NativeShapeBuffer.BufferUsage;

/**
 * Factory methods for setting up a graph view. Useful for getting started
 * quickly.
 * 
 * Warning: not optimized for minimizing size of compiled binary and not in the
 * spirit of dependency injection.
 * 
 */
public class GraphViews {
  
  public static GraphView simpleView(Graph<Node, Edge> graph, Engine engine,
      BufferUsage bufferUsage) {
    NodeView nodeView = new NodeCircleView(graph, engine.getGL(),
        engine.getCamera(), engine.getCirclesShader(), bufferUsage);
    EdgeView edgeView = new EdgeLineView(graph, engine.getGL(),
        engine.getCamera(), engine.getLinesShader(), bufferUsage);
    return new GraphView(nodeView, edgeView);
  }
  
  public static GraphView arcView(Graph<Node, Edge> graph, Engine engine,
      BufferUsage bufferUsage) {
    NodeView nodeView = new NodeCircleView(graph, engine.getGL(),
        engine.getCamera(), engine.getCirclesShader(), bufferUsage);
    EdgeView edgeView = new EdgeArcView(graph, engine.getGL(),
        engine.getCamera(), engine.getGL().getLinMath(), engine.getArcShader(), bufferUsage);
    return new GraphView(nodeView, edgeView);
  }

  public static GraphView directedView(DirectedGraph<Node, Edge> graph,
      Engine engine, BufferUsage bufferUsage) {
    NodeView nodeView = new NodeCircleView(graph, engine.getGL(),
        engine.getCamera(), engine.getCirclesShader(), bufferUsage);
    EdgeView edgeView = new EdgeArrowView(graph, engine.getGL(),
        engine.getCamera(), engine.getGL().getLinMath(),
        engine.getTriangleShader(), bufferUsage);
    return new GraphView(nodeView, edgeView);
  }

}
