package com.github.rma350.jgraphv.core.graph.rendering;

import com.github.rma350.jgraphv.core.engine.Camera;
import com.github.rma350.jgraphv.core.graph.structures.Graph;
import com.github.rma350.jgraphv.core.graph.structures.InternalEdge;
import com.github.rma350.jgraphv.core.graph.typed.Edge;
import com.github.rma350.jgraphv.core.graph.typed.Node;
import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.shapes.Lines;
import com.github.rma350.jgraphv.core.shapes.LinesBuffer;
import com.github.rma350.jgraphv.core.shapes.LinesShader;
import com.github.rma350.jgraphv.core.shapes.NativeShapeBuffer.BufferUsage;

public class EdgeLineView implements EdgeView {
  
  private Graph<Node,Edge> graph;
  
  private float[] lEdgePos;
  private LinesBuffer edgeBuffer;
  private Lines edgeLines;
  
  public EdgeLineView(Graph<Node,Edge> graph, GL gl,Camera camera, LinesShader linesShader, BufferUsage bufferUsage){
    this.graph = graph;
    
    int numEdges = graph.getAdjacency().numEdges();
    edgeBuffer = new LinesBuffer(gl, numEdges, bufferUsage);
    lEdgePos = LinesBuffer.makeBuffer(numEdges);
    updateEdgePos();
    edgeLines = new Lines(camera, linesShader, edgeBuffer, 1, 0, 0, 1, 4);
  }
  
  private void updateEdgePos() {
    int edgeIndex = 0;
    for (Edge edge : graph.getAdjacency().edges()) {
      InternalEdge<Node, Edge> internalEdge = graph.getAdjacency()
          .internalEdge(edge);
      LinesBuffer.setLine(lEdgePos, edgeIndex, internalEdge.start.position.x(),
          internalEdge.start.position.y(), internalEdge.end.position.x(),
          internalEdge.end.position.y());
      edgeIndex++;
    }
    edgeBuffer.checkedPutShapes(lEdgePos);
  }
  
  public void update(){
    updateEdgePos();
    edgeBuffer.bindRebufferUnbind();
  }
  
  public void draw(){
    this.edgeLines.draw();
  }

}
