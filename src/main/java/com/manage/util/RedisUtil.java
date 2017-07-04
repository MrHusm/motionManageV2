package com.manage.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import redis.clients.jedis.Jedis;
import base.ikanshu.util.JedisUtil;

public class RedisUtil {
protected static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	
	private static Properties props = new Properties(); 
	private static String name = null;
	private static String mhost = null;
	private static String mport = null;
	private static String mauth = null;
	static{
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("datasource.properties"));
			name = props.getProperty("redis.name");
			mhost = props.getProperty("redis.mhost");
			mport = props.getProperty("redis.mport");
			mauth = props.getProperty("redis.mauth");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取jedis
	 * @return
	 */
	public static Jedis getJedis(){
		Jedis jedis=null;
		try {
			jedis = JedisUtil.getMasterResource(name,mhost, Integer.parseInt(mport), mauth, 0);
		} catch (Exception e) {
			e.printStackTrace();
			if(jedis!=null){
				JedisUtil.returnBrokenResource(name,jedis);
			}
			jedis=null;
		}
		return jedis;
	}
	
	/**
	 * 关闭jedis
	 * @param jedis
	 */
	public static void closeJedis(Jedis jedis){
		if(jedis!=null){
			JedisUtil.returnResource(name,jedis);
		}
	}
	
	/**
	 * 异常时关闭jedis
	 * @param jedis
	 */
	public static void closeBrokenJedis(Jedis jedis){
		if(jedis!=null){
			JedisUtil.returnBrokenResource(name,jedis);
		}
		jedis=null;
	}
	
	/**
	 * 序列化对象
	 * @param value
	 * @return
	 */
	public static byte[] serialize(Object value) {  
        if (value == null) {  
            throw new NullPointerException("Can't serialize null");  
        }  
        byte[] rv=null;  
        ByteArrayOutputStream bos = null;  
        ObjectOutputStream os = null;  
        try {  
            bos = new ByteArrayOutputStream();  
            os = new ObjectOutputStream(bos);  
            os.writeObject(value);  
            os.close();  
            bos.close();  
            rv = bos.toByteArray();  
        } catch (IOException e) {  
            throw new IllegalArgumentException("Non-serializable object", e);  
        } finally {  
            close(os);  
            close(bos);  
        }  
        return rv;  
    }

	/**
	 * 反序列化
	 * @param in
	 * @return
	 */
    public static Object deserialize(byte[] in) {  
        Object rv=null;  
        ByteArrayInputStream bis = null;  
        ObjectInputStream is = null;  
        try {  
            if(in != null) {  
                bis=new ByteArrayInputStream(in);  
                is=new ObjectInputStream(bis);  
                rv=is.readObject();  
                is.close();  
                bis.close();  
            }  
        } catch (IOException e) {  
            logger.warn("Caught IOException decoding %d bytes of data",  
                    in == null ? 0 : in.length, e);  
        } catch (ClassNotFoundException e) {  
            logger.warn("Caught CNFE decoding %d bytes of data",  
                    in == null ? 0 : in.length, e);  
        } finally {  
           close(is);  
           close(bis);  
        }  
        return rv;  
    } 
    
    /**
     * 关闭资源
     * @param closeable
     */
    public static void close(Closeable closeable) {  
        if (closeable != null) {  
            try {  
                closeable.close();  
            } catch (Exception e) {  
                logger.info("Unable to close %s", closeable, e);  
            }  
        }  
    }
}
