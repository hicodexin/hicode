package com.hicode.oa.tool;

import java.io.Serializable;
import java.util.Date;

/**
 * ����
 * 
 * @author xinpeixiang
 * @date 2018-10-19
 */
public class Adviser implements Serializable{

	private static final long serialVersionUID = 145343123452311L;
	
	private String adv_id;
	private String adv_name;
	/**
	 * �����Ա�1���У�0��Ů��
	 */
	private Integer adv_sex;
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

}
