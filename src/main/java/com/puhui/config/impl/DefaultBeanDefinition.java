package com.puhui.config.impl;

import com.puhui.config.BeanDefinition;
import javafx.beans.binding.ObjectExpression;

/**
 * Created by puhui on 2017/7/11.
 */
public class DefaultBeanDefinition implements BeanDefinition {

    private Object object;
    private String parentName;
    private String beanClassName;
    private String scope;
    private String[] dependsOn;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String className) {
        this.beanClassName = className;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String[] getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(String[] dependsOn) {
        this.dependsOn = dependsOn;
    }

    public boolean isSingleton() {
        return "singleton".equals(scope.toLowerCase());
    }

    public boolean isPrototype() {
        return "prototype".equals(scope.toLowerCase());
    }

    public <T> void setObject(T obj) {
        this.object = obj;
    }

    public <T> T getObject() {
        return (T) object;
    }
}
