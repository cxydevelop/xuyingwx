package com.xuying.wx.wechat.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuying.wx.util.MessageUtil;
import com.xuying.wx.util.SignUtil;
import com.xuying.wx.web.service.SimilarDataService;
import com.xuying.wx.wechat.dispatcher.EventDispatcher;
import com.xuying.wx.wechat.dispatcher.MsgDispatcher;
import com.xuying.wx.wechat.mddel.message.req.SimilarData;


@Controller
@RequestMapping("wx/test")
public class WechatSecurityController {
	private static Logger logger = Logger.getLogger(WechatSecurityController.class.getName());

	@Autowired
	private SimilarDataService similarDataService;
	
	@ResponseBody
	@RequestMapping("selectTest")
	public Object addCatalog(String params) {
		List<SimilarData> similarDataList = similarDataService.selectAll();
		return similarDataList;
	}
	/**
	 * 开发者提交信息后,微信服务器会发送get请求到填写的服务器url上,就是这个方法(排序,拼接,加密和signature对比)
	 * @Description: TODO
	 * @author xuying.chen 
	 * @date 2018年4月16日 下午11:51:01 
	 * @param request
	 * @param response
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 */
	@RequestMapping(value = "security", method = RequestMethod.GET)
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr) {
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                logger.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
	
	/**
	 * post 方法用于接收微信服务端消息
	 * @Description: TODO
	 * @author xuying.chen 
	 * @date 2018年4月16日 下午11:51:42 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "security", method = RequestMethod.POST)
    public String DoPost(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("这是 post 方法！");
        PrintWriter out = null;
        try{
	        Map<String, String> map=MessageUtil.parseXml(request);
	        logger.info("请求进来");
	        String msgtype=map.get("MsgType");
	        System.out.println("============================="+map.get("Content"));
	        String xmlRes = "";
            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)){
            	xmlRes = EventDispatcher.processEvent(map); //进入事件处理
            }else{
            	xmlRes = MsgDispatcher.processMessage(map); //进入消息处理
            }
            response.setCharacterEncoding("utf-8");
            out = response.getWriter();
            out.print(xmlRes);
            out.flush();
            out.close();
        }catch(Exception e){
            logger.error(e.toString());
        }finally {
			if(out!=null){
				out.close();
			}
		}
        return null;
	}
	
}
