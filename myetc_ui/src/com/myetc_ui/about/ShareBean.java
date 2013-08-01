package com.myetc_ui.about;

import java.util.HashMap;
import java.util.Map;

import com.myetc_ui.about.scanner.Scanner_Type_Enum;

import android.content.Context;

public class ShareBean {
private int t;
private Class c;
private Scanner_Type_Enum ste;

public ShareBean(int t, Class c) {
	super();
	this.t = t;
	this.c = c;
}

public ShareBean(int t, Class c, Scanner_Type_Enum ste) {
	super();
	this.t = t;
	this.c = c;
	this.ste = ste;
}

public int getT() {
	return t;
}

public void setT(int t) {
	this.t = t;
}

public Class getC() {
	return c;
}
public void setC(Class c) {
	this.c = c;
}

public Scanner_Type_Enum getSte() {
	return ste;
}

public void setSte(Scanner_Type_Enum ste) {
	this.ste = ste;
}


}	
