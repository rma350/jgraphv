package com.github.rma350.jgraphv.core.demo;

import java.util.ArrayList;
import java.util.List;

public class AllDemos {
  
  public static List<Demo> allDemos(){
    List<Demo> ans = new ArrayList<Demo>();
    ans.add(new ArcDemo());
    ans.add(new ConnectTwoPointsDemo());
    ans.add(new DirectedGeometricGraph());
    ans.add(new ForceDirectedErdosRenyiDemo());
    ans.add(new GraphArcDemo());
    ans.add(new RandomCirclesDemo());    
    ans.add(new TwoPointsDemo());
    ans.add(new UndirectedGeometricGraph());
    return ans;
  }

}
