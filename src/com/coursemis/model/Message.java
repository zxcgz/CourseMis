package com.coursemis.model;

import java.sql.Timestamp;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields

	private Integer MId;
	private Teacher teacher;
	private String MReceive;
	private String MContent;
	private Timestamp MDatetime;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** full constructor */
	public Message(Teacher teacher, String MReceive, String MContent,
			Timestamp MDatetime) {
		this.teacher = teacher;
		this.MReceive = MReceive;
		this.MContent = MContent;
		this.MDatetime = MDatetime;
	}

	// Property accessors

	public Integer getMId() {
		return this.MId;
	}

	public void setMId(Integer MId) {
		this.MId = MId;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getMReceive() {
		return this.MReceive;
	}

	public void setMReceive(String MReceive) {
		this.MReceive = MReceive;
	}

	public String getMContent() {
		return this.MContent;
	}

	public void setMContent(String MContent) {
		this.MContent = MContent;
	}

	public Timestamp getMDatetime() {
		return this.MDatetime;
	}

	public void setMDatetime(Timestamp MDatetime) {
		this.MDatetime = MDatetime;
	}

}