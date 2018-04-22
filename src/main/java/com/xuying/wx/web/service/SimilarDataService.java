package com.xuying.wx.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuying.wx.web.mapper.SimilarDataMapper;
import com.xuying.wx.wechat.mddel.message.req.SimilarData;
@Service
public class SimilarDataService  {
	@Autowired
	private SimilarDataMapper similarDataMapper;

	public List<SimilarData> selectAll() {
		List<SimilarData> selectAll = similarDataMapper.selectAll();
		return selectAll;
	}

	public void inserOne() {
		SimilarData similarData = new SimilarData();
		similarData.setDataId("abc");
		similarDataMapper.inserOne(similarData);
	}
	
}
