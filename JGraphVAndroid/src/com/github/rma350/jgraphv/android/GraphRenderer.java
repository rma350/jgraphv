package com.github.rma350.jgraphv.android;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.github.rma350.jgraphv.android.coredeps.AndroidGL;
import com.github.rma350.jgraphv.core.CheckedGL;
import com.github.rma350.jgraphv.core.Engine;
import com.github.rma350.jgraphv.core.SceneDemos;

import android.opengl.GLSurfaceView;

public class GraphRenderer implements GLSurfaceView.Renderer {
	
	private Engine mEngine;
	
	public Engine getEngine(){
	  return mEngine;
	}

	@Override
	public void onDrawFrame(GL10 unused) {
		mEngine.drawFrame();
		
	}

	@Override
	public void onSurfaceChanged(GL10 unused, int width, int height) {
		mEngine.onResize(width, height);
	}

	@Override
	public void onSurfaceCreated(GL10 unused, EGLConfig arg1) {
		mEngine = new Engine(new CheckedGL(new AndroidGL()));
		SceneDemos.makeGraph(mEngine,false);
		//SceneDemos.connectPoints(mEngine);
		// SceneDemos.twoPoints(mEngine);
	}

}
