package com.manage.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.manage.dao.VersionDao;
import com.manage.model.Channel_Version_V3;

@Service("versionService")
public class VersionService{

    
    @Resource
    private VersionDao versionDao;
    
	/**
	 * 获取版本
	 * @param id
	 */
	public List<Channel_Version_V3> getVersions() {
		String sql = "select * from channel_version_v3 order by id ";
		return this.versionDao.find(sql);
	}
}
