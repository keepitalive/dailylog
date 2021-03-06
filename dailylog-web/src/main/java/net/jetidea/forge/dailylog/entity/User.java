package net.jetidea.forge.dailylog.entity;

// Generated 2013-10-17 15:38:10 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 7251057012753502918L;
	private Integer usrid;
	private Integer version;
	private String usrname;
	private String password;
	private String nickname;
	private Integer gender;
	private String email;
	private int createdBy;
	private Date createdTime;
	private Integer updatedBy;
	private Date updatedTime;
	private Set<LogInfo> logInfos = new HashSet<LogInfo>(0);

	public User() {
	}

	public User(String usrname, String password, String nickname, int createdBy) {
		this.usrname = usrname;
		this.password = password;
		this.nickname = nickname;
		this.createdBy = createdBy;
	}

	public User(String usrname, String password, String nickname, Integer gender, String email, int createdBy,
			Date createdTime, Integer updatedBy, Date updatedTime, Set<LogInfo> logInfos) {
		this.usrname = usrname;
		this.password = password;
		this.nickname = nickname;
		this.gender = gender;
		this.email = email;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.logInfos = logInfos;
	}

	public Integer getUsrid() {
		return this.usrid;
	}

	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getUsrname() {
		return this.usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Set<LogInfo> getLogInfos() {
		return this.logInfos;
	}

	public void setLogInfos(Set<LogInfo> logInfos) {
		this.logInfos = logInfos;
	}

}
