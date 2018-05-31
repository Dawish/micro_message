package com.danxx.micro.service;

import java.util.ArrayList;
import java.util.List;

import com.danxx.micro.bean.Message;
import com.danxx.micro.dao.MessageDao;

/**
 * 维护Service
 * @author danxx
 * @date 2018.5.31
 */

public class MaintainService {
	
	public void deleteOne(String id){
		if(id!=null && !"".equals(id)) {
			new MessageDao().deleteOne(Integer.valueOf(id));
		}
	}
	
	public void deleteBatch(String[] ids){
		
		List<Integer> deIds = new ArrayList<>();
		if(deIds == null || deIds.size() <= 0) {
			return;
		}
		for(String id : ids) {
			if(id!=null && !"".equals(id)) {
				deIds.add(Integer.valueOf(id));
			}
		}
		new MessageDao().deleteBatch(deIds);
	}
	
	public void addOne(Message message){
		if(message != null) {
			new MessageDao().addOne(message);
		}
	}
	
}
