package com.github.rma350.jgraphv.core;

import java.util.ArrayList;
import java.util.List;

public class Scene {
  
  private List<Drawable> drawables;
  
  public Scene(){
    this.drawables = new ArrayList<Drawable>();
  }
  
  public void addToScene(Drawable drawable){
    this.drawables.add(drawable);
  }
  
  public void draw(){
    for(Drawable drawable: drawables){
      drawable.draw();
    }
  }
  
  public void clear(){
    drawables.clear();
  }

}
