package com.xuying.wx.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuying.wx.wechat.mddel.message.req.SimilarData;

public interface SimilarDataMapper {

	List<SimilarData> selectAll();

	void inserOne(@Param("similarData")SimilarData similarData);

}
