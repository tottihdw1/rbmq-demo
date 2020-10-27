package com.wiwidai.mq.factory;

import com.wiwidai.mq.bean.MqRbParam;

public interface MqBuilder {
	
	public void buildConnection();
	public void buildChannel();
	
	public <T extends MqApi> T builder();

}
