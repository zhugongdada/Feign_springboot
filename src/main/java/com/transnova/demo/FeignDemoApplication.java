package com.transnova.demo;

import com.transnova.demo.Driver.Driver;
import com.transnova.demo.testng.DriverList;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
/* *
* Feign通过将注释处理为模板化请求来工作
 * @RequestLine  定义HttpMethod和UriTemplatefor请求。 Expressions，{expression}使用相应的带@Param注释的参数来解析用大括号包装的值。
 * @Param  定义模板变量，其值将用于Expression按名称解析相应的模板
 * @Headers  定义HeaderTemplate; 一个变种UriTemplate。使用带@Param注释的值来解析相应的Expressions。在a上使用时Type，模板将应用于每个请求。在a上使用时Method，模板仅适用于带注释的方法
 * @QueryMap 定义一个Map名称 - 值对或POJO，以扩展为查询字符串。
 * @HeaderMap  定义一个Map名称 - 值对，以扩展为Http Headers
 * @Body  定义a Template，类似于a UriTemplate和HeaderTemplate，使用带@Param注释的值来解析相应的Expressions。
* */

@SpringBootApplication
public class FeignDemoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FeignDemoApplication.class, args);
//        Driver.driver();
//        Driver.events();
//        Driver.speed();
//        DriverList.ListIndex();
    }

}
