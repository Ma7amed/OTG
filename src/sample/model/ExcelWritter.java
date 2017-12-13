package sample.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.model.DTO.Alarm.Alarm;
import sample.model.DTO.Avail.Result_Avail2G;
import sample.model.DTO.Avail.Result_Avail3G;
import sample.model.DTO.Outage.OutageRecord_3G;
import sample.model.DTO.Outage.OutageSummary_3G;

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

        //test

        for (int i = 0; i < data.size(); i++) {
            Row row = sheet1.createRow(i + 1);

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
        XSSFCellStyle headerStyle = getHeaderStyle(wb);
        while (cl < Result_Avail3G.HEADERS.length) {
            Cell cell = header.createCell(cl);
            cell.setCellValue(Result_Avail3G.HEADERS[cl]);
            cell.setCellStyle(headerStyle);
            cl++;
        }

        XSSFCellStyle cellStyle = getCellStyle(wb);


        for (int i = 0; i < data.size(); i++) {
            Row row = sheet1.createRow(i + 1);

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


    public void writeAlmData(ArrayList<Alarm> data, File file) {

        System.out.println("ExcelWritter.writeAlmData ... writing " + data.size() + " records");


        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("Alarms");

        Row header = sheet1.createRow(0);

        // set headers
        int cl = 0;
        XSSFCellStyle headerStyle = getHeaderStyle(wb);
        while (cl < Alarm.HEADERS.length) {
            Cell cell = header.createCell(cl);
            cell.setCellValue(Alarm.HEADERS[cl]);
            cell.setCellStyle(headerStyle);
            cl++;
        }

        XSSFCellStyle cellStyle = getCellStyle(wb);


        for (int i = 0; i < data.size(); i++) {
            Row row = sheet1.createRow(i + 1);

            cl = 0;
            row.createCell(cl++).setCellValue(data.get(i).getLogSerialNumber());
            row.createCell(cl++).setCellValue(data.get(i).getMoName());
            row.createCell(cl++).setCellValue(data.get(i).getAlarmID());
            row.createCell(cl++).setCellValue(data.get(i).getAlarmName());
            row.createCell(cl++).setCellValue(data.get(i).getAcutalOccurTimeString());
            row.createCell(cl++).setCellValue(data.get(i).getOccurTimeString());
            row.createCell(cl++).setCellValue(data.get(i).getClearTimeString());
            row.createCell(cl++).setCellValue(data.get(i).getRemark());
            row.createCell(cl++).setCellValue(data.get(i).getLocationInfo());
            row.createCell(cl++).setCellValue(data.get(i).getCount());


            for (int c = 0; c < row.getLastCellNum(); c++) {
                row.getCell(c).setCellStyle(cellStyle);
            }


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


    public void writeOutageSummaryData(ArrayList<OutageSummary_3G> data, File file) {

        System.out.println("ExcelWritter.writeOutageSummaryData ... writing " + data.size() + " records");


        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("OutageSummary");

        Row header = sheet1.createRow(0);

        // set headers
        int cl = 0;
        XSSFCellStyle headerStyle = getHeaderStyle(wb);
        while (cl < OutageSummary_3G.HEADERS.length) {
            Cell cell = header.createCell(cl);
            cell.setCellValue(OutageSummary_3G.HEADERS[cl]);
            cell.setCellStyle(headerStyle);
            cl++;
        }


        XSSFCellStyle cellStyle = getCellStyle(wb);
        XSSFCellStyle cellStyle_timeRange = getCellStyle_timeRange(wb);
        XSSFCellStyle cellStyle_percent = getCellStyle_percent(wb);


        for (int i = 0; i < data.size(); i++) {
            Row row = sheet1.createRow(i + 1);

            cl = 0;
            row.createCell(cl++).setCellValue(data.get(i).getStartTime());
            row.createCell(cl++).setCellValue(data.get(i).getPeriod());
            row.createCell(cl++).setCellValue(data.get(i).getNeName());
            row.createCell(cl++).setCellValue(data.get(i).getSite());
            row.createCell(cl++).setCellValue(data.get(i).getOriginalAlarmCount());
            row.createCell(cl++).setCellValue(data.get(i).getCorrelatedAlarmCount());
            row.createCell(cl++).setCellValue(data.get(i).getUnAvailTime());
            row.createCell(cl++).setCellValue(data.get(i).getTotalDownTime());
            row.createCell(cl++).setCellValue(data.get(i).getAlarmAvailGap());
            // Time Range
            row.createCell(cl++).setCellValue((float) data.get(i).getUnAvailTime() / 24 / 60 / 60);
            row.createCell(cl++).setCellValue((float) data.get(i).getTotalDownTime() / 24 / 60 / 60);
            row.createCell(cl++).setCellValue(Math.abs((float) data.get(i).getAlarmAvailGap() / 24 / 60 / 60));
            //
            String gapSign = data.get(i).getAlarmAvailGap() > 0 ? "+" : "-";
            gapSign = data.get(i).getAlarmAvailGap() == 0 ? "0" : gapSign;
            row.createCell(cl++).setCellValue(gapSign);
            row.createCell(cl++).setCellValue((float) Math.abs(data.get(i).getAlarmAvailGap())/data.get(i).getUnAvailTime());



//            {"Start Time","Period (min)", "NE Name", "Site", "Unavailable Time","Original Alarm Count",
//                    "Correlated Alarm Count", "Total Down Time","Alarm/Avail Gap"};

            for (int c = 0; c < row.getLastCellNum(); c++) {
                row.getCell(c).setCellStyle(cellStyle);
                if (c >= 9 && c <= 11) {
                    row.getCell(c).setCellStyle(cellStyle_timeRange);
                }

                if( c == 13) {
                    row.getCell(c).setCellStyle(cellStyle_percent);
                }
            }

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

    public void writeOutage3GData(ArrayList<OutageRecord_3G> data, File file) {

        System.out.println("ExcelWritter.writeOutage3GData ... writing " + data.size() + " records");


        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("Outage_3G");

        Row header = sheet1.createRow(0);

        // set headers
        int cl = 0;
        XSSFCellStyle headerStyle = getHeaderStyle(wb);
        while (cl < OutageRecord_3G.HEADERS.length) {
            Cell cell = header.createCell(cl);
            cell.setCellValue(OutageRecord_3G.HEADERS[cl]);
            cell.setCellStyle(headerStyle);
            cl++;
        }

        XSSFCellStyle cellStyle = getCellStyle(wb);


        for (int i = 0; i < data.size(); i++) {
            Row row = sheet1.createRow(i + 1);

            cl = 0;
            row.createCell(cl++).setCellValue(data.get(i).getDateString());
            row.createCell(cl++).setCellValue(data.get(i).getRegion());
            row.createCell(cl++).setCellValue(data.get(i).getRnc());
            row.createCell(cl++).setCellValue(data.get(i).getSiteName());
            row.createCell(cl++).setCellValue(data.get(i).getSiteCode());
            row.createCell(cl++).setCellValue(data.get(i).getSiteId());
            row.createCell(cl++).setCellValue(data.get(i).getSiteCategory());
            row.createCell(cl++).setCellValue(data.get(i).getTechnicalArea());
            row.createCell(cl++).setCellValue(data.get(i).getSiteLayerQism());
            row.createCell(cl++).setCellValue(data.get(i).getAlarmOccurrenceTimeString());
            row.createCell(cl++).setCellValue(data.get(i).getFaultOccurrenceTimeString());
            row.createCell(cl++).setCellValue(data.get(i).getFaultClearanceTimeString());
            row.createCell(cl++).setCellValue(data.get(i).getMttr());
            row.createCell(cl++).setCellValue(data.get(i).getDownTime());
            row.createCell(cl++).setCellValue(data.get(i).getSiteType());
            row.createCell(cl++).setCellValue(data.get(i).getSlaStatus());
            row.createCell(cl++).setCellValue(data.get(i).getReasonCategory());
            row.createCell(cl++).setCellValue(data.get(i).getReasonSubCategory());
            row.createCell(cl++).setCellValue(data.get(i).getComment());
            row.createCell(cl++).setCellValue(data.get(i).getOwner());
            row.createCell(cl++).setCellValue(data.get(i).getAccessType());
            row.createCell(cl++).setCellValue(data.get(i).getBbt());
            row.createCell(cl++).setCellValue(data.get(i).getBbtJustification());
            row.createCell(cl++).setCellValue(data.get(i).getCascadedTo());
            row.createCell(cl++).setCellValue(data.get(i).getDownSiteStatus());
            row.createCell(cl++).setCellValue(data.get(i).getStatus());
            row.createCell(cl++).setCellValue(data.get(i).getTt());





            for (int c = 0; c < row.getLastCellNum(); c++) {
                row.getCell(c).setCellStyle(cellStyle);
            }


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


    private XSSFCellStyle getHeaderStyle(Workbook wb) {

        Font font_white = wb.createFont();
        font_white.setColor(IndexedColors.WHITE.getIndex());
        font_white.setBold(true);


        // Header Style
        XSSFCellStyle cs1 = (XSSFCellStyle) wb.createCellStyle();
        cs1.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 112, 192)));
        cs1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cs1.setFont(font_white);
        cs1.setBottomBorderColor(IndexedColors.WHITE.getIndex());
        cs1.setTopBorderColor(IndexedColors.WHITE.getIndex());
        cs1.setRightBorderColor(IndexedColors.WHITE.getIndex());
        cs1.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        cs1.setBorderBottom(CellStyle.BORDER_THIN);
        cs1.setBorderTop(CellStyle.BORDER_THIN);
        cs1.setBorderRight(CellStyle.BORDER_THIN);
        cs1.setBorderLeft(CellStyle.BORDER_THIN);

        return cs1;
    }

    private XSSFCellStyle getCellStyle(Workbook wb) {


        // Cell Style
        XSSFCellStyle cs2 = (XSSFCellStyle) wb.createCellStyle();
        cs2.setFillForegroundColor(new XSSFColor(new java.awt.Color(221, 235, 247)));
        cs2.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //cs2.setFont(font);
        cs2.setBottomBorderColor(IndexedColors.WHITE.getIndex());
        cs2.setTopBorderColor(IndexedColors.WHITE.getIndex());
        cs2.setRightBorderColor(IndexedColors.WHITE.getIndex());
        cs2.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);

//        CreationHelper createHelper = wb.getCreationHelper();
//        cs2.setDataFormat(createHelper.createDataFormat().getFormat("[h]:mm:ss"));

        return cs2;

    }

    private XSSFCellStyle getCellStyle_timeRange(Workbook wb) {


        XSSFCellStyle cs2 = getCellStyle(wb);

        CreationHelper createHelper = wb.getCreationHelper();
        cs2.setDataFormat(createHelper.createDataFormat().getFormat("[h]:mm:ss"));

        return cs2;

    }

    private XSSFCellStyle getCellStyle_percent(Workbook wb) {


        XSSFCellStyle cs2 = getCellStyle(wb);

        CreationHelper createHelper = wb.getCreationHelper();
        cs2.setDataFormat(createHelper.createDataFormat().getFormat("0%"));

        return cs2;

    }

}
