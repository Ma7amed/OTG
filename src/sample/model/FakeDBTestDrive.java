package sample.model;

import sample.model.DAO.FakeU2000_DB;
import sample.model.DTO.Alarm;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// Import log4j classes.
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by m80028770 on 8/18/2017.
 */
public class FakeDBTestDrive {

    private static final Logger logger = LogManager.getRootLogger();


    public static void main(String[] args) {

        logger.trace("Didn't do it.");


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse("2017-08-15 00:00:00", formatter);
        LocalDateTime end = LocalDateTime.parse("2017-08-17 00:00:00", formatter);


        FakeU2000_DB u2000_db = new FakeU2000_DB();


        ArrayList<Alarm> result = new ArrayList<>();
        result.addAll(u2000_db.query3GAlm(start, end));


        ArrayList<Alarm> resultCorrelated = new ArrayList<>();

        AlarmUtil alarmUtil = new AlarmUtil();
        alarmUtil.setMinOccurTime("2017-08-16 00:00:00");
        alarmUtil.setMaxClearTime("2017-08-16 23:59:59");

        resultCorrelated = alarmUtil.optimize(result);

        logger.error("All Alarms Count: " + result.size());
        logger.error("Correlated Alarms count: " + resultCorrelated.size());

        File file = new File("C:\\Users\\m80028770\\test_result.csv");
        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new FileWriter(file));
            for(Alarm alarm:resultCorrelated) {
                br.write(alarm.toCsv() + "\r\n") ;
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


    }


}
