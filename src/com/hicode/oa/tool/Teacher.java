package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;

/**
 * ��ʦ��
 * @author xinpeixiang 
 * @date 2018-10-19
 */
public class Teacher  implements Serializable{
	
	private static final long serialVersionUID = 10134541546451L;
	private String t_id;
	private String t_name;
	/**
	 * ��ʦ�Ա�1���У�0��Ů��
	 */
	private Integer t_sex;
	/**
	 * �Ƿ���ְ��1����ְ��0������ְ��
	 */
	private Integer if_Onthejob;
	/**
	 * ��ְʱ��
	 */
	private Date time_creatDate;
	/**
	 * ��ְʱ��
	 */
	private Date time_endDate;
	/**
	 * ��ǰְ��/ְ��
	 */
	private String title;
	/**
	 * ����ʱ��
	 */
	private Date title_updatetime;
	
	
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public Integer getT_sex() {
		return t_sex;
	}
	public void setT_sex(Integer t_sex) {
		this.t_sex = t_sex;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getTitle_updatetime() {
		return title_updatetime;
	}
	public void setTitle_updatetime(Date title_updatetime) {
		this.title_updatetime = title_updatetime;
	}
	

}
