package com.transnova.demo.testng;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tian on 2018.12.17.
 */
@Configuration
public class DriverList {
    private static String transnovascookie;
    private static String transnovashost;

    @Value("${transnovas.cookie}")
    public void settransnovascookie(String cookie) {
        transnovascookie = cookie;
    }

    @Value("${transnovas.host}")
    public void settransnovashost(String host) {
        transnovashost = host;
    }

    public static String ListIndex() throws ParseException, IOException {
        DriverInterface driverservice = Feign.builder()
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(DriverInterface.class, transnovashost);
        Map<String, Object> map = new HashMap<>();
        map.put("driverName", "");
        map.put("companyName", "");
        map.put("pageIndex", "");
        String result = driverservice.driverIndex(map, transnovascookie);
//        System.out.println(result);
        //格式转换
        JSONObject myJson = JSONObject.fromObject(result);
        JSONObject jsondata = JSONObject.fromObject(myJson.get("data"));
        String index = (String) jsondata.get("totalPage");
//        System.out.println(index);
//        JSONObject json = JSONObject.fromObject(myJson.get("data").toString());
//        JSONArray resultnew = json.getJSONArray("result");
        System.out.println(index);
        return index;
    }

    }
