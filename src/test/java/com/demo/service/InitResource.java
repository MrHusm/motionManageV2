package com.demo.service;

import org.springframework.beans.factory.InitializingBean;

//@Component
public class InitResource implements InitializingBean {
    //private static final Logger logger = LoggerFactory.getLogger(InitResource.class);
    //public static final Map<String, String> channelMaps = new HashMap<String, String>();
    @Override
    public void afterPropertiesSet() throws Exception {
    	/*
        logger.info("init info starting.");
        List<ChannelCategorySub> channels = channelCategoryDao.getChannels();
        for (ChannelCategorySub channelCategorySub : channels) {
        	channelMaps.put(channelCategorySub.getChannelId(), channelCategorySub.getName());
		}
        logger.info("init info started.");
        */
    }
}
