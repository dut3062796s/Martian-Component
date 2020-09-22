package com.mars.cloud.core.register.model;

import com.mars.common.annotation.enums.ReqMethod;

/**
 * rest接口实体类
 */
public class RestApiModel {

    /**
     * url
     */
    private String url;

    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 请求方式
     */
    private ReqMethod reqMethod;

    /**
     * 方法名称
     */
    private String methodName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public ReqMethod getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(ReqMethod reqMethod) {
        this.reqMethod = reqMethod;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
