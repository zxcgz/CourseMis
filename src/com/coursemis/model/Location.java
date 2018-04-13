package com.coursemis.model;

/**
 * Location entity. @author MyEclipse Persistence Tools
 */

public class Location implements java.io.Serializable {

	// Fields

	private Integer TId;
	private Course course;
	private Teacher teacher;
	private Double latitude;
	private Double longitude;

	// Constructors

	/** default constructor */
	public Location() {
	}

	/** minimal constructor */
	public Location(Course course, Teacher teacher) {
		this.course = course;
		this.teacher = teacher;
	}

	/** full constructor */
	public Location(Course course, Teacher teacher, Double latitude,
			Double longitude) {
		this.course = course;
		this.teacher = teacher;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	// Property accessors

	public Integer getTId() {
		return this.TId;
	}

	public void setTId(Integer TId) {
		this.TId = TId;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}