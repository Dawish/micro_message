package com.danxx.micro.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.mysql.jdbc.Connection;
/**
 * 分页拦截器
 * @author danxx
 *
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args= {Connection.class})})
public class PageInterceptor implements Interceptor {

	private String test;
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		System.out.println(this.test);
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		this.test = properties.getProperty("test");
	}

}
