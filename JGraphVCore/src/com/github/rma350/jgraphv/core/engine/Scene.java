package com.github.rma350.jgraphv.core.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.github.rma350.jgraphv.core.animation.Animation;
import com.github.rma350.jgraphv.core.shapes.Drawable;

public class Scene {
  
  private List<Drawable> drawables;
  private Set<Animation> animations;
  
  public Scene(){
    this.drawables = new ArrayList<Drawable>();
    this.animations = new HashSet<Animation>();
  }
  
  public void addToScene(Drawable drawable){
    this.drawables.add(drawable);
  }
  
  public void addAnimation(Animation animation){
    this.animations.add(animation);
  }
  
  public void update(long ellapsedMs){
    Iterator<Animation> it = animations.iterator();
    while(it.hasNext()){
      Animation nextAnim = it.next();
      if(nextAnim.update(ellapsedMs)){
        it.remove();
      }
    }
  }
  
  public void draw(){
    for(Drawable drawable: drawables){
      drawable.draw();
    }
  }
  
  public void clear(){
    drawables.clear();
    animations.clear();
  }

}
