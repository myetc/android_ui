package com.myetc_ui.bean;

public class NewSpinner {
	private String title;
	private String state;
	private String time;
	private String source;
	private int heat;
	public NewSpinner(String title, String state, String time, String source, int heat) {
		super();
		this.title = title;
		this.state = state;
		this.time = time;
		this.source = source;
		this.heat = heat;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getHeat() {
		return heat;
	}
	public void setHeat(int heat) {
		this.heat = heat;
	}
	
}
