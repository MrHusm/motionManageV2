package com.manage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jmind.core.cache.xmemcached.Memcached;
import jmind.core.manager.MemcachedManager;

public class MemcachUtil {
	private final static Logger logger = LoggerFactory.getLogger(MemcachUtil.class);
	private static Memcached mem = null;
	public static Memcached getMem(){
		if(mem == null){
			synchronized (MemcachUtil.class) {
				try {
					return MemcachedManager.getInstance().getResource("ikanshu");
				} catch (Exception e) {
					logger.error("error:{}", e.getMessage());
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		System.out.println(MemcachUtil.getMem().set("test", "ok.."));
		System.out.println(MemcachUtil.getMem().get("test"));
	}
}
