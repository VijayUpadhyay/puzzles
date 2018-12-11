package com.vijay.messenger.chatGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.vijay.messenger.database.DatabaseClass;
import com.vijay.messenger.model.Comments;
import com.vijay.messenger.model.Message;
import com.vijay.messenger.service.CommentsService;
import com.vijay.messenger.service.MessageService;

@Path("/")
public class CommentsResource {
	MessageService service = new MessageService();
	Map<Long, Message> message = DatabaseClass.getMessages();
	
	/*@GET
	public List<Comments> getAllComments(){
		List<Message> list =  service.getMessageList();
		
	}*/
	
	public List<Comments> getComments(long messageId){
		Message msg = message.get(messageId);
		List<Comments> comments = new ArrayList<>(msg.getComments().values());
		return comments;
	}
}
