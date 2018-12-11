package com.vijay.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.vijay.messenger.model.Message;
import com.vijay.messenger.model.Profile;

public class DatabaseClass {

	private static Map<Long,Message> messages = new HashMap<Long,Message>();
	private static Map<String,Profile> profile = new HashMap<String,Profile>();
	
	public static Map<Long,Message> getMessages(){
		return messages;
	}
	
	public static Map<String,Profile> getProfiles(){
		return profile;
	}
}
