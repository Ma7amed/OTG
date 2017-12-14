package sample.model;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.model.DAO.U2000_DB;
import sample.model.DTO.Alarm.Alarm;
import sample.model.DTO.Avail.Result_Avail2G;
import sample.model.DTO.Avail.Result_Avail3G;
import sample.model.DTO.DailyReport.Daily2G;
import sample.model.DTO.DailyReport.Daily3G;
import sample.model.DTO.Outage.AlarmSummary;
import sample.model.DTO.Outage.OutageRecord_3G;
import sample.model.DTO.Outage.OutageSummary_3G;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by m80028770 on 8/6/2017.
 */
public class DBTestDrive {

    private static final Logger logger = LogManager.getLogger(DBTestDrive.class.getName());


    public static void main(String[] args) {

        logger.error("Starting ...");


        // Initializations

        // Output Path
        String otgPath = "D:/Work/OSS/Temp_Delete/20171214/OTG/";

        // Files Names

        String fileName_Avail3G = "3G_Availability.xlsx";
        String fileName_AlarmSLS = "3G_Alarms_U2000_SLS.xlsx";
        String fileName_AlarmATAE = "3G_Alarms_U2000_ATAE.xlsx";
        String fileName_Alarm = "3G_Alarms_All.xlsx";
        String fileName_AlarmCorrelated = "3G_Alarms_Correlated.xlsx";
        String fileName_DailyReport = "DailyReport.xlsx";
        String fileName_OutageSummary = "Outage_Summary.xlsx";
        String fileName_Outage3GData = "Outage3GData.xlsx";



        File tempFile = new File(otgPath);
        if(!tempFile.exists()) {
            logger.debug("Creating output Path");
            tempFile.mkdirs();
        }

        // Dates
        String reportDate = "2017-12-13";

        // Formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Start/End date for alarm query
        LocalDateTime start = LocalDateTime.parse("2017-12-01 00:00:00", formatter);
        LocalDateTime end = LocalDateTime.parse("2017-12-14 00:00:00", formatter);

        // Min/Max Occur Time (For correlating Alarms)
        String minOccurTime = reportDate + " 00:00:00";
        String maxClearTime = reportDate + " 23:59:59";

        // Availability Query Date
        LocalDateTime date = LocalDateTime.parse(reportDate + " 00:00:00", formatter);

        // Excel Writter
        ExcelWritter excelWritter = new ExcelWritter();


        // Create Database
        U2000_DB u2000_21 = new U2000_DB("10.76.2.21", "4100", "sa", "Sunews#k58");
        U2000_DB u2000_16 = new U2000_DB("10.76.2.16", "4100", "sa", "Sunews#k58");
        U2000_DB u2000_26 = new U2000_DB("10.76.2.26", "4100", "sa", "Sunews#k58");
//        U2000_DB u2000_86 = new U2000_DB("10.74.159.86", "4100", "sa", "tC9zTV#GbU@mYD");
//        U2000_DB u2000_87 = new U2000_DB("10.74.159.87", "4100", "sa", "tC9zTV#GbU@mYD");
//        U2000_DB u2000_88 = new U2000_DB("10.74.159.88", "4100", "sa", "tC9zTV#GbU@mYD");


        U2000_DB u2000_86 = new U2000_DB("10.74.159.86", "4100", "sa", "Changeme_123");
        U2000_DB u2000_87 = new U2000_DB("10.74.159.87", "4100", "sa", "Changeme_123");
        U2000_DB u2000_88 = new U2000_DB("10.74.159.88", "4100", "sa", "Changeme_123");


        // Query 3G Availability
        ArrayList<Result_Avail3G> avail_3G = new ArrayList<>();
        avail_3G.addAll(u2000_16.query3GAvail(date));
        avail_3G.addAll(u2000_26.query3GAvail(date));
        avail_3G.addAll(u2000_87.query3GAvail(date));
        avail_3G.addAll(u2000_88.query3GAvail(date));


        // Write 3G Availability
        excelWritter.write3GAvailData(avail_3G, new File(otgPath + fileName_Avail3G));


        // Query 3G Alarms
        ArrayList<Alarm> result = new ArrayList<>();
        ArrayList<Alarm> result_SLS = new ArrayList<>();
        ArrayList<Alarm> result_ATAE = new ArrayList<>();


        result_SLS.addAll(u2000_21.query3GAlm(start, end));
        result_ATAE.addAll(u2000_86.query3GAlm(start, end));
        result.addAll(result_ATAE);
        result.addAll(result_SLS);


        // Write 3G Alarms
        logger.debug("Writting ALM SLS");
        excelWritter.writeAlmData(result_SLS, new File(otgPath + fileName_AlarmSLS));
        logger.debug("Writting ALM ATAE");
        excelWritter.writeAlmData(result_ATAE, new File(otgPath + fileName_AlarmATAE));
        logger.debug("Writting ALM ALL");
        excelWritter.writeAlmData(result, new File(otgPath + fileName_Alarm));


        // Correlate Alarms (Merge alarms based on rules)
        ArrayList<Alarm> resultCorrelated;

        AlarmUtil alarmUtil = new AlarmUtil();
        alarmUtil.setMinOccurTime(minOccurTime);
        alarmUtil.setMaxClearTime(maxClearTime);
        resultCorrelated = alarmUtil.optimize(result);

        // Write Correlated Alarms
        logger.debug("Writting ALM Correlated");
        excelWritter.writeAlmData(resultCorrelated, new File(otgPath + fileName_AlarmCorrelated));




        // need to have list of alarms, to search by identifier ,,, get the original count//correlated count//total down time

        HashMap<String, AlarmSummary> alarmSummaryMap = new HashMap<>();

        for (Alarm alarm : resultCorrelated) {

            String currentIdentifier = alarm.getIdentifier();


            if (alarmSummaryMap.containsKey(currentIdentifier)) {

                // TODO: if record exist,,, add it to the current exist one and update it
                // Will update DownTime & Original Count & Correlated Count

                logger.debug("Updating currently created record ... " + currentIdentifier);
                AlarmSummary oldAlarmSummary = alarmSummaryMap.get(currentIdentifier);
                oldAlarmSummary.setCorrelatedAlarmCount(oldAlarmSummary.getCorrelatedAlarmCount() + 1);
                oldAlarmSummary.setOriginalAlarmCount(oldAlarmSummary.getOriginalAlarmCount() + alarm.getCount());
                oldAlarmSummary.setDownTime(oldAlarmSummary.getDownTime() + alarm.getDownTime());

                // alarmSummaryMap.replace(currentIdentifier,oldAlarmSummary);

            } else {


                // TODO: if not exist, add as new one
                logger.debug("Creating new record ... " + currentIdentifier);
                //    public AlarmSummary(String identifier, String moName, int downTime, int originalAlarmCount, int correlatedAlarmCount) {

                alarmSummaryMap.put(alarm.getIdentifier(), new AlarmSummary(currentIdentifier, alarm.getMoName(), alarm.getDownTime(), alarm.getCount(), 1));


            }

        }

        logger.error("Map successfully created ... " + alarmSummaryMap.size() + " records");


        // Read Daily Report


        // 3G

        ArrayList<Daily3G> daily3G_list = new ArrayList<>();

        ExcelReader excelReader = new ExcelReader();
        daily3G_list = excelReader.readDailyReport_3G(new File(otgPath + fileName_DailyReport));
        System.out.println("Daily 3G have: " + daily3G_list.size() + " sites");

        // 2G

        ArrayList<Daily2G> daily2G_list = new ArrayList<>();

        excelReader = new ExcelReader();
        daily2G_list = excelReader.readDailyReport_2G(new File(otgPath + fileName_DailyReport));
        System.out.println("Daily 2G have: " + daily2G_list.size() + " sites");

        // Daily 2G HashMap

        HashMap<String,Daily2G> daily2GMap = new HashMap<>();
        for(Daily2G daily2G:daily2G_list) {
            String site_name = daily2G.getSite_name();
            String category = daily2G.getCategory();

            if(!category.toLowerCase().replace(" ","").equals("onair")) {
                //no on air ... ignore
                continue;
            }

            if(daily2GMap.containsKey(daily2G.getSite_name())) {
                // Already exist in map
                // ignore
                continue;
            }
//            System.out.println("Adding site to map: " + site_code);
            daily2GMap.put(site_name,daily2G);
        }

        System.out.println("daily 2g MAP have :" + daily2GMap.size() + " sites");

        // Generate Report Summary

        // Daily 3G HashMap

        HashMap<String,Daily3G> daily3GMap = new HashMap<>();
        for(Daily3G daily3G:daily3G_list) {
            String site_name = daily3G.getSite_name();
            String category = daily3G.getCategory();

            if(!category.toLowerCase().replace(" ","").equals("onair")) {
                //no on air ... ignore
                continue;
            }

            if(daily3GMap.containsKey(daily3G.getSite_name())) {
                // Already exist in map
                // ignore
                continue;
            }
//            System.out.println("Adding site to map: " + site_code);
            daily3GMap.put(site_name,daily3G);
        }

        System.out.println("daily 3g MAP have :" + daily3GMap.size() + " sites");

        ArrayList<OutageSummary_3G> outageSummary = new ArrayList<>();

        for (Result_Avail3G avail : avail_3G) {

            if (avail.getUnAvailTime() < 600) {
                continue; // ignore less than 10 minutes
            }

            // Ignore sites not in daily

            if(!daily3GMap.containsKey(avail.getSite())) {
//                System.out.println("Ignoring site ... " + avail.getSite());
                continue;
            }



            OutageSummary_3G summary = new OutageSummary_3G();
            summary.setStartTime(avail.getStartTime());
            summary.setPeriod(avail.getPeriod());
            summary.setNeName(avail.getNeName());
            summary.setSite(avail.getSite());
            summary.setUnAvailTime(avail.getUnAvailTime());
//            String key = "NodeB Unavailable" + "_NodeB Name=" + avail.getSite();
            String key = "NodeB Unavailable" + "_" + avail.getSite();
            logger.debug("Key: " + key);

            if (alarmSummaryMap.containsKey(key)) {
                //
//                logger.debug("Alarm Found");
                AlarmSummary currentSummary = alarmSummaryMap.get(key);
                summary.setOriginalAlarmCount(currentSummary.getOriginalAlarmCount());
                summary.setCorrelatedAlarmCount(currentSummary.getCorrelatedAlarmCount());
                summary.setTotalDownTime(currentSummary.getDownTime());
            }else {
                logger.debug("Alarm Not Found");
            }

            outageSummary.add(summary);
        }


        logger.error("Successfully build alarm summary ... " + outageSummary.size() + " records");

//        for(OutageSummary_3G o:outageSummary) {
//            logger.debug(o);
//        }

        excelWritter.writeOutageSummaryData(outageSummary, new File(otgPath + fileName_OutageSummary));


        // Build Real Outage Report

        ArrayList<OutageRecord_3G> outage3G = new ArrayList<>();


//        outage3G.add(outageRecord_3G);

        for(Alarm alarm:resultCorrelated) {

            // don't add if not in daily
            if(!daily3GMap.containsKey(alarm.getMoName())) {
//                System.out.println("Ignoring site ... " + alarm.getMoName());
                continue;
            }

            Daily3G daily3G = daily3GMap.get(alarm.getMoName());

            OutageRecord_3G outageRecord_3G = new OutageRecord_3G();
            outageRecord_3G.setSiteName(alarm.getMoName());
            outageRecord_3G.setAlarmOccurrenceTime(alarm.getAcutalOccurTime());
            outageRecord_3G.setFaultOccurrenceTime(alarm.getAcutalOccurTime());
            outageRecord_3G.setFaultClearanceTime(alarm.getClearTime());
            outageRecord_3G.setSiteId(daily3G.getSite_id());
            outageRecord_3G.setSiteCode(daily3G.getSite_code());
            outageRecord_3G.setRnc(daily3G.getRnc());
            outageRecord_3G.setRegion(daily3G.getRegion());
            outageRecord_3G.setSiteCategory(daily3G.getSub_category());
            outageRecord_3G.setOwner(daily3G.getOwner());
            outageRecord_3G.setMttr(Util.subDate(alarm.getOccurTime(),alarm.getClearTime())/60);
            outageRecord_3G.setDownTime(Util.subDate(alarm.getOccurTime(),alarm.getClearTime())/60);
            outageRecord_3G.setDate(reportDate);
            if(daily2GMap.containsKey(alarm.getMoName())) {
//                System.out.println("DBTestDrive.main .. one site found on daily 2G" );
                Daily2G daily2G = daily2GMap.get(alarm.getMoName());
                outageRecord_3G.setTechnicalArea(daily2G.getTechnical_area());
                outageRecord_3G.setSiteLayerQism(daily2G.getSite_qism());

            }

            outage3G.add(outageRecord_3G);
        }

        excelWritter.writeOutage3GData(outage3G, new File(otgPath + fileName_Outage3GData));



    }





}
