package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.manage.dao.JpBangDao;
import com.manage.dao.JpBangLabelDao;
import com.manage.domain.JpBangCondition;
import com.manage.model.JP_BangDan;

@Service("jpBangService")
public class JpBangService{

    @Resource
    private JpBangDao jpBangDao;
    
    @Resource
    private JpBangLabelDao jpBangLabelDao;
    
	/**
	 * 根据条件查询榜单数量
	 * @param condition
	 */
	public int findCountByCondition(JpBangCondition condition) {
		String name = condition.getName();
		String source = condition.getSource();
		StringBuffer sql = new StringBuffer("select count(*) from jp_bangdan where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name = ?");
			args.add(name);
		}
		if(StringUtils.isNotBlank(source)){
			sql.append(" and source = ?");
			args.add(source);
		}
		return this.jpBangDao.findForInt(sql.toString(), args.toArray());
	}
    
    /**
     * 根据条件查询榜单列表
     * @param condition
     * @return
     */
	public List<JP_BangDan> findBangByCondition(JpBangCondition condition) {
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String name = condition.getName();
		String source = condition.getSource();
		StringBuffer sql = new StringBuffer("select * from resource_process.jp_bangdan where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ?");
			args.add("%"+name+"%");
		}
		if(StringUtils.isNotBlank(source)){
			sql.append(" and source = ?");
			args.add(source);
		}
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		List<JP_BangDan> list = this.jpBangDao.find(sql.toString(), args.toArray());
		return list;
	}
	/**
	 * 保存榜单
	 * @param bang
	 */
	public JP_BangDan saveJpBangD(JP_BangDan bang) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(this.jpBangDao.getJdbc(0));
		simpleJdbcInsert.withTableName("jp_bangdan");
		long id = (Long)this.jpBangDao.insertAndReturnId(simpleJdbcInsert, bang);
		bang.setId((int)id);
		return bang;
	}

	/**
	 * 修改榜单
	 * @param bang
	 */
	public void updateJpBangD(JP_BangDan bang) {
		String sql = "update `jp_bangdan` set name=?,bookNum=?,showNameFlag=?,freshFlag=?,downFlag=?,downNum=?,moreFlag=?,moreUrl=?,"
				+ "moreContent=?,bookFrom=?,driveId=?,driveName=?,bangStyle=?,remark=?,otherIds=?,updateDate=now() where id=?";
		String sqlPage = "update jp_page set name=? where `type`=1 and keyId=?";
		List<Object> args = new ArrayList<Object>();
		args.add(bang.getName());
		args.add(bang.getBookNum());
		args.add(bang.getShowNameFlag());
		args.add(bang.getFreshFlag());
		args.add(bang.getDownFlag());
		args.add(bang.getDownNum());
		args.add(bang.getMoreFlag());
		args.add(bang.getMoreUrl());
		args.add(bang.getMoreContent());
		args.add(bang.getBookFrom());
		args.add(bang.getDriveId());
		args.add(bang.getDriveName());
		args.add(bang.getBangStyle());
		args.add(bang.getRemark());
		args.add(bang.getOtherIds());
		args.add(bang.getId());
		this.jpBangDao.update(sql, args.toArray());
		
		List<Object> pageArgs = new ArrayList<Object>();
		pageArgs.add(bang.getName());
		pageArgs.add(bang.getId());
		this.jpBangDao.update(sqlPage, pageArgs.toArray());
	}

	/**
	 * 
	 * 删除榜单
	 * @param id
	 */
	public void deleteJpBangD(String id) {
		String sql = "delete from jp_page where type = 1 and keyId=?";
		if(!DataUtil.isEmpty(id)){
			this.jpBangDao.delete("jp_bangdan", "id", id);
			this.jpBangLabelDao.delete("jp_bangdan_label", "bangDanId", id);
			this.jpBangDao.update(sql, id);
		}
	}

	/**
	 * 根据ID获取榜单
	 * @param id
	 */
	public JP_BangDan getJpBangDById(String id) {
		String sql = "select * from jp_bangdan where id = ?";
		return this.jpBangDao.findOne(sql, id);
	}
}
