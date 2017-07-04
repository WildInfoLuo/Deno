package com.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "demo_project" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project {
	private Integer id;
	private String project_name;//供应商名字 
	private String createBy;
	private Date createDate;
	private String modifyBy;
	private Date modifyDate;
	
	private Date selectDate;
	private String projectids;
	private String  ifSelect;
	
	private String startTimeQuery;
	private String endTimeQuery;
	
	private String startTimeQueryS;
	private String endTimeQueryS;
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "project_name")
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	@Column(name = "createBy")
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "createDate")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	@Column(name = "modifyBy")
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "modifyDate")
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	@Column(name = "ifSelect")
	public String getIfSelect() {
		return ifSelect;
	}
	public void setIfSelect(String ifSelect) {
		this.ifSelect = ifSelect;
	}
	
	@Transient
	public String getProjectids() {
		return projectids;
	}
	public void setProjectids(String projectids) {
		this.projectids = projectids;
	}
	@Transient
	public String getStartTimeQuery() {
		return startTimeQuery;
	}
	public void setStartTimeQuery(String startTimeQuery) {
		this.startTimeQuery = startTimeQuery;
	}
	
	@Transient
	public String getEndTimeQuery() {
		return endTimeQuery;
	}
	public void setEndTimeQuery(String endTimeQuery) {
		this.endTimeQuery = endTimeQuery;
	}
	@Transient
	public String getStartTimeQueryS() {
		return startTimeQueryS;
	}
	public void setStartTimeQueryS(String startTimeQueryS) {
		this.startTimeQueryS = startTimeQueryS;
	}
	
	@Transient
	public String getEndTimeQueryS() {
		return endTimeQueryS;
	}
	public void setEndTimeQueryS(String endTimeQueryS) {
		this.endTimeQueryS = endTimeQueryS;
	}
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "selectDate")
	public Date getSelectDate() {
		return selectDate;
	}
	public void setSelectDate(Date selectDate) {
		this.selectDate = selectDate;
	}
	
	
	
	
}
