package com.wiwidai.mq.rbmq;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.MessageProperties;
import com.wiwidai.mq.factory.MqApi;
import com.wiwidai.mq.factory.MqConsumer;


public class RbMqApi extends MqApi {
	private Logger logger = LoggerFactory.getLogger(RbMqApi.class);
	private Connection connection;
	
	private Channel channel;
	
	private String queueName;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	
	
	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public   JSONObject sendMessageBySingle(JSONObject ob) {
		
		JSONObject result=new JSONObject();
		result.put("status", "success");
		try {
			channel.confirmSelect();
            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, ob.toJSONString().getBytes());
            channel.waitForConfirms();  
            if(channel.waitForConfirms()) {
            	 RbMqBuilder.pool.release(connection);
                logger.info("Sent success'" + ob.toJSONString() + "'");
            }else {
            	result.put("status", "fail");
            	logger.error("send  agent fail--------------------- : 单发，没有回执 : "+ob.toJSONString()); 
            }    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result.put("status", "fail");
			logger.error("send message fail--------------------- : "+e.getMessage());
			e.printStackTrace();
			return result;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			result.put("status", "fail");
			logger.error("send message fail--------------------- : "+e.getMessage());
			e.printStackTrace();
			return result;
		}  
		return result;
	}
	
	public   JSONObject sendMessageByList(List<JSONObject> list) {
		JSONObject result=new JSONObject();
		result.put("status", "success");
	try {
		
		if(list!=null&&list.size()>0) {
			channel.confirmSelect();
			for(JSONObject json:list) {	
				    String jsons=json.toJSONString();
				    System.out.println("jsons:"+jsons);
		            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, jsons.getBytes("UTF-8"));
			}
			channel.waitForConfirmsOrDie();  
            logger.info("Sent success 批量'");        		
		    RbMqBuilder.pool.release(connection);
		
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		result.put("status", "fail");
		logger.error("send list message fail--------------------- : "+e.getMessage());
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		result.put("status", "fail");
		logger.error("send list message fail--------------------- : "+e.getMessage());
		e.printStackTrace();
	}  
	return result;
	}
	
	
	public void consumer(MqConsumer csm){
		
        try {
        //在接收消息 之前不接收其它消息
        channel.basicQos(1);
        final Channel channelFinal=channel;
        final MqConsumer cmsFinal=csm;
        Consumer consumer = new DefaultConsumer(channelFinal){
        	
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)  throws IOException {
            	
                    String message = new String(body, "UTF-8");   
                    logger.info("Received '" + message + "'");
                    cmsFinal.handleMessage(message);
                    logger.info("handle '" + message + "'");
                  channelFinal.basicAck(envelope.getDeliveryTag(), false);     
            }
           
        };

        boolean askAuto=false;
        //上面是声明消费者，这里用声明的消费者消费掉队列中的消息
        channel.basicConsume(queueName, askAuto, consumer);
        
        } catch (IOException e) {
            //失败后记录日志，返回false，代表消费失败
            logger.error("consumer message faild!",e);
        }
	}

}
