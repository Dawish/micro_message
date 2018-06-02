package micro_message;

import java.util.List;

import org.junit.Test;

import com.danxx.micro.bean.Command;
import com.danxx.micro.bean.Message;
import com.danxx.micro.dao.CommandDao;
import com.danxx.micro.dao.MessageDao;
import com.danxx.micro.service.CommandService;

public class BatisTest {
	

    @Test
	public void batisTest() {
    	MessageDao messageDao = new MessageDao();
    	
    	Message messageBean = new Message();
    	messageBean.setId("6");
    	messageBean.setCommand("美女");
    	messageBean.setDescription("问谁是美女");
    	messageBean.setContent("你是大美女你信不信,对的,你就是美女!");
    	messageDao.addOne(messageBean);
    	
	
		List<Message> messageList = messageDao.queryMessageListByBatis("", "");
		for(Message message : messageList) {
			System.out.println( " message command : "+message.getCommand());
		}
	}
    
    @Test
	public void commandTest() {
    	CommandDao commandDao = new CommandDao();
    	
		List<Command> commandList = commandDao.queryCommandList("段子", "");
		for(Command command : commandList) {
			System.out.println( " ====command list size====  : "+command.getContentList().size());
		}
		
		CommandService commandService = new CommandService();
		String result = commandService.queryByCommand("段子");
		System.out.println( " ====command result====  : "+result);
	}
	
}
