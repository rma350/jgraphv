package com.github.rma350.jgraphv.core.graph.random;

import com.github.rma350.jgraphv.core.animation.AnimFuncs;
import com.github.rma350.jgraphv.core.graph.structures.DirectedGraph;
import com.github.rma350.jgraphv.core.graph.structures.UndirectedGraph;
import com.github.rma350.jgraphv.core.graph.typed.Edge;
import com.github.rma350.jgraphv.core.graph.typed.Node;
import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Vec2;

public class Geometric {
  
  public static Node[] randomPositionNodes(int numNodes, Vec2 minPos, Vec2 maxPos, float nodeRadius, LinMath math){
    Node[] nodes = new Node[numNodes];
    for(int i = 0; i < numNodes; i++ ){
      Node node = new Node(math);
      node.position.setX(AnimFuncs.lerp(minPos.x(), maxPos.x(), (float)Math.random()));
      node.position.setY(AnimFuncs.lerp(minPos.y(), maxPos.y(), (float)Math.random()));
      node.radius = nodeRadius;
      nodes[i] = node;
    }
    return nodes;
  }
  
  public static UndirectedGraph<Node,Edge> generateUndirectedGeometricGraph(Node[] nodes, float minDistForEdge, LinMath math){
    UndirectedGraph<Node,Edge> ans = new UndirectedGraph<Node,Edge>();
    for(Node node: nodes){
      ans.getAdjacency().addNode(node);
    }
    final float minDistForEdgeSq = minDistForEdge*minDistForEdge;
    for(int i = 0; i < nodes.length; i++){      
      for(int j = i+1; j < nodes.length; j++){
        if(math.distSquared(nodes[i].position, nodes[j].position) < minDistForEdgeSq){
          Edge edge = new Edge();
          ans.getAdjacency().addEdge(nodes[i], nodes[j], edge);
        }
      }
    }
    return ans;
  }
  
  public static DirectedGraph<Node,Edge> generateDirectedGeometricGraph(Node[] nodes, float minDistForEdge, boolean bothDirections, LinMath math){
    DirectedGraph<Node,Edge> ans = new DirectedGraph<Node,Edge>();
    for(Node node: nodes){
      ans.getAdjacency().addNode(node);
    }
    final float minDistForEdgeSq = minDistForEdge*minDistForEdge; 
    for(int i = 0; i < nodes.length; i++){      
      for(int j = i+1; j < nodes.length; j++){
        if(math.distSquared(nodes[i].position, nodes[j].position) < minDistForEdgeSq){
          Edge edge = new Edge();
          ans.getAdjacency().addEdge(nodes[i], nodes[j], edge);
          if(bothDirections){
            Edge otherEdge = new Edge();
            ans.getAdjacency().addEdge(nodes[j], nodes[i], otherEdge);
          }
        }
      }
    }
    return ans;
  }

}
