package com.puhui.config;

/**
 * Created by puhui on 2017/7/11.
 */
public interface BeanDefinition {
	String getParentName();

	void setParentName(String var1);

	String getBeanClassName();

	void setBeanClassName(String var1);

	String getScope();

	void setScope(String var1);

	String[] getDependsOn();

	void setDependsOn(String[] var1);

	boolean isSingleton();

	boolean isPrototype();

	<T> T getObject();

	<T> void setObject(T beanObject);
}
