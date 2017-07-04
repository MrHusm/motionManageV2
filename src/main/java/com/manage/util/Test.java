package com.manage.util;

import redis.clients.jedis.Jedis;
import base.ikanshu.util.JedisTool;
import base.ikanshu.util.JedisUtil;



public class Test {

	public static void main(String[] args) {
		Jedis jedis = null;
		String key="phqd_" + 1;
		try {
			JedisUtil.getMasterResource("test", "60.28.209.235", 7380,
					"zwsc@bqzx", 0);
			jedis = JedisTool.getMasterResource();
//			jedis.del(key);
			String[] llist=new String[2];
			Gson gson=new Gson();
			llist[0]=gson.toJson(new BookModel("236353", 100));
			llist[1]=gson.toJson(new BookModel("50019702", 90));
//			jedis.lpush("phqd_" + 1, llist);
			System.out.println(jedis.lrange("phqd_" + 1, 0, 2));
		} catch (Exception e) {
			e.printStackTrace();
			if (jedis != null) {
				JedisTool.returnBrokenResource(jedis);
			}
			jedis = null;
		} finally {
			if (jedis != null) {
				JedisTool.returnResource(jedis);
			}
		}

	}
}
