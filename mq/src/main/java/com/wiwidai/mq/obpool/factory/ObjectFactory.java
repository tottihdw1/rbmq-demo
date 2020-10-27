package com.wiwidai.mq.obpool.factory;



public interface ObjectFactory <T>
{

 public abstract T createNew();
}