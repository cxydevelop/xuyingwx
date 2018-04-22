package com.xuying.wx.wechat.mddel.message.req;

/** 
* @author  xuying.chen 
* @date 2017年11月13日 下午2:33:01 
*/
public class VoiceMessage extends BaseMessage {
	// 媒体 ID   
    private String MediaId;  
    // 语音格式   
    private String Format;  

    public String getMediaId() {  
        return MediaId;  
    }  

    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  

    public String getFormat() {  
        return Format;  
    }  

    public void setFormat(String format) {  
        Format = format;  
    }  


}