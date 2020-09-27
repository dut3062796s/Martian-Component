package com.mars.gateway.api;

import com.alibaba.fastjson.JSON;
import com.mars.cloud.request.util.model.HttpResultModel;
import com.mars.gateway.api.filter.Filter;
import com.mars.gateway.api.filter.business.GateFactory;
import com.mars.gateway.api.filter.business.GateFilter;
import com.mars.gateway.api.model.RequestInfoModel;
import com.mars.gateway.core.util.RequestAndResultUtil;
import com.mars.gateway.request.RequestServer;
import com.mars.server.server.request.HttpMarsRequest;
import com.mars.server.server.request.HttpMarsResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 核心控制器，用来实现转发和响应
 */
public class GateWayDispatcher implements HttpHandler {

    private Logger log = LoggerFactory.getLogger(GateWayDispatcher.class);

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            HttpMarsRequest request = new HttpMarsRequest(httpExchange);
            HttpMarsResponse response = new HttpMarsResponse(httpExchange);

            // 过滤非法请求
            String filterResult = Filter.filter(request);
            if(!filterResult.equals(GateFilter.SUCCESS)){
                response.send(filterResult);
                return;
            }

            // 执行过滤器
            Object result = execFilter(request, response);
            if(result != null && !result.toString().equals(GateFilter.SUCCESS)){
                response.send(JSON.toJSONString(result));
                return;
            }

            String requestUri = request.getUrl();
            RequestInfoModel requestInfoModel = RequestAndResultUtil.getServerNameAndMethodName(requestUri);

            if(requestUri.startsWith(RequestAndResultUtil.ROUTER)){
                Object object = RequestServer.doRouterRequest(requestInfoModel, request);
                response.send(JSON.toJSONString(object));
            } else if(requestUri.startsWith(RequestAndResultUtil.DOWNLOAD)) {
                HttpResultModel httpResultModel = RequestServer.doDownLoadRequest(requestInfoModel, request);
                String fileName = getFileName(httpResultModel);
                response.downLoad(fileName, httpResultModel.getInputStream());
            } else {
                throw new IOException("请求路径有误");
            }
        } catch (Exception e) {
            log.error("处理请求失败!", e);
            RequestAndResultUtil.send(httpExchange,"处理请求发生错误"+e.getMessage());
        }
    }

    /**
     * 执行过滤器
     * @param request
     * @param response
     * @return
     */
    private Object execFilter(HttpMarsRequest request, HttpMarsResponse response){
        List<GateFilter> gateFilterList = GateFactory.getGateFilter();
        if(gateFilterList != null && gateFilterList.size() > 0){
            for(GateFilter gateFilter : gateFilterList){
                Object result = gateFilter.doFilter(request, response);
                if(result != null && !result.toString().equals(GateFilter.SUCCESS)){
                    return result;
                }
            }
        }
        return GateFilter.SUCCESS;
    }

    /**
     * 获取文件名称
     * @param httpResultModel
     * @return
     */
    private String getFileName(HttpResultModel httpResultModel){
        String fileName = httpResultModel.getFileName();
        if(fileName == null){
            fileName = UUID.randomUUID().toString();
        } else {
            String[] fileNames = fileName.split("=");
            if(fileNames == null || fileNames.length < 2){
                fileName = UUID.randomUUID().toString();
            } else {
                fileName = fileNames[1];
            }
        }
        return fileName;
    }
}