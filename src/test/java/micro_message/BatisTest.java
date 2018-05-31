package micro_message;

import java.util.List;

import org.junit.Test;

import com.danxx.micro.bean.Message;
import com.danxx.micro.dao.MessageDao;

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
	
}
