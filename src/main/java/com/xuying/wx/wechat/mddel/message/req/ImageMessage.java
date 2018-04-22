package com.xuying.wx.wechat.mddel.message.req;

/** 
* @author  xuying.chen 
* @date 2017年11月13日 下午2:33:01 
*/
public class ImageMessage extends BaseMessage {
    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}