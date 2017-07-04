package com.manage.constant;

import java.util.Random;

public class Constant {
	public static final Random RANDOM=new Random();
	
	public static final String zwsc_user="http://centeruser.ikanshu.cn";
	
	public final static String redis_Key_getPhbList="zwsc_getPhbList";
	public final static String redis_Key_getPhbQdList="zwsc_getPhbQdList";
	
	public final static String redis_Key_free_cnid="zwsc_freeCnid";
	public final static String redis_Key_getRechargeAmountList="zwsc_getRechargeAmountList_%s";
	public final static String redis_Key_rechargeRepay_new="zwsc_rechargeRepay_%s_%s_%s_%s";
	public final static String redis_Key_rechargeRepay="zwsc_rechargeRepay_%s_%s_%d";
	public final static String redis_Key_bindRepay="zwsc_bindRepay_%s_%s_%d";
	
	public final static String redis_Key_jpBanner="zwsc_jpBanner_%s_%s";
	public final static String redis_Key_jpPage="zwsc_jpPage_%d";
	public final static String redis_Key_jpBangDanBook="zwsc_jpBangDanBook_%s";
	
	public final static String redis_Key_phqd="phqd_%d";
	
	public static final String JP_REMOTE_PATH = "/data/web/zwsc.ikanshu.cn/jpBannerImage/";
	public static final String REMIND_REMOTE_PATH = "/data/web/zwsc.ikanshu.cn/remindImage/";
	public static final String PLUGIN_REMOTE_PATH = "/ikanshu/www/res/211/plugin/";
	public static final String START_AD_REMOTE_PATH = "/data/web/zwsc.ikanshu.cn/startImage/";
	public static final String BANNER_APP_REMOTE_PATH = "/data/web/zwsc.ikanshu.cn/bannerAppImage/";
	
	
    // --------------------------------------------------------- 个推常量区域 开始
    public static final String GETUI_APP_ID = "cEHJO0voTe5HMo1bXCc7r2"; // Znjz0K04hA7xJ4OexGj8g3  w9ONUB9XTC9buOF1PGKcA1
    public static final String GETUI_APP_SECRET = "Tr195cHa9M77eqFGwDCED6"; // Fnfq4zzvKm8eyQHgCgFJ6  O0z9nT7LFo73C0qk5w4k66
    public static final String GETUI_APP_KEY = "RqIL1IAFcy6DSb7N44o3I8"; // emjPJkMJMZ9x2UiMIISZg5  Wp74LOANcl6lDzkFYgKqZA
    public static final String GETUI_MASTER_SECRET = "YQqg6BCbJW7AKijsjNsoB7"; // 7HcOjUf01m8uzYoW9SeeU6  laE6jOr9Xm54bXduZP67b2
    public static final String GETUI_HOST = "http://sdk.open.api.igexin.com/serviceex";
    public static final String GETUI_MSG_T001_TITLE = "亲，今天你还有钱没赚！";
    public static final String GETUI_MSG_T001_TEXT = "别忘了来赚铜币哦，一日不赚翻倍奖就木有啦！";
    public static final String GETUI_MSG_T002_TITLE = "好书免费，限时抢看！";
    public static final String GETUI_MSG_T002_TEXT = "你喜欢的书现在可以免费啦，快来囤书吧！";
    public static final String GETUI_MSG_T003_TITLE = "VIP到期提醒";
    public static final String GETUI_MSG_T003_TEXT = "您的VIP特权即将到期，点此快速续订！ 链接到开通VIP";
	
}
