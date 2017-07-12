package com.puhui.beans.factory;

import com.puhui.config.BeanDefinition;
import com.puhui.exception.NotFoundBean;
import com.puhui.util.ClassHelperUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by puhui on 2017/7/11.
 */
public abstract class DefaultBeanFactory implements BeanFactory {

    Logger logger = Logger.getLogger(DefaultBeanFactory.class);

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    public <T> T getBean(String name) throws NotFoundBean {
        return getBean(name, null);
    }

    public <T> T getBean(String name, Object... args) throws NotFoundBean {
        BeanDefinition beanDefinition = findBeanDefinition(name);
        return getInstance(beanDefinition, args);
    }

    public <T> T getBean(Class<T> clazz) throws NotFoundBean {
        BeanDefinition beanDefinition = findBeanDefinition(clazz);
        return getInstance(beanDefinition);
    }

    public boolean isSingleton(String beanName) throws NotFoundBean {
        BeanDefinition beanDefinition = findBeanDefinition(beanName);
        return beanDefinition.isSingleton();
    }

    public boolean isPrototype(String beanName) throws NotFoundBean {
        BeanDefinition beanDefinition = findBeanDefinition(beanName);
        return beanDefinition.isPrototype();
    }

    private BeanDefinition findBeanDefinition(String name) throws NotFoundBean {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition != null) {
            return beanDefinition;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("null find such bean name = " + name);
        }
        throw new NotFoundBean("no such bean name = " + name);
    }

    private BeanDefinition findBeanDefinition(Class clazz) throws NotFoundBean {
        String className = clazz.getName();
        if (className == null) {
            return null;
        }
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            BeanDefinition bd = entry.getValue();
            if (className.equals(bd.getBeanClassName())) {
                return bd;
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("null find such bean class name = " + className);
        }
        throw new NotFoundBean("no such bean class name = " + className);
    }

    private <T> T getInstance(BeanDefinition beanDefinition, Object... args) {
        if (beanDefinition.getObject() != null) {
            return beanDefinition.getObject();
        }

        String beanClassName = beanDefinition.getBeanClassName();
        try {
            T beanObject = (T) ClassHelperUtils.getObjectByConstructor(beanClassName, args);
            beanDefinition.setObject(beanObject);
            return (T) beanObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(beanClassName + " bean initail fail");
        }
    }

    public Map<String, BeanDefinition> getBeanDefinitionMap() {
        return beanDefinitionMap;
    }

    public void setBeanDefinitionMap(Map<String, BeanDefinition> beanDefinitionMap) {
        this.beanDefinitionMap = beanDefinitionMap;
    }
}
