package com.vijay.messenger.chatGroup;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.vijay.messenger.chatGroup.bean.MessageFilterBean;
import com.vijay.messenger.model.Message;
import com.vijay.messenger.service.MessageService;

@Path("/messageResource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();
	
	/*@GET
	public List<Message> getMessages(@QueryParam("year") int year,@QueryParam("start") int start,@QueryParam("size") int size){
		if(year>0)
			return messageService.getMessageBasedOnYear(year);
		if(start >=0 && size >= 0)
			return messageService.getMessageListWithPagination(start, size);
		return messageService.getMessageList();
	}*/
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		if(filterBean.getYear()>0)
			return messageService.getMessageBasedOnYear(filterBean.getYear());
		if(filterBean.getStart() >=0 && filterBean.getSize() >= 0)
			return messageService.getMessageListWithPagination(filterBean.getStart(), filterBean.getSize());
		return messageService.getMessageList();
	}
	
	@GET
	@Path("/{id}")
	public Message getMessageDetails(@PathParam("id") long id){
		return messageService.getMessage(id);
	}
	
	@POST
	public Message saveMessage(Message msg){
		return  messageService.addMessage(msg);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id,Message msg){
		msg.setId(id);
		return messageService.updateMessage(msg);
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message removeMessage(@PathParam("messageId") long id){
		return messageService.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId/comments}")
	public CommentsResource getComments(){
		return new CommentsResource();
	}
	
}
