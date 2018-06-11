package com.danxx.micro.service;

import java.util.HashMap;
/**
 * controller层是不能直接跟dao层交互的，中间需要Service层
 * @author danxx
 * @date 2018.5.30
 */
import java.util.List;
import java.util.Map;

import com.danxx.micro.bean.Message;
import com.danxx.micro.dao.MessageDao;
import com.danxx.micro.entity.Page;

public class ListService {
	
	/**
	 * 手动分页
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> queryMessageList(String command, String description,Page page){
		// 组织消息对象
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		MessageDao messageDao = new MessageDao();
		// 根据条件查询条数
		int totalNumber = messageDao.count(message);
		// 组织分页查询参数
		page.setTotalNumber(totalNumber);
		Map<String,Object> parameter = new HashMap<String, Object>();
		parameter.put("message", message);
		parameter.put("page", page);
		// 分页查询并返回结果
		return messageDao.queryMessageList(parameter);
	}
	
	/**
	 * 拦截器实现分页
	 * @param command
	 * @param description
	 * @param page
	 * @return
	 */
	public List<Message> queryMessageListByPage(String command,String description,Page page) {
		Map<String,Object> parameter = new HashMap<String, Object>();
		// 组织消息对象
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		parameter.put("message", message);
		parameter.put("page", page);
		MessageDao messageDao = new MessageDao();
		// 分页查询并返回结果
		return messageDao.queryMessageListByPage(parameter);
	}
}
