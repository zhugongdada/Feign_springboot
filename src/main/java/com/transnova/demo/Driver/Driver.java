package com.transnova.demo.Driver;


import com.transnova.demo.IRemoteService;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tian on 2018.12.14.
 */
//@Component
@Configuration
public class Driver {//坑一：注入参数得加@Configuration注解； 坑二：对于参数，还得增加方法名

        private static String transnovascookie;
        private static String transnovashost;
        private static String transnovasdriverid;
        private static String transnovasvehicleId;

        @Value("${transnovas.cookie}")
        public void settransnovascookie (String cookie){
            transnovascookie = cookie;
        }

        @Value("${transnovas.host}")
        public void settransnovashost (String host){
            transnovashost = host;
        }

        @Value("${transnovas.driverid}")
        public void setTransnovasdriverid (String driverid){
            transnovasdriverid = driverid;
        }

        @Value("${transnovas.vehicleId}")
        public void setTransnovasvehicleId (String vehicleId){
            transnovasvehicleId = vehicleId;
        }

    public static void driver() {
        IRemoteService service = Feign.builder()
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(IRemoteService.class, transnovashost);
        String result = service.driver_Index(transnovasdriverid, transnovascookie);
        System.out.println("评分指数查询结果： " + result);

    }

    public static void events() {
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

    public static void speed() {
        IRemoteService service = Feign.builder()
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(IRemoteService.class, transnovashost);
        String result = service.driver_speedlog(transnovasvehicleId, transnovascookie);
        System.out.println("个人历史速度统计： " + result);
    }
}
