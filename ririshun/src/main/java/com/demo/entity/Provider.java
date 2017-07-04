package com.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/*
 * 供应商信息 表
 */

@Entity
@Table(name = "demo_provider" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Provider {
	
	private Integer id;
	private String provider_name;//供应商名字 
	private String createBy;
	private Date createDate;
	private String modifyBy;
	private Date modifyDate;
	
	
	
	private String startTimeQuery;
	private String endTimeQuery;
	
	private String startTimeQuerym;
	private String endTimeQuerym;
	
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen")
	@SequenceGenerator(name="gen",sequenceName="seq_demo_provider")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "provider_name")
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
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
	public String getStartTimeQuerym() {
		return startTimeQuerym;
	}
	public void setStartTimeQuerym(String startTimeQuerym) {
		this.startTimeQuerym = startTimeQuerym;
	}
	public String getEndTimeQuerym() {
		return endTimeQuerym;
	}
	public void setEndTimeQuerym(String endTimeQuerym) {
		this.endTimeQuerym = endTimeQuerym;
	}
	
	
	
	
}
