package com.github.rma350.jgraphv.core.graph.rendering;

import com.github.rma350.jgraphv.core.shapes.Drawable;

public class GraphView implements Drawable {
  
  private NodeView nodeView;
  private EdgeView edgeView;
  
  public GraphView(NodeView nodeView, EdgeView edgeView){
    this.nodeView = nodeView;
    this.edgeView = edgeView;
  }
  
  @Override
  public void draw() {
    edgeView.draw();
    nodeView.draw();
  }
  
  public void update(){
    edgeView.update();
    nodeView.update();
  }

}
