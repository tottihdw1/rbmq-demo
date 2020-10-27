package com.wiwidai.mq.demo;

import com.wiwidai.mq.rbmq.RbMqApi;

public class GetDemo {
	
	public static RbMqApi api;

      static {
		if(api==null) {
			 api=InitDemo.getInit();
		}

	}
	
	

}
