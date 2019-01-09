package com.transnova.demo.baidu;

import com.transnova.demo.IRemoteService;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class loadapi {
    @Test
    public void load(){
        BAIDUService service= Feign.builder().options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(BAIDUService.class, "http://api.map.baidu.com");
        Map<String,Object> map = new HashMap<>();
        map.put("road_name","东二环");
        map.put("city","北京");
        map.put("ak","KEZHd5GnIVtNyOkHd5iMV9G3pjaZt9Vy");
        String result=service.road(map);
        System.out.println(result);
    }
}
