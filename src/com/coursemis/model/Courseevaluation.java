package com.coursemis.model;

/**
 * Courseevaluation entity. @author MyEclipse Persistence Tools
 */

public class Courseevaluation implements java.io.Serializable {

	// Fields

	private Integer ceId;
	private Student student;
	private Course course;
	private Integer ceWeekNum;
	private Integer ceQuestion1;
	private Integer ceQuestion2;
	private Integer ceQuestion3;
	private String ceFeedback;

	// Constructors

	/** default constructor */
	public Courseevaluation() {
	}

	/** minimal constructor */
	public Courseevaluation(Student student, Course course, Integer ceWeekNum,
			Integer ceQuestion1, Integer ceQuestion2, Integer ceQuestion3) {
		this.student = student;
		this.course = course;
		this.ceWeekNum = ceWeekNum;
		this.ceQuestion1 = ceQuestion1;
		this.ceQuestion2 = ceQuestion2;
		this.ceQuestion3 = ceQuestion3;
	}

	/** full constructor */
	public Courseevaluation(Student student, Course course, Integer ceWeekNum,
			Integer ceQuestion1, Integer ceQuestion2, Integer ceQuestion3,
			String ceFeedback) {
		this.student = student;
		this.course = course;
		this.ceWeekNum = ceWeekNum;
		this.ceQuestion1 = ceQuestion1;
		this.ceQuestion2 = ceQuestion2;
		this.ceQuestion3 = ceQuestion3;
		this.ceFeedback = ceFeedback;
	}

	// Property accessors

	public Integer getCeId() {
		return this.ceId;
	}

	public void setCeId(Integer ceId) {
		this.ceId = ceId;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getCeWeekNum() {
		return this.ceWeekNum;
	}

	public void setCeWeekNum(Integer ceWeekNum) {
		this.ceWeekNum = ceWeekNum;
	}

	public Integer getCeQuestion1() {
		return this.ceQuestion1;
	}

	public void setCeQuestion1(Integer ceQuestion1) {
		this.ceQuestion1 = ceQuestion1;
	}

	public Integer getCeQuestion2() {
		return this.ceQuestion2;
	}

	public void setCeQuestion2(Integer ceQuestion2) {
		this.ceQuestion2 = ceQuestion2;
	}

	public Integer getCeQuestion3() {
		return this.ceQuestion3;
	}

	public void setCeQuestion3(Integer ceQuestion3) {
		this.ceQuestion3 = ceQuestion3;
	}

	public String getCeFeedback() {
		return this.ceFeedback;
	}

	public void setCeFeedback(String ceFeedback) {
		this.ceFeedback = ceFeedback;
	}

}