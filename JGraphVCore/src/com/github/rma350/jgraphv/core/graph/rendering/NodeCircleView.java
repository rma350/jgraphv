package com.github.rma350.jgraphv.core.graph.rendering;

import com.github.rma350.jgraphv.core.engine.Camera;
import com.github.rma350.jgraphv.core.graph.structures.Graph;
import com.github.rma350.jgraphv.core.graph.typed.Edge;
import com.github.rma350.jgraphv.core.graph.typed.Node;
import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.shapes.Circles;
import com.github.rma350.jgraphv.core.shapes.CirclesBuffer;
import com.github.rma350.jgraphv.core.shapes.CirclesShader;
import com.github.rma350.jgraphv.core.shapes.NativeShapeBuffer.BufferUsage;

public class NodeCircleView implements NodeView {
  
  private Graph<Node,Edge> graph;
  
  private float[] lNodePos;
  private CirclesBuffer nodeBuffer;
  private Circles nodeCircles;
  
  
  public NodeCircleView(Graph<Node,Edge> graph, GL gl,Camera camera, CirclesShader circlesShader, BufferUsage bufferUsage){
    this.graph = graph;
    int numNodes = graph.getAdjacency().numNodes();
    nodeBuffer = new CirclesBuffer(gl, numNodes, bufferUsage);    
    lNodePos = CirclesBuffer.makeBuffer(numNodes);    
    updateNodePos();
    nodeCircles = new Circles(camera, circlesShader, nodeBuffer, 0, 0, 1, 1);
    
  }
  
  private void updateNodePos(){
    int nodeIndex = 0;
    for (Node node : graph.getAdjacency().nodes()) {

      CirclesBuffer.setCircle(lNodePos, nodeIndex, node.position.x(),
          node.position.y(), node.radius);
      nodeIndex++;
    }
    nodeBuffer.checkedPutShapes(lNodePos);
  }
  
  public void update(){
    updateNodePos();
    nodeBuffer.bindRebufferUnbind();
  }
  
  public void draw(){
    nodeCircles.draw();
  }


}
