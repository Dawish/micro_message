package com.danxx.micro.service;

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
	
	
	
}
