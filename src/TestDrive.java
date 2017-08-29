import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.model.AlarmUtil;
import sample.model.DTO.Alarm.Alarm;
import sample.model.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by m80028770 on 8/16/2017.
 */
public class TestDrive {

    private static final Logger logger = LogManager.getLogger(TestDrive.class);



    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse("2017-08-17 01:00:00", formatter);
        LocalDateTime end = LocalDateTime.parse("2017-08-17 00:00:00", formatter);

        Alarm a1 = new Alarm();
        a1.setAlarmName("NodeB Unavailable");
        a1.setMoName("NodeB Name=C2_0_ALX3698P3(MTR_AlmElroom)");
        a1.setOccurTime("2017-08-15 03:37:23");
        a1.setClearTime("2017-08-18 11:01:17");

        logger.debug(a1);

        AlarmUtil alarmUtil = new AlarmUtil();
        alarmUtil.setMinOccurTime("2017-08-16 00:00:00");
        alarmUtil.setMaxClearTime("2017-08-16 23:59:59");

        alarmUtil.trimOccurTime(a1);
        alarmUtil.trimClearTime(a1);

        logger.error(a1);



    }


    public static void main2(String[] args) {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS");
//        LocalDateTime expireDate = LocalDateTime.parse("2017-08-15 01:03:45",formatter);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime date1 = LocalDateTime.parse("2017-08-15 13:41:21",formatter);
//        LocalDateTime date2 = LocalDateTime.parse("2017-08-15 19:38:12",formatter);
//
//        System.out.println("date1: " + ( date2.toEpochSecond(ZoneOffset.UTC) - date1.toEpochSecond(ZoneOffset.UTC) ));
//        System.out.println("date1: " + Util.subDate(date1,date2 ));


        Alarm a1 = new Alarm();
        a1.setAlarmName("NodeB Unavailable");
        a1.setMoName("NodeB Name=C2_0_ALX3698P3(MTR_AlmElroom)");
        a1.setOccurTime("2017-08-15 11:01:00");
        a1.setClearTime("2017-08-15 11:01:17");


        Alarm a2 = new Alarm();
        a2.setAlarmName("NodeB Unavailable");
        a2.setMoName("NodeB Name=C2_0_ALX3698P3(MTR_AlmElroom)");
        a2.setOccurTime("2017-08-15 11:01:17");
        a2.setClearTime("2017-08-15 13:24:57");

        Alarm a3 = new Alarm();
        a3.setAlarmName("NodeB Unavailable");
        a3.setMoName("NodeB Name=C2_0_ALX3698P3(MTR_AlmElroom)");
        a3.setOccurTime("2017-08-15 13:24:57");
        a3.setClearTime("2017-08-15 13:24:58");

        Alarm a4 = new Alarm();
        a4.setAlarmName("NodeB Unavailable");
        a4.setMoName("NodeB Name=C2_0_ALX3698P3(MTR_AlmElroom)");
        a4.setOccurTime("2017-08-15 13:24:57");
        a4.setClearTime("2017-08-15 13:24:58");

        Alarm a5 = new Alarm();
        a5.setAlarmName("NodeB Unavailable");
        a5.setMoName("NodeB Name=C2_0_ALX3698P3(MTR_AlmElroom)");
        a5.setOccurTime("2017-08-15 13:25:07");
        a5.setClearTime("2017-08-15 13:41:21");

        Alarm a6 = new Alarm();
        a6.setAlarmName("NodeB Unavailable");
        a6.setMoName("NodeB Name=C2_0_ALX3698P3(MTR_AlmElroom)");
        a6.setOccurTime("2017-08-15 19:38:12");
        a6.setClearTime("2017-08-15 19:38:30");

        Alarm a7 = new Alarm();
        a7.setAlarmName("NodeB Unavailable");
        a7.setMoName("NodeB Name=C2_0_ALX3698P3(MTR_AlmElroom)");
        a7.setOccurTime("2017-08-15 19:38:30");
        a7.setClearTime("2017-08-15 19:44:03");

        Alarm a8 = new Alarm();
        a8.setAlarmName("NodeB Unavailable");
        a8.setMoName("NodeB Name=C2_0_ALX3698P3(MTR_AlmElroom)");
        a8.setOccurTime("2017-08-15 19:44:13");
        a8.setClearTime("2017-08-15 19:55:21");

        Alarm a9 = new Alarm();
        a9.setAlarmName("NodeB Unavailable");
        a9.setMoName("NodeB Name=C2_0_ALX3698P3(MTR_AlmElroom)");
        a9.setOccurTime("2017-08-15 19:44:13");
        a9.setClearTime("2017-08-15 20:00:21");


        // System.out.println("a1: " + a1);
        // System.out.println("a2: " + a2);

        System.out.println("a1 duration: " + Util.subDate(a1.getOccurTime(), a1.getClearTime()));

        ArrayList<Alarm> alarmList = new ArrayList<>();
        alarmList.add(a1);
        alarmList.add(a2);
        alarmList.add(a3);
        alarmList.add(a4);
        alarmList.add(a5);
        alarmList.add(a6);
        alarmList.add(a7);
        alarmList.add(a8);
        alarmList.add(a9);



        System.out.println("----------------- Before ------------------");

        for(Alarm alarm:alarmList) {
            System.out.println("Alarm: " + alarm);
        }


//        }

        //Collections.sort(alarmList);

        AlarmUtil alarmUtil = new AlarmUtil();


        //System.out.println("result: " + alarmUtil.mergeTwoAlarms(a1, a2));

        alarmList = alarmUtil.optimize(alarmList);


        System.out.println("----------------- After ------------------");


        for(Alarm alarm:alarmList) {
            System.out.println("Alarm: " + alarm);
        }


    }
}
