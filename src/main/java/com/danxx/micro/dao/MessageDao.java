package com.danxx.micro.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.danxx.micro.bean.Message;
import com.danxx.micro.db.DBAccess;

/**
 * 
 * @author danxx
 * @date 2018.5.30
 */
public class MessageDao implements IMessage{
	
	Logger logger = Logger.getLogger(MessageDao.class);
	
	/**
	 * 
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> queryMessageList(Map<String,Object> parameter) {
		
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Message> messageList = new ArrayList<>();
		try {
			sqlSession = dbAccess.getSqlSession();
			//messageList = sqlSession.selectList("Message.queryMessageList",message);
			IMessage iMessage = sqlSession.getMapper(IMessage.class);
			messageList = iMessage.queryMessageList(parameter);
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
	 * 根绝当前页码查找
	 */
	public List<Message> queryMessageListByPage(Map<String,Object> parameter) {
		DBAccess dbAccess = new DBAccess();
		List<Message> messageList = new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			messageList = imessage.queryMessageListByPage(parameter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}
	
	/**
	 * 根据查询条件查询消息列表的条数
	 */
	public int count(Message message) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			result = imessage.count(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
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
			//sqlSession.delete("Message.deleteOne",id);
			IMessage iMessage = sqlSession.getMapper(IMessage.class);
			iMessage.deleteOne(id);
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
	
	
	/**
	 * 
	 * @param id
	 */
	public void deleteBatch(List<Integer> ids) {
		logger.info("MessageDao deleteBatch 1 ...");
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			//sqlSession.delete("Message.deleteBatch",ids);
			IMessage iMessage = sqlSession.getMapper(IMessage.class);
			iMessage.deleteBatch(ids);
			//删除需要提交事务
			sqlSession.commit();
			logger.info("MessageDao deleteBatch 2 ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(sqlSession!=null) {
				sqlSession.close();
			}
		}
	}
	
	/**
	 * 
	 */
	public void addOne(Message message) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			//sqlSession.insert("Message.addOne",message);
			IMessage iMessage = sqlSession.getMapper(IMessage.class);
			iMessage.addOne(message);
			//删除需要提交事务
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(sqlSession!=null) {
				sqlSession.close();
			}
		}
	}
	
	/**
	 * 
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> queryMessageListByADBC(String command, String description) {

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
