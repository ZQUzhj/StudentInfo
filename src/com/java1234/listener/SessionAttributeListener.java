package com.java1234.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionAttributeListener implements HttpSessionAttributeListener{

	public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
		// TODO Auto-generated method stub
		System.out.println("��ӵ���������"+httpSessionBindingEvent.getName()+"����ֵ��"+httpSessionBindingEvent.getValue());
	}

	public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
		// TODO Auto-generated method stub
		System.out.println("ɾ������������"+httpSessionBindingEvent.getName()+"����ֵ��"+httpSessionBindingEvent.getValue());
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
