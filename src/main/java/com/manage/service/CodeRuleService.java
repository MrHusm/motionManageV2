package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.manage.dao.CodeRuleDao;
import com.manage.domain.CodeCondition;
import com.manage.model.Exchange_Code_Rule;

@Service("codeRuleService")
public class CodeRuleService{

    @Resource
    private CodeRuleDao codeRuleDao;
    
    /**
     * 根据条件查询单一兑换码列表
     * @param condition
     * @return
     */
	public List<Exchange_Code_Rule> findCodeRuleByCondition(CodeCondition condition) {
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String name = condition.getName();
		String channelId = condition.getChannelId();
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		StringBuffer sql = new StringBuffer("select *,(select sum(exchgNum) from exchange_Code where ecrId=r.id) as exchgNum from exchange_Code_rule r where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name = ? ");
			args.add(name);
		}
		if(StringUtils.isNotBlank(channelId)){
			sql.append(" and channelIds like ? ");
			args.add("%"+channelId+"%");
		}
		if(StringUtils.isNotBlank(startDate)){
			sql.append(" and startDate <= ? ");
			args.add(startDate);
		}
		if(StringUtils.isNotBlank(endDate)){
			sql.append(" and endDate >= ? ");
			args.add(endDate);
		}
		
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		List<Exchange_Code_Rule> list = this.codeRuleDao.find(sql.toString(), args.toArray());
		return list;
	}

	/**
	 * 根据条件查询单一兑换码数量
	 * @param condition
	 */
	public int findCountByCondition(CodeCondition condition) {
		String name = condition.getName();
		String channelId = condition.getChannelId();
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		StringBuffer sql = new StringBuffer("select count(*) from exchange_Code_rule where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name = ? ");
			args.add(name);
		}
		if(StringUtils.isNotBlank(channelId)){
			sql.append(" and channelIds like ? ");
			args.add("%"+channelId+"%");
		}
		if(StringUtils.isNotBlank(startDate)){
			sql.append(" and startDate <= ? ");
			args.add(startDate);
		}
		if(StringUtils.isNotBlank(endDate)){
			sql.append(" and endDate >= ? ");
			args.add(endDate);
		}
		return this.codeRuleDao.findForInt(sql.toString(), args.toArray());
	}

	/**
	 * 保存单一兑换码
	 * @param codeRule
	 */
	public void saveCodeRule(Exchange_Code_Rule codeRule) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(this.codeRuleDao.getJdbc(0));
		simpleJdbcInsert.withTableName("exchange_code_rule");
		long id = (Long)this.codeRuleDao.insertAndReturnId(simpleJdbcInsert, codeRule);
		codeRule.setId(String.valueOf(id));
	}
	
	public void updateCodeRule(Exchange_Code_Rule codeRule) {
		String sql = "update `exchange_code_rule` set `name`=?, `telFlag`=?, `imsiFlag`=?, `startDate`=?, "
				+ "`endDate`=?, `cashCouponId`=?, `money`=?, `chpIds`=?, `bookIds`=?, `byIds`=?, `updateDate`=?,`vipDay`=?,`channelType`=?,`channelIds`=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(codeRule.getName());
		list.add(codeRule.getTelFlag());
		list.add(codeRule.getImsiFlag());
		list.add(codeRule.getStartDate());
		list.add(codeRule.getEndDate());
		list.add(codeRule.getCashCouponId());
		list.add(codeRule.getMoney());
		list.add(codeRule.getChpIds());
		list.add(codeRule.getBookIds());
		list.add(codeRule.getByIds());
		list.add(codeRule.getUpdateDate());
		list.add(codeRule.getVipDay());
		list.add(codeRule.getChannelType());
		list.add(codeRule.getChannelIds());
		list.add(codeRule.getId());
		this.codeRuleDao.update(sql, list.toArray());
	}
	
	/**
	 * 
	 * 删除单一兑换码
	 * @param id
	 */
	public void deleteCodeRule(String id) {
		if(!DataUtil.isEmpty(id)){
			this.codeRuleDao.delete("exchange_code_rule", "id", id);
		}
	}

	/**
	 * 根据ID获取单一兑换码
	 * @param id
	 */
	public Exchange_Code_Rule getCodeRuleById(String id) {
		String sql = "select * from exchange_code_rule where id = ?";
		return this.codeRuleDao.findOne(sql, id);
	}
}
