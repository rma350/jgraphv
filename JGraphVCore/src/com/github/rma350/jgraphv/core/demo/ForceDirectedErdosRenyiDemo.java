package com.github.rma350.jgraphv.core.demo;

import com.github.rma350.jgraphv.core.engine.Engine;
import com.github.rma350.jgraphv.core.factory.GraphViews;
import com.github.rma350.jgraphv.core.graph.layout.SpringLayout;
import com.github.rma350.jgraphv.core.graph.random.ErdosRenyi;
import com.github.rma350.jgraphv.core.graph.rendering.AnimatedGraphLayout;
import com.github.rma350.jgraphv.core.graph.rendering.GraphView;
import com.github.rma350.jgraphv.core.graph.structures.UndirectedGraph;
import com.github.rma350.jgraphv.core.graph.typed.Edge;
import com.github.rma350.jgraphv.core.graph.typed.LayoutInput;
import com.github.rma350.jgraphv.core.graph.typed.Node;
import com.github.rma350.jgraphv.core.shapes.NativeShapeBuffer.BufferUsage;

public class ForceDirectedErdosRenyiDemo extends Demo {
  
  private IntParam numNodes;

  public ForceDirectedErdosRenyiDemo() {
    super("Force Directed Erdos Renyi Graph");
    numNodes = this.addIntParam("Number of nodes", 50);
  }

  @Override
  protected void setup(Engine engine) {
    float edgeProbability  = 3.0f/numNodes.getValue();
    UndirectedGraph<Node, Edge> graph = ErdosRenyi.generateErdosRenyiGraph(numNodes.getValue(), edgeProbability, engine.getGL().getLinMath());
    GraphView view = GraphViews.simpleView(graph, engine, BufferUsage.DYNAMIC);
    engine.getScene().addToScene(view);
    LayoutInput input = new LayoutInput(graph.getAdjacency(), 60);
    SpringLayout<Node,Edge> layout = new SpringLayout<Node,Edge>(input, engine.getGL().getLinMath());
    AnimatedGraphLayout animation = new AnimatedGraphLayout(engine.getGL().getLinMath(), 4000, view);
    engine.getScene().addAnimation(animation);    
    engine.getTaskRunner().submit(layout, animation.getCooperativeTaskListener());
  }

}
