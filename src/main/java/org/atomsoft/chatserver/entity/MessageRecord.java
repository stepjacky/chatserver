package org.atomsoft.chatserver.entity;

import java.util.Date;

public class MessageRecord {
	
	private String master;
	private String slaver;
	private Date date;
	private String content;
	

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getSlaver() {
		return slaver;
	}

	public void setSlaver(String slaver) {
		this.slaver = slaver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
