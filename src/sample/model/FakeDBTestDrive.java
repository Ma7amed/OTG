package sample.model;

import sample.model.DAO.FakeU2000_DB;
import sample.model.DAO.U2000_DB;
import sample.model.DTO.Alarm.Alarm;
import sample.model.DTO.DailyReport.Daily3G;
import sample.model.DTO.Outage.AlarmSummary;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

// Import log4j classes.
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import sample.model.DTO.Avail.Result_Avail3G;
import sample.model.DTO.Outage.OutageRecord_3G;
import sample.model.DTO.Outage.OutageSummary_3G;

/**
 * Created by m80028770 on 8/18/2017.
 */
public class FakeDBTestDrive {

    private static final Logger logger = LogManager.getRootLogger();


    public static void main2(String[] args) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse("2017-10-23 00:00:00", formatter);
        LocalDateTime end = LocalDateTime.parse("2017-08-25 00:00:00", formatter);


        FakeU2000_DB u2000_db = new FakeU2000_DB();


        ArrayList<Alarm> result = new ArrayList<>();
        result.addAll(u2000_db.query3GAlm(start, end));


        ArrayList<Alarm> resultCorrelated = new ArrayList<>();

        AlarmUtil alarmUtil = new AlarmUtil();
        alarmUtil.setMinOccurTime("2017-10-23 00:00:00");
        alarmUtil.setMaxClearTime("2017-10-25 23:59:59");

        resultCorrelated = alarmUtil.optimize(result);

        logger.error("All Alarms Count: " + result.size());
        logger.error("Correlated Alarms count: " + resultCorrelated.size());

