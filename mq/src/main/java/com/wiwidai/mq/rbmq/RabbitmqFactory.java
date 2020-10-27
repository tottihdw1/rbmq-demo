package com.wiwidai.mq.rbmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wiwidai.mq.bean.MqRbParam;
import com.wiwidai.mq.obpool.factory.ObjectFactory;

public class RabbitmqFactory implements  ObjectFactory<Connection>
{
	protected final Logger logger = LogManager.getLogger(this.getClass());
    private MqRbParam param;
    public  RabbitmqFactory(MqRbParam param) {
		this.param=param;
	}
	
	public Connection createNew() {
		// TODO Auto-generated method stub
		 ConnectionFactory factory = new ConnectionFactory();
         //设置需要连接的RabbitMQ地址，这里指向本机
	         factory.setHost(param.getHost());
	         factory.setVirtualHost(param.getvHost());
	         factory.setUsername(param.getAccount());
	         factory.setPassword(param.getPassword());
	         Connection connection = null;
	        //尝试获取一个连接 
				try {
					connection = (Connection) factory.newConnection();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error("connection create fail--------------------- : "+e.getMessage());
					e.printStackTrace();

				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					logger.error("connection create fail--------------------- : "+e.getMessage());
					e.printStackTrace();
				}
			
		return connection;
	}

}
