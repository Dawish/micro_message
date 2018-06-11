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
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.danxx.micro.bean.Message;
import com.danxx.micro.entity.Page;
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
		//获取当前页码
		String currentPage = req.getParameter("currentPage");
		logger.info("currentPage : "+currentPage);
		// 创建分页对象
		Page page = new Page();
		//使页码为标准的阿拉伯两位数
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if(currentPage == null ||  !pattern.matcher(currentPage).matches()) {
			//当页码不标准的时候就设置为第1页
			page.setCurrentPage(1);
		} else {
			//分页对象页码赋值
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		
		//页面传值
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		req.setAttribute("page", page);
		//查询数据库并传值给页面
		List<Message> messagesList = new ListService().queryMessageListByPage(command, description, page);
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
