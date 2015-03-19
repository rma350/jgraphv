package com.github.rma350.jgraphv.web.client.coredeps;


import com.github.rma350.jgraphv.core.portable.GL;
import com.github.rma350.jgraphv.core.portable.Mat4;
import com.github.rma350.jgraphv.core.portable.NativeFloatBuffer;
import com.github.rma350.jgraphv.web.client.coredeps.WebGLObjects.WebGLBuffer;
import com.github.rma350.jgraphv.web.client.coredeps.WebGLObjects.WebGLProgram;
import com.github.rma350.jgraphv.web.client.coredeps.WebGLObjects.WebGLShader;
import com.github.rma350.jgraphv.web.client.coredeps.WebGLObjects.WebGLUniformLocation;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayUtils;
import com.google.gwt.typedarrays.client.Float32ArrayNative;

public class WebGL implements GL{
  
  
  public static void alert(float[] numbers) {
    JsArrayNumber jsArray = JsArrayUtils.readOnlyJsArray(numbers);
    doAlert(jsArray);
  }  
  private static native void doAlert(JsArrayNumber numbers) /*-{
    var sum = 0;
    for( var i = 0; i < numbers.length; i++) {
      sum+= numbers[i];
    }
    $wnd.alert("sum is:" + sum);
  }-*/;
  private static native int getCanvasHeight(String canvasElementId) /*-{
    var canvas = $doc.getElementById(canvasElementId);
    return canvas.height;
  }-*/;
  
  private static native int getCanvasWidth(String canvasElementId) /*-{
    var canvas = $doc.getElementById(canvasElementId);
    return canvas.width;
  }-*/;
  
  private static native void glAttachShader(JavaScriptObject gl, WebGLProgram program, WebGLShader shader) /*-{
    gl.attachShader(program,shader);
  }-*/;
  
  private static native void glBindBuffer(JavaScriptObject gl,
      int target, WebGLBuffer buffer) /*-{
    gl.bindBuffer(target, buffer);
  }-*/;
  
  private static native void glBlendFunc(JavaScriptObject gl, int sfactor, int dfactor) /*-{
    gl.blendFunc(sfactor,dfactor);
  }-*/;
  private static native void glBufferData(JavaScriptObject gl, int target, Float32ArrayNative nativeArray, int usage) /*-{
    gl.bufferData(target, nativeArray, usage);
  }-*/;
  
  private static native void glClear(JavaScriptObject gl, int mask) /*-{
    gl.clear(mask);
  }-*/;
  
  private static native void glClearColor(JavaScriptObject gl, float r, float g, float b, float a) /*-{
    gl.clearColor(r,g,b,a);
  }-*/;
  
  private static native void glCompileShader(JavaScriptObject gl, WebGLShader shaderProgram) /*-{
    gl.compileShader(shaderProgram);
  }-*/;
  
  private static native JavaScriptObject glContext(String canvasElementId, boolean debugMode) /*-{
    try {                              
      var canvas = $doc.getElementById(canvasElementId);
      //var ctx = canvas.getContext("webgl") || canvas.getContext("experimental-webgl");
      var ctx = canvas.getContext("webgl", { antialias: true}) || canvas.getContext("experimental-webgl", { antialias: true});
      try { 
        if(debugMode){
          $wnd.WebGLDebugUtils.init(ctx);
        }
      }
      catch (e) {
        alert("Warning: Could not initialise debug for WebGL.");
      }
      return ctx;
    } catch (e) {
      alert("Could not initialise WebGL, sorry :-(");
      return null;
    }
  }-*/;
  
  private static native WebGLBuffer glCreateBuffer(JavaScriptObject gl) /*-{
    return gl.createBuffer();
  }-*/;
  
  private static native WebGLProgram glCreateProgram(JavaScriptObject gl) /*-{
    return gl.createProgram();
  }-*/;
  
  private static native WebGLShader glCreateShader(JavaScriptObject gl, int shaderType) /*-{
    return gl.createShader(shaderType);
  }-*/;
  
  private static native void glDrawArrays(JavaScriptObject gl, int mode,
      int first, int count) /*-{
		gl.drawArrays(mode, first, count);
  }-*/;
  
  private static native void glEnable(JavaScriptObject gl, int cap) /*-{
    gl.enable(cap)
  }-*/;

  private static native void glEnableVertexAttribArray(JavaScriptObject gl,
      int glLocation) /*-{
		gl.enableVertexAttribArray(glLocation)
  }-*/;
  
  private static native int glGetAttribLocation(JavaScriptObject gl,
      WebGLProgram program, String attribute) /*-{
    return gl.getAttribLocation(program, attribute);
  }-*/;
  
