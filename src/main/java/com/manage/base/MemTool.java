package com.manage.base;

import org.springframework.stereotype.Component;

import base.ikanshu.util.MemUtil;

@Component("memUtil")
public class MemTool extends MemUtil{
	static{
		MemUtil.init(1000, 1500,"/data/diskCache","flyme");
	}
}
