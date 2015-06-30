package com.github.rma350.jgraphv.core.graph.typed;

import com.github.rma350.jgraphv.core.portable.LinMath;
import com.github.rma350.jgraphv.core.portable.Vec2;

public class Node {
  
  public Node(LinMath math){
    position = math.createVec2();
  }
  
  public Vec2 position;
  public float radius;

}
