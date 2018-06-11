package com.danxx.micro.interceptor;

import java.util.Properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.danxx.micro.entity.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;
/**
 * 分页拦截器
 * @author danxx
 *
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args= {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {

	private String test;
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
		//从RoutingStatementHandler中反射取得真正的StatementHandler对象
		MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
		// 配置文件中SQL语句的ID
		String id = mappedStatement.getId();
		if(id.matches(".+ByPage$")) {
			BoundSql boundSql = statementHandler.getBoundSql();
			// 原始的SQL语句
			String sql = boundSql.getSql();
			// 查询总条数的SQL语句
			String countSql = "select count(*) from (" + sql + ") alias";
			Connection connection = (Connection)invocation.getArgs()[0];
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			//获取总数查询结果
			ResultSet rs = countStatement.executeQuery();
			//获取拦截到的sql参数
			Map<?,?> parameter = (Map<?,?>)boundSql.getParameterObject();
			Page page = (Page)parameter.get("page");
			if(rs.next()) {
				//获取第二个参数
				page.setTotalNumber(rs.getInt(1));
			}
			// 改造后带分页查询的SQL语句
			String pageSql = sql + " limit " + page.getDbIndex() + "," + page.getDbNumber();
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		return invocation.proceed();
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
