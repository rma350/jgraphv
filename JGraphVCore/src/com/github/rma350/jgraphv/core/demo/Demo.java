package com.github.rma350.jgraphv.core.demo;

import java.util.ArrayList;
import java.util.List;

import com.github.rma350.jgraphv.core.engine.Engine;

public abstract class Demo {

  private String name;
  private Engine engine;
  private List<IntParam> intParams;
  private List<FloatParam> floatParams;
  private List<BooleanParam> booleanParams;

  public Demo(String name) {
    this.name = name;
    intParams = new ArrayList<IntParam>();
    floatParams = new ArrayList<FloatParam>();
    booleanParams = new ArrayList<BooleanParam>();
  }

  protected IntParam addIntParam(String name, int initValue) {
    IntParam ans = new IntParam(name, initValue);
    intParams.add(ans);
    return ans;
  }

  protected FloatParam addFloatParam(String name, float initValue) {
    FloatParam ans = new FloatParam(name, initValue);
    floatParams.add(ans);
    return ans;
  }
  
  protected BooleanParam addBooleanParam(String name, boolean initValue) {
    BooleanParam ans = new BooleanParam(name, initValue);
    booleanParams.add(ans);
    return ans;
  }

  public String getName() {
    return name;
  }

  public void run(Engine engine) {
    this.engine = engine;
    setup(engine);
  }

  protected abstract void setup(Engine engine);

  public void restart() {
    engine.reset();
    setup(engine);
  }

  // Override this for more sophisticated behavior.
  protected void onIntParamChanged(int oldValue, IntParam param) {
    restart();
  }

  // Override this for more sophisticated behavior.
  protected void onFloatParamChanged(float oldValue, FloatParam param) {
    restart();
  }
  
  // Override this for more sophisticated behavior.
  protected void onBooleanParamChanged(boolean initValue, BooleanParam param){
    restart();
  }

  class Param<T> {

    private T value;
    private String name;

    Param(String name, T initValue) {
      this.name = name;
      this.value = initValue;
    }

    public String getName() {
      return name;
    }

    public T getValue() {
      return value;
    }

    protected T internalSetValue(T value) {
      T oldValue = this.value;
      this.value = value;
      return oldValue;
    }

  }

  public class IntParam extends Param<Integer> {

    IntParam(String name, int initValue) {
      super(name, initValue);
    }

    public void setValue(int value) {
      onIntParamChanged(internalSetValue(value), this);
    }
  }

  public class FloatParam extends Param<Float> {

    FloatParam(String name, float initValue) {
      super(name, initValue);
    }

    public void setValue(float value) {
      onFloatParamChanged(internalSetValue(value), this);
    }
  }
  
  public class BooleanParam extends Param<Boolean> {

    public BooleanParam(String name, boolean initValue) {
      super(name, initValue);
    }

    public void setValue(boolean value) {
      onBooleanParamChanged(internalSetValue(value), this);
    }
  }

}
