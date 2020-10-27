package com.wiwidai.mq.factory;

import com.wiwidai.mq.bean.BaseParam;

public interface MqProvider {
	public <T extends BaseParam> void createFactory(T t);
	
	public <T extends MqApi> T getApi();
}
