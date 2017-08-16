package sample.model;


import sample.model.DAO.U2000_DB;
import sample.model.DTO.ExcelWritter;
import sample.model.DTO.Result_Avail2G;
import sample.model.DTO.Result_Avail3G;

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




        // Create Database
        U2000_DB u2000_16 = new U2000_DB("10.76.2.16", "4100", "sa", "Sunews#k58");
        U2000_DB u2000_26 = new U2000_DB("10.76.2.26", "4100", "sa", "Sunews#k58");
        U2000_DB u2000_87 = new U2000_DB("10.74.159.87", "4100", "sa", "Changeme_123");
        U2000_DB u2000_88 = new U2000_DB("10.74.159.88", "4100", "sa", "Changeme_123");



        // Query 2G
        ArrayList<Result_Avail2G> avail_2G = new ArrayList<>();
        avail_2G.addAll(u2000_16.query2GAvail());
        avail_2G.addAll(u2000_26.query2GAvail());
        avail_2G.addAll(u2000_87.query2GAvail());
        avail_2G.addAll(u2000_88.query2GAvail());


        // Query 3G
        ArrayList<Result_Avail3G> avail_3G = new ArrayList<>();
        avail_3G.addAll(u2000_16.query3GAvail());
        avail_3G.addAll(u2000_26.query3GAvail());
        avail_3G.addAll(u2000_87.query3GAvail());
        avail_3G.addAll(u2000_88.query3GAvail());

        // Excel Writter
        ExcelWritter excelWritter = new ExcelWritter();

        // Write 2G
        excelWritter.write2GAvailData(avail_2G, new File("C:\\Users\\m80028770\\2G_Avail.xlsx"));
        // Write 3G
        excelWritter.write3GAvailData(avail_3G, new File("C:\\Users\\m80028770\\3G_Avail.xlsx"));


*/

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse("2017-05-15 03:00:00",formatter);
        LocalDateTime end = LocalDateTime.parse("2017-08-16 00:00:00",formatter);



        U2000_DB u2000_db = new U2000_DB("10.76.2.21", "4100", "sa", "Sunews#k58");

        u2000_db.getAlarmLogTableList(start,end);


    }


}
