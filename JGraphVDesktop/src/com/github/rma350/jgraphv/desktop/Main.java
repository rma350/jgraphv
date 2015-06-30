package com.github.rma350.jgraphv.desktop;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.PixelFormat;

import com.github.rma350.jgraphv.core.demo.AllDemos;
import com.github.rma350.jgraphv.core.engine.Camera;
import com.github.rma350.jgraphv.core.engine.CheckedGL;
import com.github.rma350.jgraphv.core.engine.Engine;
import com.github.rma350.jgraphv.core.threading.ThreadedTaskRunner;
import com.github.rma350.jgraphv.desktop.coredeps.LwjGL;

public class Main {

  private Engine engine;
  private int currentDemo = 0;
  
  
  private void updateDemo(int change){
    int target = currentDemo + change;
    int clampTarget = Math.max(0, target);
    clampTarget = Math.min(clampTarget, AllDemos.allDemos().size()-1);
    if(clampTarget != currentDemo){
      setDemo(clampTarget);
    }
  }
  
  private void setDemo(int demo){
    engine.reset();
    currentDemo = demo;
    AllDemos.allDemos().get(demo).run(engine);
  }

  public void start() {
    
    try {
      int width = 800;
      int height = 600;
      Display.setDisplayMode(new DisplayMode(width, height));
      Display.create(new PixelFormat(8,0,0,8));
      engine = new Engine(new CheckedGL(new LwjGL()), new ThreadedTaskRunner());
      engine.onResize(width, height);      
      GL11.glEnable(GL20.GL_VERTEX_PROGRAM_POINT_SIZE);
      setDemo(currentDemo);
    } catch (LWJGLException e) {
      e.printStackTrace();
      System.exit(0);
    }

    // init OpenGL here

    final long startTime = System.nanoTime();
    long previousTime = startTime;
    while (!Display.isCloseRequested()) {

      long currentTime = System.nanoTime();
      long deltaMs = (currentTime - previousTime)/1000000;
      // System.out.println(deltaMs);
      previousTime = currentTime;
      engine.update(deltaMs);
      // render OpenGL here
      engine.drawFrame();
      pollInput();
      Display.update();
      Display.sync(60);
    }
    engine.onStop();
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
    // process keyboard events
    int deltaEvent = 0;
    while(Keyboard.next()){
      // process on key up only
      if (!Keyboard.getEventKeyState()) {
        if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
          deltaEvent++;
        } else if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
          deltaEvent--;
        }
      }
    }
    if(deltaEvent != 0){
      updateDemo(deltaEvent);
    }
  }

  public static void main(String[] argv) {
    Main displayExample = new Main();
    displayExample.start();
  }
}
