package com.coursemis.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer SId;
	private String SNum;
	private String SName;
	private String SSex;
	private String SDepartment;
	private String SClass;
	private String STel;
	private String SEmail;
	private String SPassword;
	private String SHead;
	private Set studenthomeworks = new HashSet(0);
	private Set scores = new HashSet(0);
	private Set studentcourses = new HashSet(0);
	private Set courseevaluations = new HashSet(0);
	private Set signins = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(String SNum, String SName, String SSex, String SDepartment,
			String SClass, String STel, String SPassword) {
		this.SNum = SNum;
		this.SName = SName;
		this.SSex = SSex;
		this.SDepartment = SDepartment;
		this.SClass = SClass;
		this.STel = STel;
		this.SPassword = SPassword;
	}

	/** full constructor */
	public Student(String SNum, String SName, String SSex, String SDepartment,
			String SClass, String STel, String SEmail, String SPassword,
			String SHead, Set studenthomeworks, Set scores, Set studentcourses,
			Set courseevaluations, Set signins) {
		this.SNum = SNum;
		this.SName = SName;
		this.SSex = SSex;
		this.SDepartment = SDepartment;
		this.SClass = SClass;
		this.STel = STel;
		this.SEmail = SEmail;
		this.SPassword = SPassword;
		this.SHead = SHead;
		this.studenthomeworks = studenthomeworks;
		this.scores = scores;
		this.studentcourses = studentcourses;
		this.courseevaluations = courseevaluations;
		this.signins = signins;
	}

	// Property accessors

	public Integer getSId() {
		return this.SId;
	}

	public void setSId(Integer SId) {
		this.SId = SId;
	}

	public String getSNum() {
		return this.SNum;
	}

	public void setSNum(String SNum) {
		this.SNum = SNum;
	}

	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	public String getSSex() {
		return this.SSex;
	}

	public void setSSex(String SSex) {
		this.SSex = SSex;
	}

	public String getSDepartment() {
		return this.SDepartment;
	}

	public void setSDepartment(String SDepartment) {
		this.SDepartment = SDepartment;
	}

	public String getSClass() {
		return this.SClass;
	}

	public void setSClass(String SClass) {
		this.SClass = SClass;
	}

	public String getSTel() {
		return this.STel;
	}

	public void setSTel(String STel) {
		this.STel = STel;
	}

	public String getSEmail() {
		return this.SEmail;
	}

	public void setSEmail(String SEmail) {
		this.SEmail = SEmail;
	}

	public String getSPassword() {
		return this.SPassword;
	}

	public void setSPassword(String SPassword) {
		this.SPassword = SPassword;
	}

	public String getSHead() {
		return this.SHead;
	}

	public void setSHead(String SHead) {
		this.SHead = SHead;
	}

	public Set getStudenthomeworks() {
		return this.studenthomeworks;
	}

	public void setStudenthomeworks(Set studenthomeworks) {
		this.studenthomeworks = studenthomeworks;
	}

	public Set getScores() {
		return this.scores;
	}

	public void setScores(Set scores) {
		this.scores = scores;
	}

	public Set getStudentcourses() {
		return this.studentcourses;
	}

	public void setStudentcourses(Set studentcourses) {
		this.studentcourses = studentcourses;
	}

	public Set getCourseevaluations() {
		return this.courseevaluations;
	}

	public void setCourseevaluations(Set courseevaluations) {
		this.courseevaluations = courseevaluations;
	}

	public Set getSignins() {
		return this.signins;
	}

	public void setSignins(Set signins) {
		this.signins = signins;
	}

}