package com.myetc_ui.about.scanner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)   
@Retention(RetentionPolicy.RUNTIME)//ע�����class�ֽ����ļ��д��ڣ�������ʱ����ͨ�������ȡ�� 
public @interface Scanner_Type {
	Scanner_Type_Enum type();
	int name();
}
