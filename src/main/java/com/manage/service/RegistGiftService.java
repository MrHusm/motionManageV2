package com.manage.service;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.manage.dao.RegistGiftDao;
import com.manage.domain.RegistGiftCondition;
import com.manage.model.User_Register_Gift;


@Service("registGiftService")
public class RegistGiftService{
    
    @Resource
    private RegistGiftDao registGiftDao;

    /**
     * 根据条件查询新手礼包数量
     * @param condition
     * @return
     */
	public int findCountByCondition(RegistGiftCondition condition) {
		String name = condition.getName();
		StringBuffer sql = new StringBuffer("select count(*) from user_register_gift where 1=1 ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ? ");
			args.add("%"+name+"%");
		}
		System.out.println(sql.toString());
		return this.registGiftDao.findForInt(sql.toString(), args.toArray());
	}

	/**
	 * 根据条件查询新手礼包
	 * @param condition
	 * @return
	 */
	public List<User_Register_Gift> findRegistGiftByCondition(RegistGiftCondition condition) {
		String name = condition.getName();
		StringBuffer sql = new StringBuffer("select * from user_register_gift where 1=1 ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ? ");
			args.add("%"+name+"%");
		}
		System.out.println(sql.toString());
		return this.registGiftDao.find(sql.toString(), args.toArray());
	}

	/**
	 * 保存新手礼包
	 * @param registGift
	 */
	public void saveRegistGift(User_Register_Gift registGift) {
		this.registGiftDao.insert(registGift);
	}

	/**
	 * 根据ID删除新手礼包
	 * @param id
	 */
	public void deleteRegistGift(String id) {
		if(!DataUtil.isEmpty(id)){
			this.registGiftDao.delete("user_register_gift", "id", id);
		}
	}

	/**
	 * 根据ID删除新手礼包
	 * @param id
	 * @return
	 */
	public User_Register_Gift getRegistGiftById(String id) {
		return this.registGiftDao.get("id", id);
	}

	/**
	 * 修改新手礼包
	 * @param registGift
	 */
	public void updateRegistGift(User_Register_Gift registGift) {
		String sql = "update user_register_gift set `name`=?, type=?,num=?,`content`=?, `startDate`=?,"
				+ " `endDate`=?, `channelIds`=? where `id` = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(registGift.getName());
		list.add(registGift.getType());
		list.add(registGift.getNum());
		list.add(registGift.getContent());
		list.add(registGift.getStartDate());
		list.add(registGift.getEndDate());
		list.add(registGift.getChannelIds());
		list.add(registGift.getId());
		this.registGiftDao.update(sql, list.toArray());
	}
}