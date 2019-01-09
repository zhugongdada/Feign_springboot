package com.transnova.demo.baidu;


import feign.Headers;
import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

@Headers("Accept: application/json")
public interface BAIDUService {

    @Headers("Content-Type: application/json")
    @RequestLine("GET /traffic/v1/road?")
    String road(@QueryMap Map<String,Object> map);
}
