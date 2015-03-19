package com.github.rma350.jgraphv.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Log;
import com.github.rma350.jgraphv.core.portable.Vec2;

public class SceneDemos {
  
  private static final String TAG = SceneDemos.class.getSimpleName();
  
  public static  void twoPoints(Engine engine) {
    PointsBuffer points = new PointsBuffer(
        engine.getGL(), new float[] { 10, 20, 10, 400, 200, 15 });
    engine.getScene().addToScene(
        new Points(engine.getCamera(), engine.getPointsShader(),
            points, 0, 1, 0, 1));
  }

  public static  void nodes(Engine engine) {
    int numCircles = 1000;
    CirclesBuffer circles = new CirclesBuffer(
        engine.getGL(), numCircles);
    float[] buffer = CirclesBuffer.makeBuffer(numCircles);
    for (int i = 0; i < numCircles; i++) {
      CirclesBuffer.setCircle(buffer, i, (float) Math.random() * 2000,
          (float) Math.random() * 2000, (float) Math.random() * 20);
    }

    circles.checkedPutShapes(buffer, 0);

    Circles circs = new Circles(engine.getCamera(),
        engine.getCirclesShader(), circles, 0, 0, 1, 1);

    engine.getScene().addToScene(circs);
  }
  
  public static void connectPoints(Engine engine) {
    LinMath math = engine.getGL().getLinMath();
    DirectedArrowBuffer arrowBuffer = new DirectedArrowBuffer(engine.getGL(), 2);
    float arrowWidth = 5;
    float arrowHeadWidth = 15;
    float arrowHeadLength = 30;
    //arrowBuffer.setArrow(0, math.createVec2(100, 100), math.createVec2(300,300), arrowWidth, arrowHeadWidth, arrowHeadLength);
    arrowBuffer.setArrow(0, math.createVec2(10, 20), math.createVec2(400,200), arrowWidth, arrowHeadWidth, arrowHeadLength,10,30);
    arrowBuffer.setArrow(1, math.createVec2(200, 400), math.createVec2(400,200), arrowWidth, arrowHeadWidth, arrowHeadLength,15,30);
    arrowBuffer.writeToNative();
    DirectedLines directedLines = new DirectedLines(engine.getCamera(), engine.getTriangleShader(), arrowBuffer.getTriangles(), 1, 0, 0, 1);
    engine.getScene().addToScene(directedLines);
    
    /*LinesBuffer lineData = new LinesBuffer(
        engine.getGL(), 2);
    float[] buffer = LinesBuffer.makeBuffer(2);
    LinesBuffer.setLine(buffer, 0, 10, 20, 400, 200);
    LinesBuffer.setLine(buffer,1, 200, 400, 400, 200);
    lineData.checkedPutShapes(buffer, 0);
    Lines lines = new Lines(engine.getCamera(),
        engine.getLinesShader(), lineData, 1, 0, 0, 1, 10);
    engine.getScene().addToScene(lines);*/
    
   
    CirclesBuffer circles = new CirclesBuffer(
        engine.getGL(), 3);
    float[] circleBuffer = CirclesBuffer.makeBuffer(3);
    CirclesBuffer.setCircle(circleBuffer, 0, 10, 20, 10);
    CirclesBuffer.setCircle(circleBuffer, 1, 200, 400, 15);
    CirclesBuffer.setCircle(circleBuffer, 2, 400, 200, 30);
    circles.checkedPutShapes(circleBuffer, 0);

    Circles circs = new Circles(engine.getCamera(),
        engine.getCirclesShader(), circles, 0, 0, 1, 1);
    engine.getScene().addToScene(circs);
    
    /*PointsBuffer points = new PointsBuffer(
        engine.getGL(), new float[] { 10, 20, 10, 400, 200, 15, 200, 400, 30 });
    engine.getScene().addToScene(
        new Points(engine.getCamera(), engine.getPointsShader(),
            points, 0, 1, 0, 1));
     */
  }
  
  private static class Node {
    public float x;
    public float y;
    public float r;
    
    public static float distSquared(Node a, Node b){
      return (a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y);  
    }
  }
  
  private static class Graph {
    private List<Node> nodes;
    private Map<Node,List<Node>> edges;
    
    public Graph(){
      nodes = new ArrayList<Node>();
      edges = new HashMap<Node,List<Node>>();
    }
    
    public void addNode(Node node){
      nodes.add(node);
      edges.put(node, new ArrayList<Node>());
    }
    
    public void addEdge(Node src, Node dest){
      edges.get(src).add(dest);
    }
    
