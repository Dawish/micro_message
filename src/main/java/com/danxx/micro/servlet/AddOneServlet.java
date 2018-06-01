package com.danxx.micro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.danxx.micro.bean.Message;
import com.danxx.micro.service.MaintainService;
import com.danxx.micro.utils.StringUtils;

/**
 * 
 * @author danxx
 *
 */
public class AddOneServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger.getLogger(AddOneServlet.class);
	
	/**
	 * http://localhost:8080/micro_message/addOne.do
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("DeleteBatchServlet invoke!");
		
		req.setCharacterEncoding("UTF-8");
		//接受页面值
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		String content = req.getParameter("content");
	
		if(!StringUtils.isEmpty(command) && !StringUtils.isEmpty(content)) {
			//调用服务添加到数据库
			MaintainService maintainService = new MaintainService();
			Message message = new Message();
			message.setCommand(command);
			message.setDescription(description);
			message.setContent(content);
			maintainService.addOne(message);
		}
		/**控制跳转到jsp页面*/
		req.getRequestDispatcher("/WEB-INF/jsp/back/addPage.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
}
