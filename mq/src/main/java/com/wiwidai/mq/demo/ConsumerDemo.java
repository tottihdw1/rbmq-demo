package com.wiwidai.mq.demo;

import com.wiwidai.mq.bean.MqConsumerImpl;
import com.wiwidai.mq.factory.MqConsumer;

public class ConsumerDemo {

	public static void consumer() {
		// TODO Auto-generated method stub
		MqConsumer consumer=new MqConsumerImpl();
		GetDemo.api.consumer(consumer);
	}

}
