package com.github.rma350.jgraphv.desktop;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.PixelFormat;

import com.github.rma350.jgraphv.core.Camera;
import com.github.rma350.jgraphv.core.CheckedGL;
import com.github.rma350.jgraphv.core.Engine;
import com.github.rma350.jgraphv.core.SceneDemos;
import com.github.rma350.jgraphv.desktop.coredeps.LwjGL;

public class Main {

  private Engine engine;  

  public void start() {
    
    try {
      int width = 800;
      int height = 600;
      Display.setDisplayMode(new DisplayMode(width, height));
      Display.create(new PixelFormat(8,0,0,8));
      engine = new Engine(new CheckedGL(new LwjGL()));
      engine.onResize(width, height);      
      GL11.glEnable(GL20.GL_VERTEX_PROGRAM_POINT_SIZE);
      SceneDemos.makeGraph(engine,true);
      //SceneDemos.twoPoints(engine);
      //SceneDemos.connectPoints(engine);
    } catch (LWJGLException e) {
      e.printStackTrace();
      System.exit(0);
    }

    // init OpenGL here

    while (!Display.isCloseRequested()) {

      // render OpenGL here
      engine.drawFrame();
      pollInput();
      Display.update();
    }

    Display.destroy();
  }
  
  private void pollInput(){
    int scroll = Mouse.getDWheel();
    if(scroll > 0) {
      // Zoom in
      int x = Mouse.getX();
      int y = Mouse.getY();
      System.out.println("x: " + x + " y: " + y);
      engine.zoom(1.25f, x, y);
      
    }
    else if (scroll < 0){
      // Zoom out
      int x = Mouse.getX();
      int y = Mouse.getY();
      engine.zoom(.8f, x, y);

    }
    // Process Dragging
    if(Mouse.isButtonDown(0)){      
      int dx = Mouse.getDX();
      int dy = Mouse.getDY();
      Camera cam = engine.getCamera(); 
      cam.translateBy(-dx, -dy);
    }
    // process clicking
    while (Mouse.next()) {
      if (Mouse.getEventButton() == 0) {
        int x = Mouse.getX();
        int y = Mouse.getY();  
     // Either a click or an unclick
        if (Mouse.getEventButtonState()) {
          System.out.println("Click: " + x + " " + y);
        } else {
          System.out.println("Release: " + x + " " + y);
          //engine.getCamera().moveTo(x, y);
        }
      }
    }
  }

  public static void main(String[] argv) {
    Main displayExample = new Main();
    displayExample.start();
  }
}
