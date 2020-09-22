package com.mars.cloud.request.rest.util;

import com.mars.mvc.util.ParamAndResult;

/**
 * 获取Martian的参数和返回处理对象
 */
public class ParamAndResultFactory {

    private static ParamAndResult paramAndResult = new ParamAndResult();

    /**
     * 获取Martian的参数和返回处理对象
     * @return
     */
    public static ParamAndResult getParamAndResult(){
        return paramAndResult;
    }
}
