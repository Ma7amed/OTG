import sample.model.DAO.U2000_DB;
import sample.model.DTO.Alarm;
import sample.model.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by m80028770 on 8/16/2017.
 */
public class TestDrive {


    public static void main(String[] args) {

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS");
//        LocalDateTime expireDate = LocalDateTime.parse("2017-08-15 01:03:45",formatter);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime date1 = LocalDateTime.parse("2017-08-15 13:41:21",formatter);
//        LocalDateTime date2 = LocalDateTime.parse("2017-08-15 19:38:12",formatter);
//
//        System.out.println("date1: " + ( date2.toEpochSecond(ZoneOffset.UTC) - date1.toEpochSecond(ZoneOffset.UTC) ));
//        System.out.println("date1: " + Util.subDate(date1,date2 ));


        Alarm a1 = new Alarm();
        a1.setAlarmSource("NodeB Name=C2_0_ALX3698P3(MTR_AlmElroom)");
        a1.setOccurTime("2017-08-15 11:01:00","yyyy-MM-dd HH:mm:ss");
        a1.setClearTime("2017-08-15 13:24:58","yyyy-MM-dd HH:mm:ss");




// 1502928000



    }
}