        File file = new File("D:\\Work\\OSS\\Temp_Delete\\20170820\\OTG\\result_c.csv");
        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new FileWriter(file));
            for (Alarm alarm : resultCorrelated) {
                br.write(alarm.toCsv() + "\r\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {

                if (br != null)
                    br.close();


            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }


        // Create Database
        U2000_DB u2000_21 = new U2000_DB("10.76.2.21", "4100", "sa", "Sunews#k58");
        U2000_DB u2000_16 = new U2000_DB("10.76.2.16", "4100", "sa", "Sunews#k58");
        U2000_DB u2000_26 = new U2000_DB("10.76.2.26", "4100", "sa", "Sunews#k58");
        U2000_DB u2000_86 = new U2000_DB("10.74.159.86", "4100", "sa", "tC9zTV#GbU@mYD");
        U2000_DB u2000_87 = new U2000_DB("10.74.159.87", "4100", "sa", "tC9zTV#GbU@mYD");
        U2000_DB u2000_88 = new U2000_DB("10.74.159.88", "4100", "sa", "tC9zTV#GbU@mYD");


        // Query 3G

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse("2017-08-19 00:00:00", formatter);

        ArrayList<Result_Avail3G> avail_3G = new ArrayList<>();
        avail_3G.addAll(u2000_16.query3GAvail(date));
        avail_3G.addAll(u2000_26.query3GAvail(date));
        avail_3G.addAll(u2000_87.query3GAvail(date));
        avail_3G.addAll(u2000_88.query3GAvail(date));

        // Excel Writter
        ExcelWritter excelWritter = new ExcelWritter();

        // Write 2G
//        excelWritter.write2GAvailData(avail_2G, new File("C:\\Users\\m80028770\\2G_Avail.xlsx"));
        // Write 3G
        excelWritter.write3GAvailData(avail_3G, new File("D:\\Work\\OSS\\Temp_Delete\\20170820\\OTG\\3G_Avail.xlsx"));


//        ArrayList<OutageRecord_3G> outage_3G = new ArrayList<>();
//
//        for(Alarm alarm:resultCorrelated) {
//
//            String deviceName = alarm.getMoName();
//            String siteName = SiteUtil.deviceName2SiteName(deviceName);
//            String siteID = SiteUtil.siteName2ID(siteName);
//            long downTime = Util.subDate(alarm.getOccurTime(),alarm.getClearTime());
//
//            OutageRecord_3G outageRecord_3G = new OutageRecord_3G();
//            outageRecord_3G.setAlarmOccurrenceTime(alarm.getOccurTime());
//            outageRecord_3G.setFaultOccurrenceTime(alarm.getOccurTime());
//            outageRecord_3G.setFaultClearanceTime(alarm.getClearTime());
//            outageRecord_3G.setSiteName(deviceName);
//            outageRecord_3G.setSiteCode(siteName);
//            outageRecord_3G.setSiteId(siteID);
//            outageRecord_3G.setDownTime(downTime);
//            outageRecord_3G.setMttr(downTime);
//
//            logger.error(outageRecord_3G);

//        }


    }

    public static void main(String[] args) {


        String otgPath = "D:\\Work\\OSS\\Temp_Delete\\20171104\\OTG";

        // ALARMS

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse("2017-10-23 00:00:00", formatter);
        LocalDateTime end = LocalDateTime.parse("2017-10-25 00:00:00", formatter);


        FakeU2000_DB u2000_sls = new FakeU2000_DB();
        u2000_sls.setAlmFileLocation(otgPath + "\\U2000_SLS.csv");


        FakeU2000_DB u2000_atae = new FakeU2000_DB();
        u2000_atae.setAlmFileLocation(otgPath + "\\U2000_ATAE.csv");


        ArrayList<Alarm> result = new ArrayList<>();
        result.addAll(u2000_sls.query3GAlm(start, end));
        result.addAll(u2000_atae.query3GAlm(start, end));


//        List<String> alarmData = result.parallelStream()
//                .filter(alarm -> alarm.getOriginalAlarmCount()==3)
//                .sorted(Comparator.comparing(Alarm::getIdentifier).reversed())
//                .map(Alarm::getMoName)
//                .collect(Collectors.toList());
//
//        for(String s:alarmData) {
//            System.out.println(s);
//        }

//        HashMap<String, AlarmSummary> alarmSummaryMap = new HashMap<>();
//
//        Collections.sort(result);
//
//        String lastIdentifier = "";
//        AlarmSummary alarmSummary = new AlarmSummary();
//
//        for (Alarm alarm : result) {
//
//            String identifier = alarm.getIdentifier();
//
//            if (lastIdentifier.equals(identifier)) {
//                // add data to the current one
//
//            } else {
//                // reset values for a new one and add old one to map
//                alarmSummary = new AlarmSummary();
//            }
//
//            lastIdentifier = identifier;
//        }


//         COR

        ArrayList<Alarm> resultCorrelated = new ArrayList<>();

        AlarmUtil alarmUtil = new AlarmUtil();
        alarmUtil.setMinOccurTime("2017-10-25 00:00:00");
        alarmUtil.setMaxClearTime("2017-10-25 23:59:59");

        resultCorrelated = alarmUtil.optimize(result);

        logger.error("All Alarms Count: " + result.size());
        logger.error("Correlated Alarms count: " + resultCorrelated.size());


        // need to have list of alarms, to search by identifier ,,, get the original count//correlated count//total down time

        HashMap<String, AlarmSummary> alarmSummaryMap = new HashMap<>();

        for (Alarm alarm : resultCorrelated) {

            String currentIdentifier = alarm.getIdentifier();

            if (alarmSummaryMap.containsKey(currentIdentifier)) {

                // TODO: if record exist,,, add it to the current exist one and update it
                // Will update DownTime & Original Count & Correlated Count

                logger.debug("Updating currently created record");
                AlarmSummary oldAlarmSummary = alarmSummaryMap.get(currentIdentifier);
                oldAlarmSummary.setCorrelatedAlarmCount(oldAlarmSummary.getCorrelatedAlarmCount() + 1);
                oldAlarmSummary.setOriginalAlarmCount(oldAlarmSummary.getOriginalAlarmCount() + alarm.getCount());
                oldAlarmSummary.setDownTime(oldAlarmSummary.getDownTime() + alarm.getDownTime());

                // alarmSummaryMap.replace(currentIdentifier,oldAlarmSummary);

            } else {


                // TODO: if not exist, add as new one
                logger.debug("Creating new record");
                //    public AlarmSummary(String identifier, String moName, int downTime, int originalAlarmCount, int correlatedAlarmCount) {

                alarmSummaryMap.put(alarm.getIdentifier(), new AlarmSummary(currentIdentifier, alarm.getMoName(), alarm.getDownTime(), alarm.getCount(), 1));


            }

        }

        logger.error("Map successfully created ... " + alarmSummaryMap.size() + " records");

//        Iterator i = alarmSummaryMap.entrySet().iterator();
//        while (i.hasNext()) {
//            Map.Entry pair = (Map.Entry) i.next();
//            System.out.println(pair.getKey() + "," + pair.getValue());
//        }


//         AVAILABILITY

//
        // Create Database
        U2000_DB u2000_21 = new U2000_DB("10.76.2.21", "4100", "sa", "Sunews#k58");
        U2000_DB u2000_16 = new U2000_DB("10.76.2.16", "4100", "sa", "Sunews#k58");
        U2000_DB u2000_26 = new U2000_DB("10.76.2.26", "4100", "sa", "Sunews#k58");
        U2000_DB u2000_86 = new U2000_DB("10.74.159.86", "4100", "sa", "tC9zTV#GbU@mYD");
        U2000_DB u2000_87 = new U2000_DB("10.74.159.87", "4100", "sa", "tC9zTV#GbU@mYD");
        U2000_DB u2000_88 = new U2000_DB("10.74.159.88", "4100", "sa", "tC9zTV#GbU@mYD");

        LocalDateTime date = LocalDateTime.parse("2017-10-25 00:00:00", formatter);

        ArrayList<Result_Avail3G> avail_3G = new ArrayList<>();
        avail_3G.addAll(u2000_16.query3GAvail(date));
        avail_3G.addAll(u2000_26.query3GAvail(date));
        avail_3G.addAll(u2000_87.query3GAvail(date));
        avail_3G.addAll(u2000_88.query3GAvail(date));


        ExcelWritter excelWritter = new ExcelWritter();
        excelWritter.writeAlmData(result, new File(otgPath + "\\U2000_ALL.xlsx"));
        excelWritter.writeAlmData(resultCorrelated, new File(otgPath + "\\U2000_Correlated.xlsx"));


        excelWritter.write3GAvailData(avail_3G, new File(otgPath + "\\U2000_AVAIL.xlsx"));
//


        // Read Daily Report

        ArrayList<Daily3G> daily3G_list = new ArrayList<>();

        ExcelReader excelReader = new ExcelReader();
        daily3G_list = excelReader.readDailyReport_3G(new File(otgPath + "\\DailyReport.xlsx"));
        System.out.println("Daily 3G have: " + daily3G_list.size() + " sites");

        // Generate Report Summary

        HashMap<String,String> daily3GMap = new HashMap<>();
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
            daily3GMap.put(site_name,site_name);
        }

        System.out.println("daily 3g MAP have :" + daily3GMap.size() + " sites");

        ArrayList<OutageSummary_3G> outageSummary = new ArrayList<>();

        for (Result_Avail3G avail : avail_3G) {

            if (avail.getUnAvailTime() < 600) {
                continue; // ignore less than 10 minutes
            }

            // Ignore sites not in daily

            if(!daily3GMap.containsKey(avail.getSite())) {
                System.out.println("Ignoring site ... " + avail.getSite());
                continue;
            }



            OutageSummary_3G summary = new OutageSummary_3G();
            summary.setStartTime(avail.getStartTime());
            summary.setPeriod(avail.getPeriod());
            summary.setNeName(avail.getNeName());
            summary.setSite(avail.getSite());
            summary.setUnAvailTime(avail.getUnAvailTime());
            String key = "NodeB Unavailable" + "_NodeB Name=" + avail.getSite();
            logger.debug("Key: " + key);

            if (alarmSummaryMap.containsKey(key)) {
                //
                AlarmSummary currentSummary = alarmSummaryMap.get(key);
                summary.setOriginalAlarmCount(currentSummary.getOriginalAlarmCount());
                summary.setCorrelatedAlarmCount(currentSummary.getCorrelatedAlarmCount());
                summary.setTotalDownTime(currentSummary.getDownTime());
            }

            outageSummary.add(summary);
        }


        logger.error("Successfully build alarm summary ... " + outageSummary.size() + " records");

//        for(OutageSummary_3G o:outageSummary) {
//            logger.debug(o);
//        }

        excelWritter.writeOutageSummaryData(outageSummary, new File(otgPath + "\\Outage_Summary.xlsx"));


    }


}
