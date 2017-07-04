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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "demo_user" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5733736552735667173L;
	private Integer  id;
	private String userId;
	private String nickName;
	private String passWord;
	private String createBy;
	private Date createDate;
	private String modifyBy;
	private Date modifyDate;
	
	@Id
	@Column(name = "id", unique = true, length = 36)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen")
	@SequenceGenerator(name="gen",sequenceName="seq_demo_user")
 	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "userid")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "nickName",length = 36)
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Column(name = "passWord",length = 36)
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	@Column(name = "createBy",length = 36)
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
	
	@Column(name = "modifyBy",length = 36)
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
	
	
	
}
