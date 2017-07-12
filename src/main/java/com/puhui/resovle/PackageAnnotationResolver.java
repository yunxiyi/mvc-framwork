package com.puhui.resovle;

import com.puhui.config.BeanDefinition;

/**
 * Created by puhui on 2017/7/11.
 */
public interface PackageAnnotationResolver {
    Class[] getClass(String packageName);
}
