package com.danxx.micro.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.danxx.micro.bean.Message;
import com.danxx.micro.dao.MessageDao;
import com.danxx.micro.servlet.DeleteBatchServlet;

/**
 * 维护Service
 * @author danxx
 * @date 2018.5.31
 */

public class MaintainService {
	
	
	Logger logger = Logger.getLogger(MaintainService.class);
	
	public void deleteOne(String id){
		if(id!=null && !"".equals(id)) {
			new MessageDao().deleteOne(Integer.valueOf(id));
		}
	}
	
	public void deleteBatch(String[] ids){
		
		List<Integer> deIds = new ArrayList<>();
		if(ids == null || ids.length <= 0) {
			logger.info("MaintainService deleteBatch ids args null");
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
