package com.danxx.micro.dao;

import java.util.List;
import java.util.Map;

import com.danxx.micro.bean.Message;

/**
 * Message数据库操作接口
 * 为了避免手写namespace 和 无法预知的数据库返回类型
 * @author danxx
 *
 */
public interface IMessage {
	
	/**根据一些参数查询相关的Message*/
	public List<Message> queryMessageList(Map<String,Object> parameter);
	
	/**条件分页查找*/
	public List<Message> queryMessageListByPage(Map<String,Object> parameter);
	
	/**根据id删除一条Message*/
	public void deleteOne(int id);
	
	/**根据一组id删除一组Message*/
	public void deleteBatch(List<Integer> ids);
	
	/**添加一条Message*/
	public void addOne(Message message);
	
	/**根据条件查找列表总数*/
	public int count(Message message);
}
