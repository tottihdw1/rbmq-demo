package com.wiwidai.mq.bean;

import java.io.Serializable;

public class MqRbParam extends BaseParam implements Serializable {
	private String host;
	private String vHost;
	private String queueName;

	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getvHost() {
		return vHost;
	}
	public void setvHost(String vHost) {
		this.vHost = vHost;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	
	

}