  private static native int glGetError(JavaScriptObject gl) /*-{
    return gl.getError();
  }-*/;
  
  private static native String webGlDebugUtilsEnumToString(int error) /*-{
    return $wnd.WebGLDebugUtils.glEnumToString(error);
  }-*/;
  
  private static native WebGLUniformLocation glGetUniformLocation(JavaScriptObject gl,
      WebGLProgram program, String uniform) /*-{
    return gl.getUniformLocation(program, uniform);
  }-*/; 
  
  private static native void glLineWidth(JavaScriptObject gl,
      float width) /*-{
    gl.lineWidth(width)
  }-*/;
  
  private static native void glLinkProgram(JavaScriptObject gl,
      WebGLProgram program) /*-{
    gl.linkProgram(program);
  }-*/;
  
  private static native String glProgramInfoLog(JavaScriptObject gl,
      WebGLProgram program) /*-{
    return gl.getProgramInfoLog(program);
  }-*/;
  
  private static native int glProgramParameter(JavaScriptObject gl,
      WebGLProgram program, int pname) /*-{
    return gl.getProgramParameter(program, pname);
  }-*/;
  
  private static native String glShaderInfoLog(JavaScriptObject gl,
      WebGLShader shader) /*-{
    return gl.getShaderInfoLog(shader);
  }-*/;
  
  private static native int glShaderParameter(JavaScriptObject gl,
      WebGLShader shaderProgram, int pname) /*-{
    return gl.getShaderParameter(shaderProgram, pname);
  }-*/;
  
  private static native void glShaderSource(JavaScriptObject gl,
      WebGLShader shaderProgram, String shaderCode) /*-{
    gl.shaderSource(shaderProgram, shaderCode);
  }-*/;
  
  private static native void glUniform4f(JavaScriptObject gl,
      WebGLUniformLocation glLocation, float x, float y, float z, float w) /*-{
    gl.uniform4f(glLocation, x,y,z,w);
  }-*/;
  
  private static native void glUniformMatrix4fv(JavaScriptObject gl, WebGLUniformLocation uniform, WebMat4 mat) /*-{
    gl.uniformMatrix4fv(uniform, false, mat);
  }-*/;
  
  private static native void glUseProgram(JavaScriptObject gl,
      WebGLProgram program) /*-{
    gl.useProgram(program);
  }-*/;
  
  private static native void glValidateProgram(JavaScriptObject gl, WebGLProgram program) /*-{
    gl.validateProgram(program);
  }-*/;
  
  private static native void glVertexAttribPointer(JavaScriptObject gl, int glLocation, int size, boolean normalized, int stride, int offset) /*-{
    gl.vertexAttribPointer(glLocation, size, gl.FLOAT, normalized, stride, offset);
  }-*/;
  
  private static native void glViewport(JavaScriptObject gl, int x, int y, int width, int height) /*-{
    gl.viewport(x,y,width,height);
  }-*/;
   
  private static native int kGL_ARRAY_BUFFER(JavaScriptObject gl) /*-{
    return gl.ARRAY_BUFFER;
  }-*/;
  
  private static native int kGL_BLEND(JavaScriptObject gl) /*-{
		return gl.BLEND;
  }-*/;
  
  
  private static native int kGL_COLOR_BUFFER_BIT(JavaScriptObject gl) /*-{
    return gl.COLOR_BUFFER_BIT;
  }-*/;
  
  private static native int kGL_COMPILE_STATUS(JavaScriptObject gl) /*-{
    return gl.COMPILE_STATUS;
  }-*/;
  
  private static native int kGL_FLOAT(JavaScriptObject gl) /*-{
    return gl.FLOAT;
  }-*/;
  
  private static native int kGL_FRAGMENT_SHADER(JavaScriptObject gl) /*-{
    return gl.FRAGMENT_SHADER;
  }-*/;
  
  
  private static native int kGL_LINES(JavaScriptObject gl) /*-{
		return gl.LINES;
  }-*/;
  
  private static native int kGL_LINK_STATUS(JavaScriptObject gl) /*-{
    return gl.LINK_STATUS;
  }-*/;
  
  
  private static native int kGL_ONE_MINUS_SRC_ALPHA(JavaScriptObject gl) /*-{
		return gl.ONE_MINUS_SRC_ALPHA;
  }-*/;
  
  private static native int kGL_POINTS(JavaScriptObject gl) /*-{
    return gl.POINTS;
  }-*/;
  
  
  private static native int kGL_SRC_ALPHA(JavaScriptObject gl) /*-{
		return gl.SRC_ALPHA;
  }-*/;
  
