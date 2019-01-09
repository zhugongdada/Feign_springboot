package com.transnova.demo.testng;

import com.transnova.demo.IRemoteService;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tian on 2018.12.14.
 */

public class testng {//坑一：使用参数注入未成功，不知道如何在@Test前获取配置文件的参数； 坑二： 通过xml的配置文件可以配置测试的参数； 坑三： 未实现使用DataProviders的参数

    @Parameters({"transnovashost", "transnovasdriverid", "transnovascookie"})
    @Test()
    public void test(String transnovashost, String transnovasdriverid, String transnovascookie) {
        IRemoteService service = Feign.builder()
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(IRemoteService.class, transnovashost);
        String result = service.driver_Index(transnovasdriverid, transnovascookie);
        System.out.println("评分指数查询结果： " + result);
    }

    @Parameters({"transnovashost", "transnovasdriverid", "transnovascookie"})
    @Test(dependsOnMethods = "test")
    public static void events(String transnovashost, String transnovasdriverid, String transnovascookie) {
        IRemoteService service = Feign.builder()
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(IRemoteService.class, transnovashost);
        Map<String, Object> map = new HashMap<>();
        map.put("driverId", transnovasdriverid);
        map.put("vin", "");
        map.put("beginTime", "");
        map.put("endTime", "");
        map.put("pageIndex", "");
        String result = service.driver_event(map, transnovascookie);
        System.out.println("事件类型查询结果： " + result);
    }

    @Parameters({"transnovashost", "transnovasdriverid", "transnovascookie"})
    @Test(dependsOnMethods = "events")
    public static void speed(String transnovashost, String transnovasvehicleId, String transnovascookie) {
        IRemoteService service = Feign.builder()
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(IRemoteService.class, transnovashost);
        String result = service.driver_speedlog(transnovasvehicleId, transnovascookie);
        System.out.println("个人历史速度统计： " + result);
    }
}
