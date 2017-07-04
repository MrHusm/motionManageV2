package com.manage.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.domain.LotteryCondition;
import com.manage.model.Prize_Exchange;
import com.manage.model.User;
import com.manage.service.LotteryService;
import com.manage.service.UserService;

@Controller
@RequestMapping("/campaign")
public class CampaignManageAction extends AbstractAction{
    @Resource
    private LotteryService lotteryService;
    @Resource
    private UserService userService;

	/**
	 * 活动配置--抽奖
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listLottery")
	public String listLottery(LotteryCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		//查询总共条数
		int totalRow = this.lotteryService.getPrizeExchangeCount(condition);
		//封装condition
		condition = (LotteryCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Prize_Exchange> list = lotteryService.getPrizeExchangeByCondition(condition);
		for (Prize_Exchange prizeExchangeObj:list) {
		    User user = userService.getUserByCondition("", "", prizeExchangeObj.getUserId());
		    if (user == null) {
	            prizeExchangeObj.setUserName("");
	            prizeExchangeObj.setNickName("");
		    } else {
	            prizeExchangeObj.setUserName(user.getName());
	            prizeExchangeObj.setNickName(user.getNickName());
		    }
		}
		obj.put("condition",condition);
		obj.put("list", list);
		return "campaign/lottery_list";
	}

}