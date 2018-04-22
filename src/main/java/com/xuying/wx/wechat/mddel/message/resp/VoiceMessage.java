package com.xuying.wx.wechat.mddel.message.resp;

/** 
* @author  xuying.chen 
* @date 2017年11月13日 下午2:33:01 
*/
public class VoiceMessage extends BaseMessage {
	private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }

}