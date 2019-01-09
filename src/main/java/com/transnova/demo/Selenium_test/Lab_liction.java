package com.transnova.demo.Selenium_test;

import com.csvreader.CsvReader;
import io.qameta.allure.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Lab_liction {
    @Epic("模拟程序")
    @Feature("路线列表")
    @Stories(value = {@Story(value = "list")})
    @Step(value = "创建路线")
    @Test
    public void route() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\64960\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        //用来保存数据
        ArrayList<String[]> csvFileList = new ArrayList<String[]>();
        //创建CSV读对象
        CsvReader reader = new CsvReader("./tools/CSV/2.csv", ',', Charset.forName("GB18030"));
        //跳过表头
        reader.readHeaders();
        //逐行读入除表头的数据
        while (reader.readRecord()) {
            csvFileList.add(reader.getValues());
        }

        for (int i = 0; i < csvFileList.size(); i++) {
//            Random rand = new Random();
//            int j = rand.nextInt(csvFileList.size());
            String route = new String(csvFileList.get(i)[0].getBytes("utf-8"));
            String qi = new String(csvFileList.get(i)[1].getBytes("UTF-8"));
            String zhong = new String(csvFileList.get(i)[2].getBytes("UTF-8"));
            String url1 = "http://192.168.0.103:7080/transnova-data-generator/route/edit?id=";
            long time = System.currentTimeMillis();
            String url = url1 + time;
            driver.get(url);
            Thread.sleep(1000);

            try {
                //名称、速度阈值
                driver.findElement(By.xpath("//input[@id='name']")).sendKeys(route);
                driver.findElement(By.xpath("//input[@id='minSpeed']")).sendKeys("10");
                driver.findElement(By.xpath("//input[@id='maxSpeed']")).sendKeys("100");

                //路线规划
                jsExecutor.executeScript("var saddress = '" + qi + "', eaddress = '" + zhong + "';$('#route-duration').html('-');\n" +
                        "            $('#route-distance').html('-');\n" +
                        "\n" +
                        "            map.clearOverlays();\n" +
                        "            route.points = [];\n" +
                        "            route.stayPoints = [];\n" +
                        "            route.troubleSegments = [];\n" +
                        "\n" +
                        "            var transit = new BMap.DrivingRoute(map, {\n" +
                        "                renderOptions: {\n" +
                        "                    map: map,\n" +
                        "                    enableDragging : true\n" +
                        "                },\n" +
                        "                onSearchComplete : function(result)\n" +
                        "                {\n" +
                        "                    if (result.getNumPlans() == 0) return;\n" +
                        "                    var plan = result.getPlan(0);\n" +
                        "                    var duration = plan.getDuration();\n" +
                        "                    var distance = plan.getDistance();\n" +
                        "                    $('#route-duration').html(duration);\n" +
                        "                    $('#route-distance').html(distance);\n" +
                        "\n" +
                        "                    route.mileages = plan.getDistance(false);\n" +
                        "                },\n" +
                        "                onPolylinesSet : function(routes)\n" +
                        "                {\n" +
                        "                    var points = [];\n" +
                        "                    for (var i = 0; i < routes.length; i++)\n" +
                        "                    {\n" +
                        "                        var line = routes[i].getPolyline();\n" +
                        "                        line.setStrokeColor('#ffffff');\n" +
                        "                        points = points.concat(line.getPath());\n" +
                        "                    }\n" +
                        "                    if (points.length == 0) return alert('无法完成从 ' + saddress + ' 到 ' + eaddress + ' 的线路规划！');\n" +
                        "\n" +
                        "                    // 点集合\n" +
                        "                    for (var i = 0; i < points.length; i++)\n" +
                        "                    {\n" +
                        "                        points[i].index = i;\n" +
                        "                    }\n" +
                        "\n" +
                        "                    route.points = points;\n" +
                        "                    route.stayPoints = [];\n" +
                        "                    route.troubleSegments = [];\n" +
                        "                    route.__generate_stay_point_rows();\n" +
                        "                    route.__generate_segment_rows();\n" +
                        "                    isMarkMode = false;\n" +
                        "                }\n" +
                        "            });\n" +
                        "            transit.search(saddress, eaddress);");
                Thread.sleep(1000);
                //点击保存
                driver.findElement(By.xpath("//*[@id='btn-save']")).click();
                Alert alert = driver.switchTo().alert();
                alert.accept();
                Thread.sleep(500);
                alert.accept();
                Thread.sleep(500);
                alert.accept();
            } catch (Error e) {
                e.printStackTrace();
            }
            System.out.print("创建第 " + i + " 条： " + qi + "--" + zhong + " 的路线");
        }
        driver.close();
        WindowsUtils.killByName("chromedriver.exe");
    }
}
