package com.mars.cloud.request.rest.test;


import com.alibaba.fastjson.JSONObject;
import com.mars.cloud.request.rest.model.RequestParamModel;
import com.mars.cloud.request.rest.test.model.TestParamModelA;
import com.mars.cloud.request.rest.test.model.TestParamModelB;
import com.mars.cloud.request.rest.test.model.TestParamModelC;
import com.mars.cloud.request.rest.util.ParamConversionUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

/**
 * 测试参数转化类
 */
public class ParamConversionUtilTest {

    /**
     * 将Object数组转成一个json对象
     */
    @Test
    public void conversionToJson(){
        try {
            Long start = new Date().getTime();

            TestParamModelA testParamModelA = new TestParamModelA();
            TestParamModelB testParamModelB = new TestParamModelB();

            Object[] objects = new Object[]{testParamModelA, testParamModelB};

            JSONObject jsonObject = ParamConversionUtil.conversionToJson(objects);

            Long end = new Date().getTime();

            System.out.println("参数转JSON完成，耗时:" + (end - start));

            Assert.assertEquals(testParamModelA.getName(), jsonObject.getString("name"));
            Assert.assertEquals(testParamModelA.getAge(), jsonObject.getInteger("age"));

            Assert.assertEquals(testParamModelB.getName2(), jsonObject.getString("name2"));
            Assert.assertEquals(testParamModelB.getAge2(), jsonObject.getInteger("age2"));

            System.out.println(jsonObject.toJSONString());
        } catch (Exception e){
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    /**
     * 将参数转成统一规格的对象集合
     * @return
     * @throws Exception
     */
    @Test
    public void getRequestParamModelList() {
        try {
            Long start = new Date().getTime();

            TestParamModelA testParamModelA = new TestParamModelA();
            TestParamModelC testParamModelC = new TestParamModelC();

            Object[] objects = new Object[]{testParamModelA, testParamModelC};

            Map<String, RequestParamModel> requestParamModelMap = ParamConversionUtil.getRequestParamModelList(objects);

            Long end = new Date().getTime();

            System.out.println("参数转对象完成，耗时:" + (end - start));


            Assert.assertEquals(testParamModelA.getName(), requestParamModelMap.get("name").getValue());
            Assert.assertEquals(testParamModelA.getAge(), requestParamModelMap.get("age").getValue());

            Assert.assertEquals(testParamModelC.getName3(), requestParamModelMap.get("name3").getValue());
            Assert.assertEquals(testParamModelC.getAge3(), requestParamModelMap.get("age3").getValue());

            for(RequestParamModel requestParamModel : requestParamModelMap.values()){
                System.out.println(requestParamModel);
            }

        } catch (Exception e){
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
