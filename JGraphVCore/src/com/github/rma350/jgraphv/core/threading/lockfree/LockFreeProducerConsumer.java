package com.github.rma350.jgraphv.core.threading.lockfree;

public class LockFreeProducerConsumer<T> {
  
  private Buffer<T> buffer;
  private Producer<T> producer;
  private Consumer<T> consumer;
  
  public Producer<T> getProducer() {
    return producer;
  }
  
  public Consumer<T> getConsumer() {
    return consumer;
  }
  
  
  /**
   * Ownership of all initial data is taken. Producer is in swap mode (copyMode
   * == false).
   */
  public LockFreeProducerConsumer(T initProducer, T initBuffer, T initConsumer, ProductionUpdater<T> updater){
    this.buffer = new Buffer<T>(initBuffer);
    this.producer = new Producer<T>(initProducer, buffer, updater);
    this.consumer = new Consumer<T>(initConsumer, buffer);
  }
  
  
  
  private static class Buffer<T> {
    private T data;
    public volatile boolean isProducersTurn;
    
    public Buffer(T data){
      this.data = data;
      isProducersTurn = true;
    }
  }
  
  public static interface ProductionUpdater<T> {
    public void updateData(T t);
  }
   
  public static class Producer<T> {
    
    private T data;
    private Buffer<T> buffer;
    private ProductionUpdater<T> updater;
    
    /** A producer that swaps on publish.*/
    public Producer(T data, Buffer<T> buffer, ProductionUpdater<T> updater){
      this.data = data;
      this.buffer = buffer;
      this.updater = updater;
    }
    
    
    
    
    /** Returns true if successfully published.*/
    public boolean publishIfBufferReady(){
      if(buffer.isProducersTurn){
        updateBuffer();
        buffer.isProducersTurn = false;
        return true;
      }
      return false;
    }

    private void updateBuffer() {
      updater.updateData(data);
      T temp = data;
      data = buffer.data;
      buffer.data = temp;
    }
    
  }
  
  public static class Consumer<T> {
    private T data;
    private Buffer<T> buffer;    
    
    public Consumer(T data, Buffer<T> buffer){
      this.data = data;
      this.buffer = buffer;
    }
    
    /**
     * For the purpose of reading data. Callers should not hold references to
     * the result, the instance may change between successive calls to
     * consumeIfBufferReady.
     */
    public T getData(){
      return data;
    }
    
    /** Returns true if successfully consumed.*/
    public boolean consumeIfBufferReady(){
      if(!buffer.isProducersTurn){
        updateBuffer();
        buffer.isProducersTurn = true;
        return true;
      }
      return false;
    }
    
    private void updateBuffer(){
      T temp = data;
      data = buffer.data;
      buffer.data = temp;
    }
  }
}
