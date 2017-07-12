package com.puhui.test;

import com.puhui.annotation.Bean;
import com.puhui.annotation.Parmeter;

/**
 * Created by puhui on 2017/7/10.
 */
@Bean("beanTest")
public class BeanTest {

    @Parmeter("10")
    private String id;

    @Parmeter(value = "yunxiyi")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
