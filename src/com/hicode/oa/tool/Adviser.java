package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;

/**
 * 顾问详情
 * 
 * @author xinpeixiang
 * @date 2018-10-19
 */
public class Adviser implements Serializable{

	

	private static final long serialVersionUID = 145343123452311L;
	
	private String adv_id;
	private String adv_name;
	/**
	 * 顾问性别（1：男；0：女）
	 */
	private Integer adv_sex;
	
	/**
	 * 当前职务
	 */
	private String title;
	
	/**
	 * 是否在职（1：在职；0：不在职）
	 */
	private Integer if_Onthejob;
	/**
	 * 入职时间
	 */
	private Date time_creatDate;
	/**
	 * 离职时间
	 */
	private Date time_endDate;
	
	/**
	 * 晋级时间
	 */
	private Date title_updatetime;

	public String getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(String adv_id) {
		this.adv_id = adv_id;
	}

	public String getAdv_name() {
		return adv_name;
	}

	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}

	public Integer getAdv_sex() {
		return adv_sex;
	}

	public void setAdv_sex(Integer adv_sex) {
		this.adv_sex = adv_sex;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIf_Onthejob() {
		return if_Onthejob;
	}

	public void setIf_Onthejob(Integer if_Onthejob) {
		this.if_Onthejob = if_Onthejob;
	}

	public Date getTime_creatDate() {
		return time_creatDate;
	}

	public void setTime_creatDate(Date time_creatDate) {
		this.time_creatDate = time_creatDate;
	}

	public Date getTime_endDate() {
		return time_endDate;
	}

	public void setTime_endDate(Date time_endDate) {
		this.time_endDate = time_endDate;
	}

	public Date getTitle_updatetime() {
		return title_updatetime;
	}

	public void setTitle_updatetime(Date title_updatetime) {
		this.title_updatetime = title_updatetime;
	}
	@Override
	public String toString() {
		return "Adviser [adv_id=" + adv_id + ", adv_name=" + adv_name + ", adv_sex=" + adv_sex + ", title=" + title
				+ ", if_Onthejob=" + if_Onthejob + ", time_creatDate=" + time_creatDate + ", time_endDate="
				+ time_endDate + ", title_updatetime=" + title_updatetime + "]";
	}
	
}
