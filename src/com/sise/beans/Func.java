package com.sise.beans;

public class Func {
	private int func_img;
	private String func_name;
	
	public Func(int func_img,String func_name){
		this.func_img=func_img;
		this.func_name=func_name;
	}
	
	public int getFunc_img() {
		return func_img;
	}
	public void setFunc_img(int func_img) {
		this.func_img = func_img;
	}
	public String getFunc_name() {
		return func_name;
	}
	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}
	
	

}
