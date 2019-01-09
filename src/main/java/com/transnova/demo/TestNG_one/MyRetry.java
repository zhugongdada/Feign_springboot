package com.transnova.demo.TestNG_one;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
/**
 * 运行失败进行重试
 * **/
public class MyRetry implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}


