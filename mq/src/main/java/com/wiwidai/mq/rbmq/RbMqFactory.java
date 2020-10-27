package com.wiwidai.mq.rbmq;

import com.wiwidai.mq.bean.BaseParam;
import com.wiwidai.mq.bean.MqRbParam;
import com.wiwidai.mq.factory.MqApi;
import com.wiwidai.mq.factory.MqBuilder;
import com.wiwidai.mq.factory.MqProvider;

public class RbMqFactory implements MqProvider {

	private  RbMqApi api;
	
	public <T extends BaseParam> void createFactory(T t) {
		// TODO Auto-generated method stub
		MqBuilder builder=new RbMqBuilder((MqRbParam)t);
		builder.buildConnection();
		builder.buildChannel();
		api=builder.builder();
	}
	
	
	public <T extends MqApi> T  getApi() {
		return (T) api;
	}


}
