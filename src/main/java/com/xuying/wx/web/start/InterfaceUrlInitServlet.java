package com.xuying.wx.web.start;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InterfaceUrlInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 项目启动加载配置文件
	 */
	@Override
    public void init(ServletConfig config) throws ServletException {
        InterfaceUrlInit.init();
    }
}
