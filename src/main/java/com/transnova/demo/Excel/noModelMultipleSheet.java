package com.transnova.demo.Excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.testng.annotations.Test;

import java.io.*;
import java.util.List;

import static com.helger.commons.io.resource.ClassPathResource.getInputStream;

public class noModelMultipleSheet {
    public void noModelMultipleSheet() {
        InputStream inputStream = getInputStream("2007NoModelMultipleSheet.xlsx");
        try {
            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, new AnalysisEventListener<List<String>>() {
                @Override
                public void invoke(List<String> object, AnalysisContext context) {
                    System.out.println(
                            "当前sheet:" + context.getCurrentSheet().getSheetNo() + " 当前行：" + context.getCurrentRowNum() + " data:" + object);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                }
            });
            reader.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test1() throws FileNotFoundException {
        OutputStream out = new FileOutputStream("/Users/jipengfei/78.xlsx");
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
//写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0, ExcelPropertyIndexModel.class);
            writer.write(getData(), sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
