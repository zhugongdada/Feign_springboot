package com.transnova.demo.testng;

import com.beust.jcommander.Parameter;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

/**
 * Created by tian on 2018.12.17.
 */
@Headers("Accept: application/json")
public interface DriverInterface {
    @Headers({"Content-Type: application/json", "Cookie: {cookie}"})//坑： 将cookie写入请求头，需以{}格式写入
    @RequestLine("GET /transnova-government-web/driver/index/find/?")
    String driverIndex(@QueryMap Map<String,Object> map, @Param(value = "cookie") String cookie);

    @RequestLine("GET /item/你好/32416?fr={fr}")
    String baidu(@Param(value ="fr" ) String fr);
}
