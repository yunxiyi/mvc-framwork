package com.puhui.beans.factory;

import com.puhui.exception.NotFoundBean;

/**
 * Created by puhui on 2017/7/11.
 */
public interface BeanFactory {

	<T> T getBean(String name) throws NotFoundBean;

	<T> T getBean(String name, Object... args) throws NotFoundBean;

	<T> T getBean(Class<T> clazz) throws NotFoundBean;

	boolean isSingleton(String beanName) throws NotFoundBean;

	boolean isPrototype(String beanName) throws NotFoundBean;

}
