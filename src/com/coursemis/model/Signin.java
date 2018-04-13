package com.coursemis.model;

/**
 * Signin entity. @author MyEclipse Persistence Tools
 */

public class Signin implements java.io.Serializable {

	// Fields

	private Integer id;
	private Teacher teacher;
	private Course course;
	private Student student;
	private Integer flag;

	// Constructors

	/** default constructor */
	public Signin() {
	}

	/** full constructor */
	public Signin(Teacher teacher, Course course, Student student, Integer flag) {
		this.teacher = teacher;
		this.course = course;
		this.student = student;
		this.flag = flag;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}