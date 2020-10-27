package com.wiwidai.mq.bean;

public class BaseParam {
	private String account;
	private String password;
	
	private boolean isSingleton=true;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isSingleton() {
		return isSingleton;
	}
	public void setSingleton(boolean isSingleton) {
		this.isSingleton = isSingleton;
	}
	
	

}
