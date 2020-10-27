package com.wiwidai.mq.rbmq;

import java.io.IOException;

import com.rabbitmq.client.Connection;
import com.wiwidai.mq.obpool.Pool.Validator;

public class RabbitmqValidator implements Validator <Connection>
{

	 public boolean isValid(Connection con)
	 
	    {
	        if(con == null)
	        {
	            return false;
	        }

	        	return con.isOpen();
	    }

	    public void invalidate(Connection con)
	    {
	    	 try {
				con.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
	
	
}
