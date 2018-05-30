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
import com.sun.org.glassfish.external.statistics.annotations.Reset;

/**
 * 列表初始化控制器
 * @author danxx
 *
 */
public class ListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*jdbc链接数据库**/
		try {
			
			req.setCharacterEncoding("UTF-8");
			
			String command = req.getParameter("command");
			String description = req.getParameter("description");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/micro_message?characterEncoding=utf-8","root","123456");
			System.out.println("mysql connect success!");
			StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from message where 1=1");
			List<String> paramList = new ArrayList<>();
			
			if(command != null && !"".equals(command.trim())) {
				sql.append(" and COMMAND=?");
				paramList.add(command);
				req.setAttribute("command", command);
			}
			if(description != null && !"".equals(description.trim())) {
				sql.append(" and DESCRIPTION like '%' ? '%'");
				paramList.add(description);
				req.setAttribute("description", description);
			}
			
			PreparedStatement statement = connection.prepareStatement(sql.toString());
			/**sql语句写完了才可以设置参数 原生的没办法就是这么不好用*/
			for(int i=0;i<paramList.size();i++) {
				/**sql语句中的index是从1开始的*/
				statement.setString(i+1, paramList.get(i));
			}
			
			ResultSet rs = statement.executeQuery();
			List<Message> messagesList = new ArrayList<Message>();
			while (rs.next()) {
				Message message = new Message();
				messagesList.add(message);
				message.setId(rs.getString("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				message.setContent(rs.getString("CONTENT"));
			}
			req.setAttribute("messagesList", messagesList);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**控制跳转*/
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
	
	

}
