package com.itheima;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Author: LiXianG
 * Date: 2019/11/18 13:23
 */
public class POITest {
    //使用POI读取Excel文件中的数据
    @Test
    public void test1() throws Exception {
        //加载指定文件，创建一个Excel对象（工作簿）
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("e:\\poi.xlsx")));
        //读取Excel文件中第一个Sheet标签页
        XSSFSheet sheet = excel.getSheetAt(0);
        //遍历Sheet标签页，获得每一行数据
        for (Row row : sheet) {
            //遍历行，获得每个单元格对象
            for (Cell cell : row) {
                System.out.println(cell.getStringCellValue());
            }
        }
        //关闭资源
        excel.close();
    }


    @Test
    public void test2() throws Exception {
        //加载指定文件，创建一个Excel对象（工作簿）
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("e:\\poi.xlsx")));
        //读取Excel文件中第一个Sheet标签页
        XSSFSheet sheet = excel.getSheetAt(0);
        //获取当前Sheet标签页的有数据的最后一行的行号 从 0 开始
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);//根据行号，获取每一行
            //获得当前行左后一个单元格的索引
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                XSSFCell cell = row.getCell(j);//根据单元格索引获取每个单元格对象
                System.out.println(cell.getStringCellValue());
            }
            //关闭资源
            excel.close();
        }
    }

    @Test
    public void test3() throws Exception {
        //在内存中创建一个Excel文件
        XSSFWorkbook excel = new XSSFWorkbook();
        //创建一个工作簿对象
        XSSFSheet sheet1 = excel.createSheet("牛逼");
        //在工作表中创建行对象
        XSSFRow title = sheet1.createRow(0);
        //在行中创建单元格对象
        title.createCell(0).setCellValue("姓名");
        title.createCell(1).setCellValue("地址");
        title.createCell(2).setCellValue("年龄");

        XSSFRow dataRow = sheet1.createRow(1);

        dataRow.createCell(0).setCellValue("张三");
        dataRow.createCell(1).setCellValue("北京");
        dataRow.createCell(2).setCellValue("20");

        //创建输出流，通过输出流将内存中的Excel文件写到磁盘
        FileOutputStream fos = new FileOutputStream(new File("e:\\hello.xlsx"));
        excel.write(fos);
        fos.flush();
        excel.close();
        fos.close();
    }
}
