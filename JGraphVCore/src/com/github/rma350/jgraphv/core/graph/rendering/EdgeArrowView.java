package com.github.rma350.jgraphv.core.graph.rendering;

import com.github.rma350.jgraphv.core.engine.Camera;
import com.github.rma350.jgraphv.core.graph.structures.DirectedGraph;
import com.github.rma350.jgraphv.core.graph.structures.Graph;
import com.github.rma350.jgraphv.core.graph.structures.InternalEdge;
import com.github.rma350.jgraphv.core.graph.typed.Edge;
import com.github.rma350.jgraphv.core.graph.typed.Node;
import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Vec2;
import com.github.rma350.jgraphv.core.shapes.DirectedArrowBuffer;
import com.github.rma350.jgraphv.core.shapes.DirectedLines;
import com.github.rma350.jgraphv.core.shapes.NativeShapeBuffer.BufferUsage;
import com.github.rma350.jgraphv.core.shapes.TriangleShader;

public class EdgeArrowView implements EdgeView {

  private Graph<Node, Edge> graph;

  private DirectedArrowBuffer edgeBuffer;
  private DirectedLines edgeLines;
  
  private float arrowWidth;
  private float arrowHeadWidth;
  private float arrowHeadLength;

  private Vec2 lSrcVec;
  private Vec2 lDestVec;

  public EdgeArrowView(DirectedGraph<Node, Edge> graph, GL gl, Camera camera,
      LinMath math, TriangleShader triangleShader, BufferUsage bufferUsage) {
    this.graph = graph;
    int edgeCount = graph.getAdjacency().numEdges();
    arrowWidth = 2;
    arrowHeadWidth = 5;
    arrowHeadLength = 10;
    lSrcVec = math.createVec2();
    lDestVec = math.createVec2();
    edgeBuffer = new DirectedArrowBuffer(gl, edgeCount, bufferUsage);
    updateEdgePos();
    edgeLines = new DirectedLines(camera, triangleShader,
        edgeBuffer.getTriangles(), 1, 0, 0, 1);
  }

  private void updateEdgePos() {
    int edgeIndex = 0;
    for (Edge edge : graph.getAdjacency().edges()) {
      InternalEdge<Node, Edge> internalEdge = graph.getAdjacency()
          .internalEdge(edge);
      lSrcVec.copy(internalEdge.start.position);
      lDestVec.copy(internalEdge.end.position);
      edgeBuffer.setArrow(edgeIndex, lSrcVec, lDestVec, arrowWidth,
          arrowHeadWidth, arrowHeadLength, (internalEdge.start.radius * .9f),
          internalEdge.end.radius); // A hack to prevent white space.
      edgeIndex++;
    }
    edgeBuffer.writeToNative();
  }

  public void update() {
    updateEdgePos();
    edgeBuffer.getTriangles().bindRebufferUnbind();
  }

  public void draw() {
    this.edgeLines.draw();
  }

}
