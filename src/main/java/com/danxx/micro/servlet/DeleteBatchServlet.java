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
import com.danxx.micro.service.MaintainService;

/**
 * 批量删除
 * @author danxx
 * @Date 2018.5.31
 *
 */
public class DeleteBatchServlet extends HttpServlet{
	
	
	Logger logger = Logger.getLogger(DeleteBatchServlet.class);
	
	/**
	 * http://localhost:8080/micro_message/list.do
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("DeleteBatchServlet invoke!");
		
		req.setCharacterEncoding("UTF-8");
		//接受页面值
		String[] ids = req.getParameterValues("id");
		logger.info("DeleteBatchServlet id size : "+ids.length);
		//查询数据库并传值给页面
		MaintainService maintainService = new MaintainService();
		maintainService.deleteBatch(ids);
		/**控制跳转到控制器*/
		req.getRequestDispatcher("/list.do").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
	
	

}
