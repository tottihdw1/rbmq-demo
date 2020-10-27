package com.wiwidai.mq.demo;

import com.wiwidai.mq.bean.MqRbParam;
import com.wiwidai.mq.factory.MqProvider;
import com.wiwidai.mq.rbmq.RbMqApi;
import com.wiwidai.mq.rbmq.RbMqFactory;

public class InitDemo {
	
	private static RbMqApi rmApi;
	static {
		MqProvider provider=new RbMqFactory();
		MqRbParam param=new MqRbParam();
		param.setHost("39.107.69.160");
		param.setvHost("wwd");
		param.setAccount("wwd");
		param.setPassword("wwd20180827");
		param.setQueueName("wwd-loan");
		provider.createFactory(param);
		rmApi=provider.getApi();
	}
	
	private InitDemo() {
		
	}
	
	public  static  RbMqApi getInit() {
		return Holder.demo.rmApi;
	}
	
	private static class Holder {
		private static InitDemo demo=new InitDemo();
	}

}
