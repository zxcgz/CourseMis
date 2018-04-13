package com.coursemis.message;

/**
 * 点名的消息类
 * @author lenovo
 *
 */
public class CallTheRollMess {
	private String tag ;
	private String type ;
	private String mess ;
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}
}
