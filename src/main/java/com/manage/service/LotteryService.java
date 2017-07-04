package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.dao.PrizeExchangeDao;
import com.manage.domain.LotteryCondition;
import com.manage.model.Prize_Exchange;

@Service
public class LotteryService {
	public static final Logger logger = Logger.getLogger(LotteryService.class);

    @Autowired
    private PrizeExchangeDao prizeExchangeDao;

    public List<Prize_Exchange> getPrizeExchangeByCondition(LotteryCondition condition) {
        List<Object> paramList = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        int start = condition.getStartRow();
        int limit = condition.getPageSize();
        String prizeName = condition.getPrizeName();
        String userTel = condition.getUserTel();
        String prizeType = condition.getPrizeType();

        sql.append(" SELECT prize_exchange.id AS id, prize_exchange.userId, prize_exchange.prizeType, prize_exchange.prizeName, ");
        sql.append(" prize_exchange.dhTime, prize_exchange.hdId, prize_exchange.hdName, prize_exchange.trueName, prize_exchange.address, ");
        sql.append(" CASE prize_exchange.prizeType WHEN '0' THEN beMobleTel ");
        sql.append("  WHEN '2' THEN linktel ");
        sql.append("  ELSE '' ");
        sql.append("  END AS linktel ");
        sql.append(" FROM prize_exchange WHERE 1=1 ");
        if(StringUtils.isNotBlank(prizeName)){
            sql.append(" AND prizeName = ? ");
            paramList.add(prizeName);
        }
        if(StringUtils.isNotBlank(userTel)){
            sql.append(" AND (linktel = ? OR beMobleTel = ?) ");
            paramList.add(userTel);
            paramList.add(userTel);
        }
        if(StringUtils.isNotBlank(prizeType) && !"-".equals(prizeType)){
            sql.append(" AND prizeType = ? ");
            paramList.add(prizeType);
        }

        sql.append(" ORDER BY prize_exchange.updateTime DESC LIMIT ?,?");
        paramList.add(start);
        paramList.add(limit);
        logger.info(sql.toString());
        return prizeExchangeDao.find(sql.toString(), paramList.toArray());
    }

    public int getPrizeExchangeCount(LotteryCondition condition) {
        List<Object> paramList = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();

        String prizeName = condition.getPrizeName();
        String userTel = condition.getUserTel();
        String prizeType = condition.getPrizeType();

        sql.append(" SELECT COUNT(prize_exchange.id) ");
        sql.append(" FROM prize_exchange WHERE 1=1 ");
        if(StringUtils.isNotBlank(prizeName)){
            sql.append(" AND prizeName = ? ");
            paramList.add(prizeName);
        }
        if(StringUtils.isNotBlank(userTel)){
            sql.append(" AND (linktel = ? OR beMobleTel = ?) ");
            paramList.add(userTel);
            paramList.add(userTel);
        }
        if(StringUtils.isNotBlank(prizeType) && !"-".equals(prizeType)){
            sql.append(" AND prizeType = ? ");
            paramList.add(prizeType);
        }
        logger.info(sql.toString());
        return prizeExchangeDao.findForInt(sql.toString(), paramList.toArray());
    }
}