  private static native int kGL_STATIC_DRAW(JavaScriptObject gl) /*-{
    return gl.STATIC_DRAW;
  }-*/;
  
  private static native int kGL_TRIANGLES(JavaScriptObject gl) /*-{
    return gl.TRIANGLES;
  }-*/;
  
  private static native int kGL_VERTEX_SHADER(JavaScriptObject gl) /*-{
    return gl.VERTEX_SHADER;
  }-*/;
  
  private static native int kGL_DYNAMIC_DRAW(JavaScriptObject gl) /*-{
    return gl.DYNAMIC_DRAW;
  }-*/;
  
  private static native int kGL_NO_ERROR(JavaScriptObject gl) /*-{
    return gl.NO_ERROR;
  }-*/;
  
  private static native int kGL_VALIDATE_STATUS(JavaScriptObject gl) /*-{
    return gl.VALIDATE_STATUS;
  }-*/;  

  
  private String canvasElementId;  
  private JavaScriptObject gl;  
  private WebGLObjects webGLObjects;
  private WebMath webLinMath;
  private boolean debugMode;
  
  public WebGL(String canvasElementId, boolean debugMode){
    this.debugMode = debugMode;
    gl = glContext(canvasElementId, debugMode);
    this.canvasElementId = canvasElementId;
    webGLObjects = new WebGLObjects();
    webLinMath = new WebMath();
    
  }
  
  public int getCanvasHeight(){
    return getCanvasHeight(canvasElementId);
  }
  
  public int getCanvasWidth(){
    return getCanvasWidth(canvasElementId);
  }
  
  @Override
  public void glAttachShader(int program, int shader) {
    glAttachShader(gl,webGLObjects.getProgram(program),webGLObjects.getShader(shader));
  }
  
  @Override
  public void glBindBuffer(int target, int buffer) {
    glBindBuffer(gl,target, webGLObjects.getBuffer(buffer));
  }
  
  @Override
  public void glBlendFunc(int sfactor, int dfactor) {
    glBlendFunc(gl,sfactor,dfactor);
  }
  
  @Override
  public void glBufferData(int target, NativeFloatBuffer nativeBuffer, int usage){
    glBufferData(gl,target,((WebNativeFloatBuffer)nativeBuffer).getNativeArray(),usage);
  }
  
  @Override
  public void glClear(int mask) {
    glClear(gl,mask);
  }
  
  @Override
  public void glClearColor(float r, float g, float b, float a) {
    glClearColor(gl,r,g,b,a);
  }
  
  @Override
  public void glCompileShader(int shader) {
    glCompileShader(gl, webGLObjects.getShader(shader));
  }  
  
  @Override
  public int glCreateBuffer() {
    return webGLObjects.addBuffer(glCreateBuffer(gl));
  }
  
  @Override
  public int glCreateProgram() {
    WebGLProgram program = glCreateProgram(gl);
    return this.webGLObjects.addProgram(program);
  }
  
  @Override
  public int glCreateShader(int shaderType) {
    WebGLShader shader = glCreateShader(gl,shaderType);
    return this.webGLObjects.addShader(shader);
  }
  
  @Override
  public void glDrawArrays(int mode, int first, int count) {
    glDrawArrays(gl,mode,first,count);
  }
  
  @Override
  public void glEnable(int cap) {
    glEnable(gl,cap);
  }
  
  @Override
  public void glEnableVertexAttribArray(int glLocation) {
    glEnableVertexAttribArray(gl,glLocation);
  }
  
  @Override
  public int glGetAttribLocation(int program, String attribute) {
    return glGetAttribLocation(gl, webGLObjects.getProgram(program), attribute);
  }
  
  @Override
  /** Warning: this must only be called once per shader.*/
  public int glGetUniformLocation(int program, String uniform) {
    return this.webGLObjects.addUniformLocation(glGetUniformLocation(gl,webGLObjects.getProgram(program), uniform));
  }
  
  @Override
  public void glLineWidth(float width) {
    glLineWidth(gl,width);
  }

  @Override
  public void glLinkProgram(int program) {
    glLinkProgram(gl,this.webGLObjects.getProgram(program));
  }
  
  @Override
  public String glGetProgramInfoLog(int program) {
    return glProgramInfoLog(gl,this.webGLObjects.getProgram(program));
  }

  @Override
  public void glGetProgramiv(int program, int pname, int[] params) {
    params[0] = glProgramParameter(gl,this.webGLObjects.getProgram(program),pname);
  }
  

