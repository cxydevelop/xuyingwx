package com.xuying.wx.web.start;

import java.util.Properties;

public class GlobalConstants {
	public static Properties interfaceUrlProperties;
	
	/**
	 * 根据key获取配置文件的值
	 * @Description: TODO
	 * @author xuying.chen 
	 * @date 2018年4月16日 下午11:55:47 
	 * @param key
	 * @return
	 */
    public static String getInterfaceUrl(String key) {
        return (String) interfaceUrlProperties.get(key);
    }

}
