package com.github.rma350.jgraphv.core.graph.random;

import com.github.rma350.jgraphv.core.graph.structures.UndirectedGraph;
import com.github.rma350.jgraphv.core.graph.typed.Edge;
import com.github.rma350.jgraphv.core.graph.typed.Node;
import com.github.rma350.jgraphv.core.portable.LinMath;

public class ErdosRenyi {
  
  public static UndirectedGraph<Node,Edge> generateErdosRenyiGraph(int numNodes, float edgeProbability, LinMath math){
    UndirectedGraph<Node,Edge> ans = new UndirectedGraph<Node,Edge>();
    Node[] nodes = new Node[numNodes];
    for(int i = 0; i < numNodes; i++ ){
      Node node = new Node(math);
      node.position.set((float)(Math.random()*600), (float)(Math.random()*600));
      node.radius = 20;
      ans.getAdjacency().addNode(node);
      nodes[i] = node;
    }
   
    for(int i = 0; i < numNodes; i++){      
      for(int j = i+1; j < numNodes; j++){
        if(Math.random() < edgeProbability){
          Edge edge = new Edge();
          edge.preferredLength = 60;
          ans.getAdjacency().addEdge(nodes[i], nodes[j], edge);
        }
      }
    }
    return ans;
  }

}
