package com.transnova.demo.testng;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tian on 2018.12.17.
 */
public class DriverID {
    @Parameters({"transnovashost", "transnovascookie"})
    @Test()
    public void driverid(String transnovashost,String transnovascookie){
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
        JSONObject json = JSONObject.fromObject(myJson.get("data").toString());
        JSONArray results = json.getJSONArray("result");
        for (int i=0;i<1;i++){
            String driverId=results.getJSONObject(i).get("id").toString();
            System.out.println(driverId);
            try {
                Assert.assertEquals(driverId,"100007");
                System.out.println("数值正确！");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
