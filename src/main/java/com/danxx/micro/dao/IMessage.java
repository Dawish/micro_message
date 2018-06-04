package com.danxx.micro.dao;

import java.util.List;

import com.danxx.micro.bean.Message;

/**
 * Message数据库操作接口
 * 为了避免手写namespace 和 无法预知的数据库返回值
 * @author danxx
 *
 */
public interface IMessage {
	
	/**根据一些参数查询相关的Message*/
	public List<Message> queryMessageList(Message message);
	
	
	/**根据id删除一条Message*/
	public void deleteOne(int id);
	
	/**根据一组id删除一组Message*/
	public void deleteBatch(List<Integer> ids);
	
	/**添加一条Message*/
	public void addOne(Message message);
}
