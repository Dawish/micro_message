package com.danxx.micro.service;

import java.util.List;
import java.util.Random;

import com.danxx.micro.bean.Command;
import com.danxx.micro.dao.CommandDao;
import com.danxx.micro.utils.Iconst;
import com.danxx.micro.utils.StringUtils;
import com.sun.org.apache.bcel.internal.generic.ICONST;

public class CommandService {

	/**
	 * 通过指令查询自动回复的内容
	 * @param command 指令
	 * @return 自动回复的内容
	 */
	public String queryByCommand(String command) {
		
		CommandDao commandDao = new CommandDao();
		List<Command> commandList;
		/**请求"帮助"*/
		if(command != null && StringUtils.equals(command, Iconst.HELP_COMMAND)) {
			commandList = commandDao.queryCommandList(null, null);
			if(commandList != null) {
				StringBuilder stringBuilder = new StringBuilder();
				for(int i=0;i<commandList.size();i++) {
					if(i != 0) {
						/**增加换行*/
						stringBuilder.append("<br/>");
					}
					stringBuilder.append("回复[" + commandList.get(i).getName() + "]可以查看" + commandList.get(i).getDescription());
				}
			}
		}
		
		commandList = commandDao.queryCommandList(command, null);
		
		/**两层随机取一条匹配到的内容*/
		if(commandList!=null && commandList.size()>0) {
			Command randomCommand = commandList.get(new Random().nextInt(commandList.size()));
			if(randomCommand!=null && randomCommand.getContentList()!=null && randomCommand.getContentList().size()>0) {
				return randomCommand.getContentList().get(new Random().nextInt(randomCommand.getContentList().size())).getContent();
			}
		}
		
		/**指令没有匹配*/
		return Iconst.NO_MATCHING_CONTENT; 
	}
	
}
