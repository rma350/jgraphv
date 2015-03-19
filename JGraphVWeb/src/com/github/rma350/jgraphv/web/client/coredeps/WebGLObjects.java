package com.github.rma350.jgraphv.web.client.coredeps;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.JavaScriptObject;

class WebGLObjects {
  
  private static final Logger logger = Logger.getLogger(WebGLObjects.class.getSimpleName());
  
  static class WebGLObject extends JavaScriptObject {
    protected WebGLObject(){}
  }
  static class WebGLBuffer extends JavaScriptObject {
    protected WebGLBuffer(){}
  }
  static class WebGLFrameBuffer extends JavaScriptObject {
    protected WebGLFrameBuffer(){}
  }
  static class WebGLProgram extends JavaScriptObject {
    protected WebGLProgram(){}
  }
  static class WebGLRenderbuffer extends JavaScriptObject {
    protected WebGLRenderbuffer(){}
  }
  static class WebGLShader extends JavaScriptObject {
    protected WebGLShader(){}
  }
  static class WebGLTexture extends JavaScriptObject {
    protected WebGLTexture(){}
  }
  static class WebGLUniformLocation extends JavaScriptObject {
    protected WebGLUniformLocation(){}
  }
  
  // TODO(rma350): these have fields, deal with them.
  // class WebGLActiveInfo extends JavaScriptObject {}
  // class WebGLShaderPrecisionFormat extends JavaScriptObject {}
  
  private List<WebGLObject> objects;
  private List<WebGLBuffer> buffers;
  private List<WebGLFrameBuffer> frameBuffers;
  private List<WebGLProgram> programs;
  private List<WebGLRenderbuffer> renderbuffers;
  private List<WebGLShader> shaders;
  private List<WebGLTexture> textures;
  private List<WebGLUniformLocation> uniformLocations;
  
  WebGLObjects(){
    objects = new ArrayList<>();
    buffers = new ArrayList<>();
    frameBuffers = new ArrayList<>();
    programs = new ArrayList<>();
    renderbuffers = new ArrayList<>();
    shaders = new ArrayList<>();
    textures = new ArrayList<>();
    uniformLocations = new ArrayList<>();
  }
  
  private static <T> void checkSize(List<T> list, String className){
    if(list.size() > 500){
      logger.log(Level.WARNING, "Created " + list.size() + " unique " + className + ", you probably don't want to do this.");
    }
  }
  
  WebGLObject getObject(int index){
    return objects.get(index);
  }
  
  int addObject(WebGLObject object){
    objects.add(object);
    checkSize(objects,WebGLObject.class.getSimpleName());
    return objects.size()-1;
  }
  
  void deleteObject(int index){
    objects.set(index, null);
  }
  
  WebGLBuffer getBuffer(int index){
    return buffers.get(index);
  }
  
  int addBuffer(WebGLBuffer buffer){
    buffers.add(buffer);
    checkSize(objects,WebGLBuffer.class.getSimpleName());
    return buffers.size()-1;
  }
  
  void deleteBuffer(int index){
    buffers.set(index, null);
  }
  
  WebGLFrameBuffer getFrameBuffer(int index){
    return frameBuffers.get(index);
  }
  
  int addFrameBuffer(WebGLFrameBuffer frameBuffer){
    frameBuffers.add(frameBuffer);
    checkSize(objects,WebGLFrameBuffer.class.getSimpleName());
    return frameBuffers.size()-1;
  }
  
  void deleteFrameBuffer(int index){
    frameBuffers.set(index, null);
  }
  
  WebGLProgram getProgram(int index){
    return programs.get(index);
  }
  
  int addProgram(WebGLProgram program){
    programs.add(program);
    checkSize(objects,WebGLProgram.class.getSimpleName());
    return programs.size()-1;
  }
  
  void deleteProgram(int index){
    programs.set(index, null);
  }
  
  WebGLRenderbuffer getRenderbuffer(int index){
    return renderbuffers.get(index);
  }
  
  int addRenderbuffer(WebGLRenderbuffer renderbuffer){
    renderbuffers.add(renderbuffer);
    checkSize(objects,WebGLRenderbuffer.class.getSimpleName());
    return renderbuffers.size()-1;
  }
  
  void deleteRenderbuffer(int index){
    renderbuffers.set(index, null);
  }
  
  WebGLShader getShader(int index){
    return shaders.get(index);
  }
  
  int addShader(WebGLShader shader){
    shaders.add(shader);
    checkSize(objects,WebGLShader.class.getSimpleName());
    return shaders.size()-1;
  }
  
  void deleteShader(int index){
    shaders.set(index, null);
  }
  
  WebGLTexture getTexture(int index){
    return textures.get(index);
  }
  
  int addTexture(WebGLTexture texture){
    textures.add(texture);
    checkSize(objects,WebGLTexture.class.getSimpleName());
    return textures.size()-1;
  }
  
  void deleteTexture(int index){
    textures.set(index, null);
  }
  
  WebGLUniformLocation getUniformLocation(int index){
    return uniformLocations.get(index);
  }
  
  int addUniformLocation(WebGLUniformLocation uniformLocation){
    uniformLocations.add(uniformLocation);
    checkSize(objects,WebGLUniformLocation.class.getSimpleName());
    return uniformLocations.size()-1;
  }
  
  void deleteUniformLocation(int index){
    uniformLocations.set(index, null);
  }
}

