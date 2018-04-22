package com.xuying.wx.wechat.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.xuying.wx.util.HttpUtils;
import com.xuying.wx.web.start.GlobalConstants;

public class WeChatTask {
	/**
	 * 需要做定时任务,每隔两个小时去请求一次,得到token放到初始化时加载的配置文件对象中,调用接口会用到
	 * @Description: TODO
	 * @author xuying.chen 
	 * @date 2018年4月17日 上午1:10:25 
	 * @throws Exception
	 */
	public void getToken_getTicket() throws Exception {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("grant_type", "client_credential");
//        params.put("appid", GlobalConstants.getInterfaceUrl("appid"));
//        params.put("secret", GlobalConstants.getInterfaceUrl("AppSecret"));
//        String jstoken = HttpUtils.sendGet(
//                GlobalConstants.getInterfaceUrl("tokenUrl"), params);
//        String access_token = JSONObject.parseObject(jstoken).getString(
//                "access_token"); // 获取到 token 并赋值保存
//        GlobalConstants.interfaceUrlProperties.put("access_token", access_token);
//                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"token 为=============================="+access_token);
		System.out.println("asd");
    }

}
