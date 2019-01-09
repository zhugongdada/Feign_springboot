package com.transnova.demo;

import feign.*;

import java.util.Map;

/**
 * Created by DELL on 2018/6/20.
 * 免费api： https://www.apishop.net/#/api/detail/?productID=73
 */
@Headers("Accept: application/json")
public interface IRemoteService {

    @Headers({"Content-Type: application/json", "Cookie: {cookie}"})//坑： 将cookie写入请求头，需以{}格式写入
    @RequestLine("GET transnova-government-web/company/overview/?companyId={companyId}")
    String driver(@Param(value = "companyId") String companyId, @Param(value = "cookie") String cookie);

    //获取个人指数评分统计
    @Headers({"Content-Type: application/json", "Cookie: {cookie}"})
    @RequestLine("GET /transnova-government-web/driver/overview/getLastWeekly?driverId={driverId}")
    String driver_Index(@Param(value = "driverId") String driverId,@Param(value = "cookie")String cookie);

    //个人历史事件类型
    @Headers({"Content-Type: application/json", "Cookie: {cookie}"})
    @RequestLine("GET /transnova-government-web/driver/event/eventLogAnalyze?")
    String driver_event(@QueryMap Map<String,Object> map,@Param(value = "cookie") String cookie);

    //个人历史运行轨迹统计
    @Headers({"Content-Type: application/json", "Cookie: {cookie}"})
    @RequestLine("GET /transnova-government-web/vehicle/analyze/track/lastn?vehicleId={vehicleId}")
    String driver_speedlog(@Param(value = "vehicleId")String vehicleId,@Param(value = "cookie")String cookie);

}
