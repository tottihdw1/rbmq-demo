package com.wiwidai.mq.factory;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public abstract class MqApi {

	public  abstract JSONObject sendMessageByList(List<JSONObject> list);
	
	public  abstract JSONObject sendMessageBySingle(JSONObject ob);
	
	public  abstract void consumer(MqConsumer csm);
	
}
