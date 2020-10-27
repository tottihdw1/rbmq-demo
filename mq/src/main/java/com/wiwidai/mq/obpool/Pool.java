package com.wiwidai.mq.obpool;

/**
 * 对象池
 * @author totti
 *
 * @param <T>
 */
public interface Pool<T>
{

	 public <T>  T get();

	 public void release(T t);

	 public void shutdown();
 
 

 public static interface Validator < T >
 {

  public boolean isValid(T t);


  public void invalidate(T t);
 } 
 
}