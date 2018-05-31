package com.danxx.micro.service;

/**
 * controller层是不能直接跟dao层交互的，中间需要Service层
 * @author danxx
 * @date 2018.5.30
 */
import java.util.List;

import com.danxx.micro.bean.Message;
import com.danxx.micro.dao.MessageDao;

public class ListService {
	
	public List<Message> queryMessageList(String command, String description){
		//return new MessageDao().queryMessageList(command, description);
		return new MessageDao().queryMessageListByBatis(command, description);
	}
	
}
