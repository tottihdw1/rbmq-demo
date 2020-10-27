package com.wiwidai.mq.obpool;


/**
 * 
 * @author totti
 *
 * @param <T>
 */
abstract class AbstractPool <T> implements Pool <T>
{

 public final void release(T t)
 {
  if(isValid(t))
  {
   returnToPool(t);
  }
  else
  {
   handleInvalidReturn(t);
  }
 }

 protected abstract void handleInvalidReturn(T t);

 protected abstract void returnToPool(T t);

 protected abstract boolean isValid(T t);


}