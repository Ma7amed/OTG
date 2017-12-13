package sample.model;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.model.DTO.DailyReport.Daily2G;
import sample.model.DTO.DailyReport.Daily3G;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by m80028770 on 8/29/2017.
 */
public class ExcelReader {

    public ArrayList<Daily3G> readDailyReport_3G(File file) {

        ArrayList<Daily3G> daily3G_list = new ArrayList<>();


        try {

            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);
            // Sheet datatypeSheet = workbook.getSheetAt(0);
            Sheet datatypeSheet = workbook.getSheet("3G");

            DataFormatter formatter = new DataFormatter();


            for(Row row:datatypeSheet) {

                // skip first row

                if(row.getRowNum()==0) {
                    // ignore
                    continue;
                }

                Daily3G daily3G = new Daily3G();


                int i=0;
                daily3G.setSite_code(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setSite_id(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setRegion(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setBsc_region(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setBsc(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setRnc(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setSite_name(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setOffice(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setM2000(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setLmt(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setCategory(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setSub_category(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setOwner(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setRot_td_sp(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setNo_of_cells(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setNo_of_activated_cells(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setFirst_activation_date(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setDt_confirm(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setComments_of_3g(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setType(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setLocking_date(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setLocking_year(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setCreation_date(formatter.formatCellValue(row.getCell(i++)));
                daily3G.setCreation_year(formatter.formatCellValue(row.getCell(i++)));

                daily3G_list.add(daily3G);

//                System.out.println(daily3G_list.size());

//                System.out.println("OthersTestDrive.main: " + daily3G);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        System.out.println(daily3G_list.size());

        return daily3G_list;
    }

    public ArrayList<Daily2G> readDailyReport_2G(File file) {

        ArrayList<Daily2G> daily2G_list = new ArrayList<>();


        try {

            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);
            // Sheet datatypeSheet = workbook.getSheetAt(0);
            Sheet datatypeSheet = workbook.getSheet("2G");

            DataFormatter formatter = new DataFormatter();


            for(Row row:datatypeSheet) {

                // skip first row

                if(row.getRowNum()==0) {
                    // ignore
                    continue;
                }

                Daily2G daily2G = new Daily2G();


                int i=0;
                daily2G.setSite_code(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setSite_id(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setRegion(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setBsc(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setBsc_region(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setSite_name(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setOffice(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setTechnical_area(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setSite_qism(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setTier(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setM2000_status(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setLmt_status(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setCategory(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setSub_category(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setOwner(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setRot_td_sp_huawei(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setCairo_swapped(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setFirst_activation_date(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setBts_type(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setType_of_sharing(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setHost_guest(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setNo_of_cells(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setNo_of_activated_cells(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setIdu_odu(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setLocking_date(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setDt_confirm(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setTurnkey_global_deal(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setLat(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setLongitude(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setTransfer_date(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setComments_of_2g(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setStatus_3g(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setAddress(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setGovernorate(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setLocking_date(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setLocking_year(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setCreation_date(formatter.formatCellValue(row.getCell(i++)));
                daily2G.setCreation_year(formatter.formatCellValue(row.getCell(i++)));



                daily2G_list.add(daily2G);

//                System.out.println(daily3G_list.size());

//                System.out.println("OthersTestDrive.main: " + daily3G);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        System.out.println(daily3G_list.size());

        return daily2G_list;
    }


}
