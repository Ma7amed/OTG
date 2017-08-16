package sample.model.DTO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by m80028770 on 8/7/2017.
 */
public class ExcelWritter {

    public void write2GAvailData(ArrayList<Result_Avail2G> data, File file) {

        System.out.println("ExcelWritter.write2GAvailData ... writing " + data.size() + " records");


        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("Avail_2G");

        Row header = sheet1.createRow(0);

        // set headers
        int cl = 0;
        while (cl < Result_Avail2G.HEADERS.length) {
            header.createCell(cl).setCellValue(Result_Avail2G.HEADERS[cl]);
            cl++;
        }

        for(int i = 0;i<data.size();i++) {
            Row row = sheet1.createRow(i+1);

            cl = 0;
            row.createCell(cl++).setCellValue(data.get(i).getStartTime());
            row.createCell(cl++).setCellValue(data.get(i).getPeriod());
            row.createCell(cl++).setCellValue(data.get(i).getNeName());
            row.createCell(cl++).setCellValue(data.get(i).getSite());
            row.createCell(cl++).setCellValue(data.get(i).getInServiceDuration());
            row.createCell(cl++).setCellValue(data.get(i).getOutServiceDuration());



        }


        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void write3GAvailData(ArrayList<Result_Avail3G> data, File file) {

        System.out.println("ExcelWritter.write3GAvailData ... writing " + data.size() + " records");


        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("Avail_3G");

        Row header = sheet1.createRow(0);

        // set headers
        int cl = 0;
        while (cl < Result_Avail3G.HEADERS.length) {
            header.createCell(cl).setCellValue(Result_Avail3G.HEADERS[cl]);
            cl++;
        }

        for(int i = 0;i<data.size();i++) {
            Row row = sheet1.createRow(i+1);

            cl = 0;
            row.createCell(cl++).setCellValue(data.get(i).getStartTime());
            row.createCell(cl++).setCellValue(data.get(i).getPeriod());
            row.createCell(cl++).setCellValue(data.get(i).getNeName());
            row.createCell(cl++).setCellValue(data.get(i).getSite());
            row.createCell(cl++).setCellValue(data.get(i).getUnAvailTime());



        }


        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
