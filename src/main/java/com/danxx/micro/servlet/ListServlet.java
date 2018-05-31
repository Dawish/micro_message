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

import org.apache.log4j.Logger;

import com.danxx.micro.bean.Message;
import com.danxx.micro.service.ListService;
import com.sun.org.glassfish.external.statistics.annotations.Reset;

/**
 * 列表初始化控制器
 * @author danxx
 * @Date 2018.5.30
 *
 */
public class ListServlet extends HttpServlet{
	Logger logger = Logger.getLogger(ListServlet.class);
	/**
	 * http://localhost:8080/micro_message/list.do
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("ListServlet invoke!");
		
		req.setCharacterEncoding("UTF-8");
		//接受页面值
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		//页面传值
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		//查询数据库并传值给页面
		List<Message> messagesList = new ListService().queryMessageList(command, description);
		req.setAttribute("messagesList", messagesList);
		/**控制跳转到jsp页面*/
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
	
	

}
