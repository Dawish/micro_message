package com.danxx.micro.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.danxx.micro.bean.Message;
import com.danxx.micro.db.DBAccess;

/**
 * 
 * @author danxx
 * @date 2018.5.30
 */
public class MessageDao {
	
	Logger logger = Logger.getLogger(MessageDao.class);
	
	public List<Message> queryMessageListByBatis(String command, String description) {
		
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Message> messageList = new ArrayList<>();
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		try {
			sqlSession = dbAccess.getSqlSession();
			messageList = sqlSession.selectList("Message.queryMessageList",message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(sqlSession!=null) {
				sqlSession.close();
			}
		}
		
		return messageList;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deleteOne(int id) {
		
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			sqlSession.delete("Message.deleteOne",id);
			//删除需要提交事务
			sqlSession.commit();
			logger.info("deleteOne commit id : "+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(sqlSession!=null) {
				sqlSession.close();
			}
		}
	}
	
	public List<Message> queryMessageList(String command, String description) {

		List<Message> messagesList = new ArrayList<Message>();
		/* jdbc链接数据库 **/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/micro_message?characterEncoding=utf-8", "root", "123456");
			System.out.println("mysql connect success!");
			StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from message where 1=1");
			List<String> paramList = new ArrayList<>();

			if (command != null && !"".equals(command.trim())) {
				sql.append(" and COMMAND=?");
				paramList.add(command);
			}
			if (description != null && !"".equals(description.trim())) {
				sql.append(" and DESCRIPTION like '%' ? '%'");
				paramList.add(description);
			}

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			/** sql语句写完了才可以设置参数 原生的没办法就是这么不好用 */
			for (int i = 0; i < paramList.size(); i++) {
				/** sql语句中的index是从1开始的 */
				statement.setString(i + 1, paramList.get(i));
			}

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Message message = new Message();
				messagesList.add(message);
				message.setId(rs.getString("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				message.setContent(rs.getString("CONTENT"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return messagesList;
	}

}
