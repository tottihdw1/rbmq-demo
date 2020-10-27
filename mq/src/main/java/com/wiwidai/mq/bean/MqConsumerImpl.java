package com.wiwidai.mq.bean;

import com.wiwidai.mq.factory.MqConsumer;

public class MqConsumerImpl implements MqConsumer {

	public void handleMessage(String message) {
		// TODO Auto-generated method stub
	
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%do: "+message);
		
	}

}
