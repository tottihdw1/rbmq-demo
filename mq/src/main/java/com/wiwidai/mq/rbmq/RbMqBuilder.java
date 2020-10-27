package com.wiwidai.mq.rbmq;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wiwidai.mq.bean.MqRbParam;
import com.wiwidai.mq.factory.MqApi;
import com.wiwidai.mq.factory.MqBuilder;
import com.wiwidai.mq.obpool.BoundedBlockingPool;
import com.wiwidai.mq.obpool.Pool;

public class RbMqBuilder implements MqBuilder {
	
	protected final Logger logger = LogManager.getLogger(this.getClass());
	public static ConcurrentHashMap<String,Channel> map=new ConcurrentHashMap<String,Channel>();
	public static Pool < Connection > pool;
	private MqRbParam mqParam;
	private Connection connection;
	private Channel channel ;
	private RbMqApi api;
	
	RbMqBuilder(){
		
	}
	
    RbMqBuilder(MqRbParam param){
    	mqParam=param;
	}
	
	public void buildConnection() {
		// TODO Auto-generated method stub
		pool =new BoundedBlockingPool <Connection> (3,new RabbitmqValidator(),new RabbitmqFactory(mqParam));
	    connection= pool.get();
	    logger.info("connection create success!!!!!!!!!!!!!! ");
	}

	public void buildChannel() {
		boolean durable=true;
		// TODO Auto-generated method stub
		try {
	    String id=connection.getId();
		if(map!=null&&StringUtils.isNotEmpty(id)&&map.get(id)!=null&&map.get(id).isOpen()) {
			channel=map.get(id);
		}else {
			connection.setId(UUID.randomUUID().toString());
			channel = connection.createChannel();
	        channel.queueDeclare(mqParam.getQueueName(), durable, false, false, null);
	        map.put(connection.getId(), channel);
		}
	
        logger.info("channel create success!!!!!!!!!!!!!! ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("channel create fail--------------------- : "+e.getMessage());
			e.printStackTrace();
			
		} 
	}

	public <T extends MqApi> T builder() {
		// TODO Auto-generated method stub
		api=new RbMqApi();
		api.setConnection(connection);
		api.setChannel(channel);
		api.setQueueName(mqParam.getQueueName());
		return (T) api;
	}

}
