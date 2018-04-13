package com.coursemis.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course implements java.io.Serializable {

	// Fields

	private Integer CId;
	private Teacher teacher;
	private String CNum;
	private String CName;
	private Boolean CFlag;
	private Integer CPointTotalNum;
	private Set coursetimes = new HashSet(0);
	private Set locations = new HashSet(0);
	private Set evaluations = new HashSet(0);
	private Set scores = new HashSet(0);
	private Set periods = new HashSet(0);
	private Set studentcourses = new HashSet(0);
	private Set signins = new HashSet(0);
	private Set sourcemanages = new HashSet(0);
	private Set questionbanks = new HashSet(0);
	private Set courseevaluations = new HashSet(0);

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** minimal constructor */
	public Course(Teacher teacher, String CNum, String CName, Boolean CFlag,
			Integer CPointTotalNum) {
		this.teacher = teacher;
		this.CNum = CNum;
		this.CName = CName;
		this.CFlag = CFlag;
		this.CPointTotalNum = CPointTotalNum;
	}

	/** full constructor */
	public Course(Teacher teacher, String CNum, String CName, Boolean CFlag,
			Integer CPointTotalNum, Set coursetimes, Set locations,
			Set evaluations, Set scores, Set periods, Set studentcourses,
			Set signins, Set sourcemanages, Set questionbanks,
			Set courseevaluations) {
		this.teacher = teacher;
		this.CNum = CNum;
		this.CName = CName;
		this.CFlag = CFlag;
		this.CPointTotalNum = CPointTotalNum;
		this.coursetimes = coursetimes;
		this.locations = locations;
		this.evaluations = evaluations;
		this.scores = scores;
		this.periods = periods;
		this.studentcourses = studentcourses;
		this.signins = signins;
		this.sourcemanages = sourcemanages;
		this.questionbanks = questionbanks;
		this.courseevaluations = courseevaluations;
	}

	// Property accessors

	public Integer getCId() {
		return this.CId;
	}

	public void setCId(Integer CId) {
		this.CId = CId;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getCNum() {
		return this.CNum;
	}

	public void setCNum(String CNum) {
		this.CNum = CNum;
	}

	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	public Boolean getCFlag() {
		return this.CFlag;
	}

	public void setCFlag(Boolean CFlag) {
		this.CFlag = CFlag;
	}

	public Integer getCPointTotalNum() {
		return this.CPointTotalNum;
	}

	public void setCPointTotalNum(Integer CPointTotalNum) {
		this.CPointTotalNum = CPointTotalNum;
	}

	public Set getCoursetimes() {
		return this.coursetimes;
	}

	public void setCoursetimes(Set coursetimes) {
		this.coursetimes = coursetimes;
	}

	public Set getLocations() {
		return this.locations;
	}

	public void setLocations(Set locations) {
		this.locations = locations;
	}

	public Set getEvaluations() {
		return this.evaluations;
	}

	public void setEvaluations(Set evaluations) {
		this.evaluations = evaluations;
	}

	public Set getScores() {
		return this.scores;
	}

	public void setScores(Set scores) {
		this.scores = scores;
	}

	public Set getPeriods() {
		return this.periods;
	}

	public void setPeriods(Set periods) {
		this.periods = periods;
	}

	public Set getStudentcourses() {
		return this.studentcourses;
	}

	public void setStudentcourses(Set studentcourses) {
		this.studentcourses = studentcourses;
	}

	public Set getSignins() {
		return this.signins;
	}

	public void setSignins(Set signins) {
		this.signins = signins;
	}

	public Set getSourcemanages() {
		return this.sourcemanages;
	}

	public void setSourcemanages(Set sourcemanages) {
		this.sourcemanages = sourcemanages;
	}

	public Set getQuestionbanks() {
		return this.questionbanks;
	}

	public void setQuestionbanks(Set questionbanks) {
		this.questionbanks = questionbanks;
	}

	public Set getCourseevaluations() {
		return this.courseevaluations;
	}

	public void setCourseevaluations(Set courseevaluations) {
		this.courseevaluations = courseevaluations;
	}

}