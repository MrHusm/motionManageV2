package com.manage.service;


import java.util.List;

import javax.annotation.Resource;




import org.springframework.stereotype.Service;

import com.manage.dao.JpBangLabelDao;
import com.manage.model.JP_BangDan_Label;

@Service("jpBangLabelService")
public class JpBangLabelService{

    @Resource
    private JpBangLabelDao jpBangLabelDao;
    
    /**
     * 保存榜单标签
     * @param label
     */
    public void saveJpBangDLabel(JP_BangDan_Label label){
    	this.jpBangLabelDao.insert(label);
    }
    
    /**
     * 根据榜单ID获取标签
     * @param bdId
     * @return
     */
    public List<JP_BangDan_Label> getJpBangDLabelsByBdId(String bdId){
    	String sql = "select * from jp_bangdan_label where bangDanId = ?";
    	List<JP_BangDan_Label> labels = this.jpBangLabelDao.find(sql, bdId);
    	return labels;
    }
    
    /**
     * 根据榜单ID删除榜单标签
     * @param bdId
     */
    public void deleteJpBangDLabelsByBdId(String bdId){
    	this.jpBangLabelDao.delete("jp_bangdan_label", "bangDanId", bdId);
    }
}
