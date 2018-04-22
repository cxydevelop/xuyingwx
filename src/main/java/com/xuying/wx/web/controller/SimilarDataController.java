package com.xuying.wx.web.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("wx/test/heheda")
public class SimilarDataController {
	private static Logger logger = Logger.getLogger(SimilarDataController.class.getName());

	@Autowired
	private SimilarDataService similarDataService;
	
	@ResponseBody
	@RequestMapping("getTick")
	public Object getTick(String params) {
		List<SimilarData> similarDataList = similarDataService.selectAll();
		return similarDataList;
	}
	
}
