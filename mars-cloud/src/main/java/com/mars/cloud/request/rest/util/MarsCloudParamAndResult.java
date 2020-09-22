package com.mars.cloud.request.rest.util;

import com.mars.common.constant.MarsConstant;
import com.mars.common.util.SerializableUtil;
import com.mars.iserver.par.base.BaseParamAndResult;
import com.mars.mvc.util.ParamAndResult;
import com.mars.server.server.request.HttpMarsRequest;
import com.mars.server.server.request.HttpMarsResponse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 入参与响应数据
 */
public class MarsCloudParamAndResult implements BaseParamAndResult {

    /**
     * 获取参数
     * @param method 方法
     * @param request 请求
     * @param response 响应
     * @return 参数
     * @throws Exception 异常
     */
    @Override
    public Object[] getParam(Method method, HttpMarsRequest request, HttpMarsResponse response) throws Exception {
        try {
            return ParamAndResultFactory.getParamAndResult().getParam(method,request,response);
        } catch (Exception e){
            throw new Exception("参数注入异常",e);
        }
    }

    /**
     * 返回数据
     * @param response 响应
     * @param resultObj 返回的数据
     * @throws Exception 异常
     */
    @Override
    public void result(HttpMarsResponse response, Object resultObj) throws Exception {
        byte[] bytes = new byte[0];
        if(resultObj != null && !resultObj.toString().equals(MarsConstant.VOID)) {
            bytes = SerializableUtil.serialization(resultObj);
        }
        response.downLoad("blank",converToInputStream(bytes));
    }

    /**
     * 将序列化后的对象转成输入流
     * @param by
     * @return
     */
    private InputStream converToInputStream(byte[] by){
        InputStream swapStream = new ByteArrayInputStream(by);
        return swapStream;
    }
}