package com.xuying.wx.wechat.mddel.message.req;

/** 
* @author  xuying.chen 
* @date 2017年11月13日 下午2:33:01 
*/
public class TextMessage extends BaseMessage {
	// 消息内容   
    private String Content;  

    public String getContent() {  
        return Content;  
    }  

    public void setContent(String content) {  
        Content = content;  
    }  

}