  @Override
  public String glGetShaderInfoLog(int shader) {
    return glShaderInfoLog(gl,this.webGLObjects.getShader(shader));
  }

  @Override
  public void glGetShaderiv(int shader, int pname, int[] params) {
    params[0]= glShaderParameter(gl,this.webGLObjects.getShader(shader),pname);
  }

  @Override
  public void glShaderSource(int shader, String shaderCode) {
    glShaderSource(gl,this.webGLObjects.getShader(shader),shaderCode);
  }

  @Override
  public void glUniform4f(int glLocation, float x, float y, float z, float w) {
    glUniform4f(gl,this.webGLObjects.getUniformLocation(glLocation),x,y,z,w);
  }
  
  @Override
  public void glUniformMatrix4(int glLocation, Mat4 mat) {
    WebGLUniformLocation uniform = this.webGLObjects.getUniformLocation(glLocation);
    glUniformMatrix4fv(gl,uniform,(WebMat4)mat);
  }
  
  @Override
  public void glUseProgram(int program) {
    glUseProgram(gl,this.webGLObjects.getProgram(program));
  }
  
  @Override
  // Call glVertexAttribPointer with type=GL_FLOAT
  public void glVertexAttribPointer(int glLocation, int size, boolean normalized, int stride, int offset) {
    glVertexAttribPointer(gl,glLocation, size, normalized, stride, offset );
  }
  
  @Override
  public void glViewport(int x, int y, int width, int height) {
    if(x != 0){
      throw new RuntimeException("x must be zero in glViewport but was " + x);
    }
    if(y != 0){
      throw new RuntimeException("y must be zero in glViewport but was " + y);
    }
    glViewport(gl,x,y,width,height);
  }
  
  @Override
  public int kGL_ARRAY_BUFFER(){
    return kGL_ARRAY_BUFFER(gl);
  }
  
  @Override
  public int kGL_BLEND() {
    return kGL_BLEND(gl);
  }
  
  @Override
  public int kGL_COLOR_BUFFER_BIT(){
    return kGL_COLOR_BUFFER_BIT(gl);
  }
  
  @Override
  public int kGL_COMPILE_STATUS() {
    return kGL_COMPILE_STATUS(gl);
  }
  
  @Override
  public int kGL_FLOAT() {
    return kGL_FLOAT(gl);
  }
  
  @Override
  public int kGL_FRAGMENT_SHADER() {
    return kGL_FRAGMENT_SHADER(gl);
  }
  
  @Override
  public int kGL_LINES() {
    return kGL_LINES(gl);
  }
  
  @Override
  public int kGL_LINK_STATUS() {
    return kGL_LINK_STATUS(gl);
  }
  
  @Override
  public int kGL_ONE_MINUS_SRC_ALPHA() {
    return kGL_ONE_MINUS_SRC_ALPHA(gl);
  }
  
  @Override
  public int kGL_POINTS() {
    return kGL_POINTS(gl);
  }
  
  @Override
  public int kGL_SRC_ALPHA() {
    return kGL_SRC_ALPHA(gl);
  }
  
  @Override
  public int kGL_STATIC_DRAW() {
    return kGL_STATIC_DRAW(gl);
  }
  
  @Override
  public int kGL_TRIANGLES() {
    return kGL_TRIANGLES(gl);
  }
  
  @Override
  public int kGL_VERTEX_SHADER() {
    return kGL_VERTEX_SHADER(gl);
  }
  @Override
  public WebNativeFloatBuffer createFloatBuffer(int numFloats) {
    return new WebNativeFloatBuffer(numFloats);
  }
  @Override
  public WebMath getLinMath() {
    return webLinMath;
  }
  @Override
  public int glGetError() {
    return glGetError(gl);
  }
  @Override
  public void glValidateProgram(int program) {
    glValidateProgram(gl,this.webGLObjects.getProgram(program));
  }
  @Override
  public String gluErrorString(int errorCode) {
    if(this.debugMode){
      return webGlDebugUtilsEnumToString(errorCode);
    }
 else {
      return "String for error " + errorCode
          + " is only available in debug mode"
          + " (Set in constructor of WebGL). "
          + " Be sure to include webgl-debug.js";
    }
    
  }
  @Override
  public int kGL_DYNAMIC_DRAW() {
    return kGL_DYNAMIC_DRAW(gl);
  }
  @Override
  public int kGL_NO_ERROR() {
    return kGL_NO_ERROR(gl);
  }
  @Override
  public int kGL_VALIDATE_STATUS() {
    return kGL_VALIDATE_STATUS(gl);
  }
  

}
