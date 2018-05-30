package com.danxx.micro.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @desc 访问数据库
 * @author danxx
 * @date 2018.5.30
 */
public class DBAccess {
	
	public SqlSession getSqlSession() throws IOException {
		//通过配置文件获得数据库连接信息
		 Reader reader = Resources.getResourceAsReader("com/danxx/micro/config/Configuration.xml");
		 //通过配置信息构建一个SqlSessionFactory
		 SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		 //通过SqlSessionFactory打开一个数据库会话
		 return sessionFactory.openSession();
	}
	
}
