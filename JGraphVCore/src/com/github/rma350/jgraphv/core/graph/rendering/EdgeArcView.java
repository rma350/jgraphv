package com.github.rma350.jgraphv.core.graph.rendering;

import com.github.rma350.jgraphv.core.engine.Camera;
import com.github.rma350.jgraphv.core.graph.structures.Graph;
import com.github.rma350.jgraphv.core.graph.structures.InternalEdge;
import com.github.rma350.jgraphv.core.graph.typed.Edge;
import com.github.rma350.jgraphv.core.graph.typed.Node;
import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.shapes.ArcBuffer;
import com.github.rma350.jgraphv.core.shapes.ArcBufferBuilder;
import com.github.rma350.jgraphv.core.shapes.ArcBuilder;
import com.github.rma350.jgraphv.core.shapes.ArcShader;
import com.github.rma350.jgraphv.core.shapes.Arcs;
import com.github.rma350.jgraphv.core.shapes.NativeShapeBuffer.BufferUsage;

public class EdgeArcView implements EdgeView {

  private Graph<Node, Edge> graph;

  private ArcBuilder arcBuilder;
  private ArcBufferBuilder bufferBuilder;
  private ArcBuffer arcBuffer;
  private Arcs arcs;


    public EdgeArcView(Graph<Node, Edge> graph, GL gl, Camera camera,
      LinMath math, ArcShader arcShader, BufferUsage bufferUsage) {
    this.graph = graph;
    
    
    arcBuilder = new ArcBuilder(math);
    bufferBuilder = new ArcBufferBuilder(1);
    arcBuilder.iArcWidth = 3;
    arcBuilder.iArcRadians = (float)Math.PI/3;
    arcBuffer = new ArcBuffer(gl, graph.getAdjacency().numEdges(), bufferUsage);
    updateEdgePos();
    arcs = new Arcs(camera, arcShader, arcBuffer, 1, 0, 0, 1);
    
  }

  private void updateEdgePos() {
    int edgeIndex = 0;
    for (Edge edge : graph.getAdjacency().edges()) {
      InternalEdge<Node, Edge> internalEdge = graph.getAdjacency()
          .internalEdge(edge);
      arcBuilder.iFrom.copy(internalEdge.start.position);
      arcBuilder.iTo.copy(internalEdge.end.position);
      
      arcBuilder.computeArc();
      bufferBuilder.updateArc(arcBuilder, edgeIndex);
      edgeIndex++;
    }
    arcBuffer.checkedPut(bufferBuilder.getBuffer(), 0,
        bufferBuilder.sizeInFloats());
  }

  public void update() {
    updateEdgePos();
    arcBuffer.bindRebufferUnbind();
  }

  public void draw() {
    this.arcs.draw();
  }

}

