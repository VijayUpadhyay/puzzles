package com.vijay.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.vijay.messenger.database.DatabaseClass;
import com.vijay.messenger.model.Comments;
import com.vijay.messenger.model.Message;

public class MessageService {

	Map<Long,Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L,new Message(1, "abc", "Vijay"));
		messages.put(2L,new Message(2, "def", "Vijay"));
	}
	
	public List<Message> getMessageList(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id){
		return messages.get(id);
	}
	
	public Message addMessage(Message msg){
		msg.setId(messages.size()+1);
		messages.put(msg.getId(), msg);
		return msg;
	}
	
	public Message updateMessage(Message msg){
		if(msg.getId() <= 0)
			return null;
		messages.put(msg.getId(), msg);
		return msg;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}

	public List<Message> getMessageBasedOnYear(int year){
		List<Message> msgList = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message msg : messages.values()){
			cal.setTime(msg.getCreated());
			if(cal.get(Calendar.YEAR) > year)
				msgList.add(msg);
		}
		return msgList;
	}
	
	public List<Message> getMessageListWithPagination(int start,int size) {
		List<Message> list = new ArrayList<Message>(messages.values());
		if(size ==0 && size ==0)
			return getMessageList();
		return list.subList(start, start+size);
		
	}
	
}
