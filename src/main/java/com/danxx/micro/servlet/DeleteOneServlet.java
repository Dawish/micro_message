package com.danxx.micro.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danxx.micro.bean.Message;
import com.danxx.micro.service.ListService;
import com.danxx.micro.service.MaintainService;
import com.sun.org.glassfish.external.statistics.annotations.Reset;

/**
 * 列表初始化控制器
 * @author danxx
 * @Date 2018.5.30
 *
 */
public class DeleteOneServlet extends HttpServlet{

	/**
	 * http://localhost:8080/micro_message/list.do
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		//接受页面值
		String id = req.getParameter("id");
		//页面传值
		req.setAttribute("id", id);
		//查询数据库并传值给页面
		MaintainService maintainService = new MaintainService();
		maintainService.deleteOne(id);
		/**控制跳转到控制器*/
		req.getRequestDispatcher("/list.do").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
	
	

}
