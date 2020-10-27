package com.wiwidai.mq.factory;

public interface MqConsumer {
	
	void handleMessage(String message);

}
