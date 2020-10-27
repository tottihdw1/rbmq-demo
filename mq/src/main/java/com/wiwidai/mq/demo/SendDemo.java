package com.wiwidai.mq.demo;


import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class SendDemo {
	


	public static void send() {
		JSONObject obj=new JSONObject();
		obj.put("name", "hdw");
		obj.put("password", "123456");
	/*	JSONObject result1=GetDemo.api.sendMessageBySingle(obj);
		if(result1!=null&&result1.get("status").equals("success")) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
		*/
		List<JSONObject> list=new ArrayList<JSONObject>();
		for(int i=0;i<10;i++) {
			JSONObject objdd=new JSONObject();
			objdd.put("hello", "中国");
			objdd.put("num",i);
			list.add(objdd);
		}
		GetDemo.api.sendMessageByList(list);

		
	}

}
