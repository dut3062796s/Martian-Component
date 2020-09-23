package com.mars.cloud.request.rest.test.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class TestParamModelA {

    private String name = "张三";

    private Integer age = 12;

//    private TestParamModelC testParamModelC = new TestParamModelC();

    @JSONField(format = "yyyy-MM-dd")
    private Date date = new Date();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    public TestParamModelC getTestParamModelC() {
//        return testParamModelC;
//    }
//
//    public void setTestParamModelC(TestParamModelC testParamModelC) {
//        this.testParamModelC = testParamModelC;
//    }
}
