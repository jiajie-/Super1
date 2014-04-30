package com.sise.beans;
public class Card {
	private String course_name;
	private String course_code;
	private String status;
	private int icon;
	
	public Card(String course_name, String course_code,String status) {
		this.course_name = course_name;
		this.course_code = course_code;
		this.status=status;
	}
	
	public String getName() {
		return course_name;
	}
	public void setName(String name) {
		this.course_name = name;
	}
	public String getDesc() {
		return course_code;
	}
	public void setDesc(String desc) {
		this.course_code = desc;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}