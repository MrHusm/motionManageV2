package com.manage.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.manage.constant.Constant;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

/**
 * 该类主要参考自个推官方sample
 * getui关联的jar:
 *      gexin-rp-sdk-base-4.0.0.3.jar
 *      gexin-rp-sdk-http-4.0.1.0.jar
 *      gexin-rp-sdk-template-4.0.0.3.jar
 *      protobuf-java-2.5.0.jar
 *      以及jackson相关类
 * 
 * @author weiqz
 *
 */
public class GetuiUtil {
    protected static final Logger logger = LoggerFactory.getLogger(GetuiUtil.class);
    private static int getui_Limit = 1000;

    /**
     * 对指定列表用户推送消息
     * 
     */
    public static void pushMessageToList(List<Map<String,Object>> clients, String title, String text, String content) {
        IGtPush push = new IGtPush(Constant.GETUI_HOST, Constant.GETUI_APP_KEY, Constant.GETUI_MASTER_SECRET);
        
        // 通知透传模板
        NotificationTemplate template = notificationTemplateDemo(title, text, content);   
        ListMessage message = new ListMessage();
        message.setData(template);
    
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(86400000); // 一天毫秒数：24*3600*1000

        // 配置推送目标(每一千件向getui发送一次请求)
        logger.info("clients.size()：" + clients.size());
        int loops = clients.size() / getui_Limit;
        if (loops > 0) {
            loops = clients.size() % getui_Limit == 0 ? loops - 1 : loops;
        }
        for (int i = 0; i <= loops; i++) {
            List<Target> targets = new ArrayList<Target>();
            int max = getui_Limit * (i + 1) >= clients.size() ? clients.size(): getui_Limit * (i + 1);
            for (int j = getui_Limit * (i); j < max; j++) {
            	Map<String,Object> client = clients.get(j);
                Target target = new Target();
                target.setAppId(Constant.GETUI_APP_ID);
                target.setClientId(String.valueOf(client.get("clientId")));
                targets.add(target);
            }
            // 获取taskID
            String taskId = push.getContentId(message);
            // 使用taskID对目标进行推送
            IPushResult ret = push.pushMessageToList(taskId, targets);
            // 打印服务器返回信息
            logger.info("pushMessageToList:" + ret.getResponse().toString());
        }
    }
    public static NotificationTemplate notificationTemplateDemo(String title, String text, String content) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(Constant.GETUI_APP_ID);
        template.setAppkey(Constant.GETUI_APP_KEY);
        // 设置通知栏标题与内容
        template.setTitle(title);
        template.setText(text);
        // 配置通知栏图标
        template.setLogo("push.png"); // push.png
        // 配置通知栏网络图标
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2); // 1:程序关闭的时候，点击会启动应用/2:只有在程序启动的时候才能有效接收参数
        template.setTransmissionContent(content);
        return template;
    }

    public static TransmissionTemplate transmissionTemplateDemo(String title, String text, String content) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(Constant.GETUI_APP_ID);
        template.setAppkey(Constant.GETUI_APP_KEY);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent("请输入需要透传的内容");
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }

    public static void main(String[] args) {
        List<Map<String,Object>> clients = new ArrayList<Map<String,Object>>();
        Map<String,Object> client = new HashMap<String,Object>();
        client.put("clientId","a3cf36a3bd834bbc1b16b220baeec50b"); //  cb17d6d7f56fa6fab1e61039b81c17da  0cbe5277b645fb291851574805faf9ce
        Map<String, Object> jsonMap = new HashMap<String, Object>(); //
        String content = JSONObject.toJSONString(jsonMap);
        
        clients.add(client);
        jsonMap.put("action_data", "http://www.baidu.com"); 
        content = JSONObject.toJSONString(jsonMap);
        GetuiUtil.pushMessageToList(clients,  "请与钟鹏联系，带着手机", Constant.GETUI_MSG_T002_TEXT, content);

//        Map<String, Object> jsonMap = new HashMap<String, Object>(); //
//        jsonMap.put("action_data", "http://www.baidu.com/");
//        jsonMap.put("type", "1");
//        String content = JSONObject.toJSONString(jsonMap);
//        GetuiUtil.pushMessageToList(clients, Constant.GETUI_MSG_T002_TITLE, Constant.GETUI_MSG_T002_TEXT, content);

//        jsonMap = new HashMap<String, Object>(); //
//        jsonMap.put("action_data", "/user/baoyue"); 
//        jsonMap.put("type", "1"); 
//        content = JSONObject.toJSONString(jsonMap);
//        GetuiUtil.pushMessageToList(clients,  Constant.GETUI_MSG_T002_TITLE, Constant.GETUI_MSG_T002_TEXT, content);
//
//        jsonMap = new HashMap<String, Object>(); //
//        jsonMap.put("action_data", "native://gotoMakeMoney"); 
//        jsonMap.put("type", "1"); 
//        content = JSONObject.toJSONString(jsonMap);
//        GetuiUtil.pushMessageToList(clients,  Constant.GETUI_MSG_T003_TITLE, Constant.GETUI_MSG_T003_TEXT, content);
//
//        jsonMap = new HashMap<String, Object>(); //
//        jsonMap.put("action_data", "/bookmall/channelmf"); 
//        jsonMap.put("type", "1"); 
//        content = JSONObject.toJSONString(jsonMap);
//        GetuiUtil.pushMessageToList(clients,  Constant.GETUI_MSG_T004_TITLE, Constant.GETUI_MSG_T004_TEXT, content);
    }
}
