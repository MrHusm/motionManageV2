package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.manage.dao.CodeDao;
import com.manage.domain.CodeCondition;
import com.manage.model.Exchange_Code;
import com.manage.model.Exchange_Code_Rule;

@Service("codeService")
public class CodeService{

    @Resource
    private CodeDao codeDao;
    
    /**
     * 根据条件查询兑换码列表
     * @param condition
     * @return
     */
	public List<Exchange_Code> findCodeByCondition(CodeCondition condition) {
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String name = condition.getName();
		String channelId = condition.getChannelId();
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		String code = condition.getCode();
		String ecrId = condition.getEcrId();
		StringBuffer sql = new StringBuffer("select * from exchange_code where 1 = 1 ");
		
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
		if(StringUtils.isNotBlank(code)){
			sql.append(" and code = ? ");
			args.add(code);
		}
		if(StringUtils.isNotBlank(ecrId)){
			sql.append(" and ecrId = ? ");
			args.add(ecrId);
		}
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		List<Exchange_Code> list = this.codeDao.find(sql.toString(), args.toArray());
		return list;
	}

	/**
	 * 根据条件查询兑换码数量
	 * @param condition
	 */
	public int findCountByCondition(CodeCondition condition) {
		String name = condition.getName();
		String channelId = condition.getChannelId();
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		String code = condition.getCode();
		String ecrId = condition.getEcrId();
		StringBuffer sql = new StringBuffer("select count(*) from  exchange_code where 1 = 1 ");
		
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
		if(StringUtils.isNotBlank(code)){
			sql.append(" and code = ? ");
			args.add(code);
		}
		if(StringUtils.isNotBlank(ecrId)){
			sql.append(" and ecrId = ?");
			args.add(ecrId);
		}
		return this.codeDao.findForInt(sql.toString(), args.toArray());
	}

	/**
	 * 保存兑换码
	 * @param code
	 */
	public void saveCode(Exchange_Code code) {
		this.codeDao.insert(code);
	}
	
	/**
	 * 修改公共兑换码
	 * @param code
	 */
	public void updateCode(Exchange_Code code) {
		String sql = "update `exchange_code` set `name`=?, `cc`=?, `userCc`=?, `telFlag`=?, `imsiFlag`=?, `startDate`=?, "
				+ "`endDate`=?, `cashCouponId`=?, `money`=?, `chpIds`=?, `bookIds`=?, `byIds`=?, `updateDate`=?,`vipDay`=?,`channelType`=?,`channelIds`=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(code.getName());
		list.add(code.getCc());
		list.add(code.getUserCc());
		list.add(code.getTelFlag());
		list.add(code.getImsiFlag());
		list.add(code.getStartDate());
		list.add(code.getEndDate());
		list.add(code.getCashCouponId());
		list.add(code.getMoney());
		list.add(code.getChpIds());
		list.add(code.getBookIds());
		list.add(code.getByIds());
		list.add(code.getUpdateDate());
		list.add(code.getVipDay());
		list.add(code.getChannelType());
		list.add(code.getChannelIds());
		list.add(code.getId());
		this.codeDao.update(sql, list.toArray());
	}
	
	/**
	 * 修改公共兑换码
	 * @param codeRule
	 */
	public void updateCode(Exchange_Code_Rule codeRule) {
		String sql = "update `exchange_code` set `name`=?, `telFlag`=?, `imsiFlag`=?, `startDate`=?, "
				+ "`endDate`=?, `cashCouponId`=?, `money`=?, `chpIds`=?, `bookIds`=?, `byIds`=?, `updateDate`=?,`vipDay`=?,`channelType`=?,`channelIds`=? where ecrId=?";
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
		this.codeDao.update(sql, list.toArray());
	}

	/**
	 * 
	 * 删除兑换码
	 * @param id
	 */
	public void deleteCode(String id) {
		if(!DataUtil.isEmpty(id)){
			this.codeDao.delete("exchange_code", "id", id);
		}
	}
	
	/**
	 * 
	 * 删除兑换码
	 * @param ruleId
	 */
	public void deleteCodeByRule(String ruleId) {
		if(!DataUtil.isEmpty(ruleId)){
			this.codeDao.delete("exchange_code", "ecrId", ruleId);
		}
	}

	/**
	 * 根据ID获取兑换码
	 * @param id
	 */
	public Exchange_Code getCodeById(String id) {
		String sql = "select * from exchange_code where id = ?";
		return this.codeDao.findOne(sql, id);
	}

	/**
	 * 批量生成公共兑换码
	 * @param list
	 */
	public void saveCodeRuleList(List<Exchange_Code> list) {
		for(Exchange_Code code : list){
			this.codeDao.insert(code);
		}
	}
}