    public int edgeCount(){
      int ans = 0;
      for(List<Node> target : edges.values()){
        ans += target.size();
      }
      return ans;
    }
    
    public List<Node> getNodes(){
      return nodes;
    }
    public Map<Node,List<Node>> getEdges(){
      return edges;
    }
  }
  
  private static Graph makeRandomGraph(int numNodes, int gridSize, double connectionDistance){
    Graph graph = new Graph();
    for(int i = 0; i < numNodes; i++){
      Node node = new Node();
      node.x =(float) Math.random() * gridSize;
      node.y = (float) Math.random() * gridSize;
      node.r = 10f;
      graph.addNode(node);
    }
    for(int i = 0; i< numNodes; i++){
      Node ni = graph.getNodes().get(i);
      for(int j = i+1; j < numNodes; j++){
        Node nj = graph.getNodes().get(j);
        if(Node.distSquared(ni, nj) < connectionDistance*connectionDistance){
          graph.addEdge(ni, nj);
        }
      }
    }
    return graph;
  }
  
  
  public static void makeGraph(Engine engine, boolean directed) {
    Graph graph = makeRandomGraph(5000, 5000, 100); //(5,100,50);//
    
    // Draw the edges
    
    int edgeCount = graph.edgeCount();
    
    if (directed) {
      LinMath math = engine.getGL().getLinMath();
      DirectedArrowBuffer arrowBuffer = new DirectedArrowBuffer(engine.getGL(),
          edgeCount);
      float arrowWidth = 2;
      float arrowHeadWidth = 5;
      float arrowHeadLength = 10;
      int nextEdge = 0;
      Vec2 srcVec = math.createVec2();
      Vec2 destVec = math.createVec2();
      for (Node src : graph.nodes) {
        srcVec.setX(src.x);
        srcVec.setY(src.y);
        for (Node dest : graph.getEdges().get(src)) {
          destVec.setX(dest.x);
          destVec.setY(dest.y);
          arrowBuffer.setArrow(nextEdge, srcVec,
              destVec, arrowWidth, arrowHeadWidth,
              arrowHeadLength, (src.r*.9f) /* A hack to prevent white space.*/, dest.r);
          nextEdge++;
        }
      }
      Log.out.i(TAG, "Graph has " + graph.nodes.size() + " nodes and "
          + edgeCount + " edges.");
      if (edgeCount != nextEdge) {
        throw new RuntimeException("Expected : " + edgeCount
            + " edges but found: " + nextEdge);
      }
      arrowBuffer.writeToNative();
      DirectedLines directedLines = new DirectedLines(engine.getCamera(),
          engine.getTriangleShader(), arrowBuffer.getTriangles(), 1, 0, 0, 1);
      engine.getScene().addToScene(directedLines);
    } else {
      LinesBuffer lineData = new LinesBuffer(engine.getGL(), edgeCount);
      float[] lineBuffer = LinesBuffer.makeBuffer(edgeCount);
      int nextEdge = 0;
      for (Node src : graph.nodes) {
        for (Node dest : graph.getEdges().get(src)) {
          // Log.out.d(TAG,"Adding line: (" + src.x + ", " + src.y + ") (" +
          // dest.x + ", " + dest.y + ")");
          LinesBuffer.setLine(lineBuffer, nextEdge, src.x, src.y, dest.x,
              dest.y);
          nextEdge++;
        }
      }
      Log.out.i(TAG, "Graph has " + graph.nodes.size() + " nodes and "
          + edgeCount + " edges.");
      if (edgeCount != nextEdge) {
        throw new RuntimeException("Expected : " + edgeCount
            + " edges but found: " + nextEdge);
      }
      lineData.checkedPutShapes(lineBuffer, 0);
      Lines lines = new Lines(engine.getCamera(), engine.getLinesShader(),
          lineData, 1, 0, 0, 1, 10);
      engine.getScene().addToScene(lines);
    }
    
    // Draw the nodes
    int numCircles = graph.getNodes().size();
    CirclesBuffer circles = new CirclesBuffer(
        engine.getGL(), numCircles);
    float[] circleBuffer = CirclesBuffer.makeBuffer(numCircles);
    for (int i = 0; i < numCircles; i++) {
      Node ni = graph.getNodes().get(i);
      CirclesBuffer.setCircle(circleBuffer, i, ni.x, ni.y, ni.r);
    }

    circles.checkedPutShapes(circleBuffer, 0);

    Circles circs = new Circles(engine.getCamera(),
        engine.getCirclesShader(), circles, 0, 0, 1, 1);
    engine.getScene().addToScene(circs);
    
    
    
   
  }

}
