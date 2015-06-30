package com.github.rma350.jgraphv.android;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.github.rma350.jgraphv.android.coredeps.AndroidGL;
import com.github.rma350.jgraphv.core.demo.AllDemos;
import com.github.rma350.jgraphv.core.engine.CheckedGL;
import com.github.rma350.jgraphv.core.engine.Engine;
import com.github.rma350.jgraphv.core.portable.Log;
import com.github.rma350.jgraphv.core.threading.ThreadedTaskRunner;

import android.opengl.GLSurfaceView;
import android.os.Debug;

public class GraphRenderer implements GLSurfaceView.Renderer {
	
	private Engine mEngine;
	private long lastFrameNanos = 0;
	private int frames = 0;
	
	public Engine getEngine(){
	  return mEngine;	  
	}

	@Override
	public void onDrawFrame(GL10 unused) {
	  long currentTimeNanos = System.nanoTime();
	  Log.out.d("Renderer", "Frame time: " + (currentTimeNanos- lastFrameNanos)/(1000*1000));
		mEngine.drawFrame();
		//Log.out.d("Renderer", "Draw time: " + (System.nanoTime()- lastFrameNanos)/(1000*1000));
		if(lastFrameNanos != 0){
		  //if(frames == 0){
		  //Debug.startMethodTracing("engineUpdate");
		  //}
		  mEngine.update((currentTimeNanos - lastFrameNanos)/(1000*1000));
		  //frames++;
		  //if(frames >= 10){
		  //Debug.stopMethodTracing();
		  //}
		  //Log.out.d("Renderer", "Update time: " + (System.nanoTime()- lastFrameNanos)/(1000*1000));
		}
		lastFrameNanos = currentTimeNanos;
	}

	@Override
	public void onSurfaceChanged(GL10 unused, int width, int height) {
		mEngine.onResize(width, height);
	}
	
	public void setDemo(int index){
	  mEngine.reset();
	  AllDemos.allDemos().get(index).run(mEngine);
	}

	@Override
	public void onSurfaceCreated(GL10 unused, EGLConfig arg1) {
		mEngine = new Engine(new CheckedGL(new AndroidGL()), new ThreadedTaskRunner());
		setDemo(0);
	}

}
