package micro_message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.danxx.micro.bean.Message;
import com.danxx.micro.dao.MessageDao;
import com.danxx.micro.entity.Page;
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
    	Map<String, Object> paramer = new HashMap<>();
    	paramer.put("message", messageBean);
    	Page page = new Page();
    	
    	page.setTotalNumber(messageDao.count(messageBean));
    	
    	page.count();
    	paramer.put("page", page);
		List<Message> messageList = messageDao.queryMessageList(paramer);
		for(Message message : messageList) {
			System.out.println( " message command : "+message.getCommand());
		}
	}
    
    @Test
	public void commandTest() {
		CommandService commandService = new CommandService();
		String result1 = commandService.queryByCommand("帮助");
		System.out.println( " ====command result====  : "+result1);
		
		String result2 = commandService.queryByCommand("祝福");
		System.out.println( " ====command result====  : "+result2);
	}
	
}
