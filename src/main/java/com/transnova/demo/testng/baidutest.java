package com.transnova.demo.testng;

import com.transnova.demo.TestNG_one.MyRetry;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import org.testng.annotations.Test;

public class baidutest {
    @Test(retryAnalyzer = MyRetry.class)
    public void baidutest(){
        DriverInterface driverservice = Feign.builder()
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(DriverInterface.class, "https://baike.baidu.com");
        String result = driverservice.baidu("aladdin");
        System.out.println(result);
    }
}
