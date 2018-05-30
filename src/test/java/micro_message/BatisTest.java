package micro_message;

import java.util.List;

import org.junit.Test;

import com.danxx.micro.bean.Message;
import com.danxx.micro.dao.MessageDao;

public class BatisTest {
	

    @Test
	public void batisTest() {
		MessageDao messageDao = new MessageDao();
		List<Message> messageList = messageDao.queryMessageListByBatis("", "");
		for(Message message : messageList) {
			System.out.println( " message command : "+message.getCommand());
		}
	}
	
}
