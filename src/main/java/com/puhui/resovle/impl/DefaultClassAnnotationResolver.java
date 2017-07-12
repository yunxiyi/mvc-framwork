package com.puhui.resovle.impl;

import com.puhui.config.BeanDefinition;
import com.puhui.config.impl.DefaultBeanDefinition;
import com.puhui.resovle.ClassAnnotationResolver;
import com.puhui.util.ClassHelperUtils;
import org.apache.log4j.Logger;

/**
 * Created by puhui on 2017/7/11.
 */
public class DefaultClassAnnotationResolver implements ClassAnnotationResolver {

    Logger logger = Logger.getLogger(DefaultClassAnnotationResolver.class);

    public BeanDefinition getBeanDefinition(String className) {
        BeanDefinition beanDefinition = new DefaultBeanDefinition();
        try {
            ClassHelperUtils.getClass(className);
            beanDefinition.setBeanClassName(className);

            return beanDefinition;
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public BeanDefinition getBeanDefinition(Class clazz) {
        return null;
    }

}
