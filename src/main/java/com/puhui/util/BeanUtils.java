package com.puhui.util;

import com.puhui.annotation.Bean;
import com.puhui.annotation.Controller;
import com.puhui.config.BeanDefinition;
import com.puhui.test.BeanTest;

import java.lang.annotation.Annotation;

/**
 * Created by puhui on 2017/7/10.
 */
public class BeanUtils {
	public static String resolveBeanAnnotion(Class clazz){
		Annotation[] annotations =clazz.getAnnotations();
		for(Annotation annotation : annotations){
			if(annotation instanceof Bean){
				Bean beanAnnotation = (Bean) annotation;
				System.out.println(beanAnnotation.name() + ":" );
			}else if(annotation instanceof Controller){

			}
		}
		return null;
	}

	public static String resolveBeanAnnotion(Class clazz, BeanDefinition beanDefinition){
		boolean isBean = false;
		boolean isController = false;
		boolean isService = false;

		String beanName =null;

		Bean beanAnnotation = (Bean) clazz.getAnnotation(Bean.class);

		if(beanAnnotation != null) {
			beanName = beanAnnotation.name();
			isBean = true;
		}

		if(!isBean) {
			Controller controllerAnnotation = (Controller) clazz.getAnnotation(Controller.class);
			if(controllerAnnotation != null){
				beanName = beanAnnotation.name();
				isBean = true;
			}
		}
		if(beanAnnotation == null){
			beanName = clazz.getSimpleName();
			String first = beanName.substring(0, 1);
			beanName = first.toLowerCase() + beanName.substring(1, beanName.length());
		}
		return beanName;
	}

	public static void main(String [] args){
		resolveBeanAnnotion(BeanTest.class);
	}
}
