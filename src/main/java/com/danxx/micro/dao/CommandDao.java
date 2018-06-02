package com.danxx.micro.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.danxx.micro.bean.Command;
import com.danxx.micro.bean.Message;
import com.danxx.micro.db.DBAccess;

/**
 * 处理一个指令对应多个内容 数据库一对多  使用两张表实现
 * @author Administrator
 *
 */
public class CommandDao {

	
	Logger logger = Logger.getLogger(CommandDao.class);
	
	/**
	 * 
	 * @param name 指令名称
	 * @param description 指令描述
	 * @return 返回所有指令 以及一个指令对应的多个不同内容
	 */
	public List<Command> queryCommandList(String name, String description) {
		
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Command> commandList = new ArrayList<>();
		Command command = new Command();
		command.setName(name);
		command.setDescription(description);
		try {
			sqlSession = dbAccess.getSqlSession();
			commandList = sqlSession.selectList("Command.queryCommandList",command);
			logger.info("size : " +commandList.size() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(sqlSession!=null) {
				sqlSession.close();
			}
		}
		
		return commandList;
	}
	
	
	
}
