package sample.model;


import sample.model.DAO.U2000_DB;
import sample.model.DTO.Alarm.Alarm;
import sample.model.DTO.Avail.Result_Avail2G;
import sample.model.DTO.Avail.Result_Avail3G;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by m80028770 on 8/6/2017.
 */
public class DBTestDrive {

    public static void main(String[] args) {
       /*
        Sybase_DB sybase_db = new Sybase_DB("10.76.2.55","4100","sa","emsems");
        sybase_db.printVersion();

        Sybase_DB sybase_db = new Sybase_DB("10.76.2.21","4100","sa","Sunews#k58");
        sybase_db.printVersion();

        Sybase_DB sybase_db = new Sybase_DB("10.76.2.16","4100","sa","Sunews#k58");
        sybase_db.printVersion();

        Sybase_DB sybase_db = new Sybase_DB("10.76.2.26","4100","sa","Sunews#k58");
        sybase_db.printVersion();


        Sybase_DB sybase_db = new Sybase_DB("10.74.159.86","4100","sa","Changeme_123");
        sybase_db.printVersion();


*/



        // Create Database
        U2000_DB u2000_21 = new U2000_DB("10.76.2.21", "4100", "sa", "Sunews#k58");
        U2000_DB u2000_16 = new U2000_DB("10.76.2.16", "4100", "sa", "Sunews#k58");
        U2000_DB u2000_26 = new U2000_DB("10.76.2.26", "4100", "sa", "Sunews#k58");
        U2000_DB u2000_86 = new U2000_DB("10.74.159.86", "4100", "sa", "Changeme_123");
        U2000_DB u2000_87 = new U2000_DB("10.74.159.87", "4100", "sa", "Changeme_123");
        U2000_DB u2000_88 = new U2000_DB("10.74.159.88", "4100", "sa", "Changeme_123");



//        // Query 2G
//        ArrayList<Result_Avail2G> avail_2G = new ArrayList<>();
//        avail_2G.addAll(u2000_16.query2GAvail());
//        avail_2G.addAll(u2000_26.query2GAvail());
//        avail_2G.addAll(u2000_87.query2GAvail());
//        avail_2G.addAll(u2000_88.query2GAvail());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse("2017-08-16 00:00:00", formatter);

        // Query 3G
        ArrayList<Result_Avail3G> avail_3G = new ArrayList<>();
       // avail_3G.addAll(u2000_16.query3GAvail(date));
       // avail_3G.addAll(u2000_26.query3GAvail(date));
        avail_3G.addAll(u2000_87.query3GAvail());
        avail_3G.addAll(u2000_88.query3GAvail());

        // Excel Writter
        ExcelWritter excelWritter = new ExcelWritter();

        // Write 2G
//        excelWritter.write2GAvailData(avail_2G, new File("C:\\Users\\m80028770\\2G_Avail.xlsx"));
        // Write 3G
        excelWritter.write3GAvailData(avail_3G, new File("D:\\Work\\OSS\\Temp_Delete\\20170820\\OTG_x2\\3G_Avail.xlsx"));




        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse("2017-08-19 00:00:00", formatter);
        LocalDateTime end = LocalDateTime.parse("2017-08-20 00:00:00", formatter);


        // U2000_DB u2000_db = new U2000_DB("10.74.159.86", "4100", "sa", "Changeme_123");

        ArrayList<Alarm> result = new ArrayList<>();

        ArrayList<Alarm> result_SLS = new ArrayList<>();
        ArrayList<Alarm> result_ATAE = new ArrayList<>();


        result_SLS.addAll(u2000_21.query3GAlm(start, end));
        result_ATAE.addAll(u2000_86.query3GAlm(start, end));

//        result.addAll(result_SLS);
        result.addAll(result_ATAE);

        excelWritter.writeAlmData(result_SLS, new File("D:\\Work\\OSS\\Temp_Delete\\20170820\\OTG_x2\\3G_Alm_sls.xlsx"));
        excelWritter.writeAlmData(result_ATAE, new File("D:\\Work\\OSS\\Temp_Delete\\20170820\\OTG_x2\\3G_Alm_atae.xlsx"));


        excelWritter.writeAlmData(result, new File("D:\\Work\\OSS\\Temp_Delete\\20170820\\OTG_x2\\3G_Alm.xlsx"));


        ArrayList<Alarm> resultCorrelated;

        AlarmUtil alarmUtil = new AlarmUtil();
        alarmUtil.setMinOccurTime("2017-08-19 00:00:00");
        alarmUtil.setMaxClearTime("2017-08-19 23:59:59");

        resultCorrelated = alarmUtil.optimize(result);

        excelWritter.writeAlmData(resultCorrelated, new File("D:\\Work\\OSS\\Temp_Delete\\20170820\\OTG_x2\\3G_Alm_c.xlsx"));


    }


}
