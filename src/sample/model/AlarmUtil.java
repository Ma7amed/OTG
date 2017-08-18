package sample.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.model.DTO.Alarm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class AlarmUtil {

    private static final Logger logger = LogManager.getLogger(AlarmUtil.class);


    private int maxTimeGap = 1800;
    private LocalDateTime minOccurTime;
    private LocalDateTime maxClearTime;
    private int minDuration = 600;


    public ArrayList<Alarm> optimize(ArrayList<Alarm> alarmList) {
        // Merge alarms together that happen at the same time, the same time,
        // or there is little gap between them (ex: 30 minutes)

        // Empty array to contains the results
        ArrayList<Alarm> tempList = new ArrayList<>();
        tempList.addAll(alarmList);

        ArrayList<Alarm> result;

        // 1- Sort Alarms
        logger.debug("Sorting alarms by Identifier");
        Collections.sort(tempList);

        // 2- Remove useless alarms (according to occur/clear time)
        logger.debug("Trimming alarms occur/clear times");
        //tempList = trimAlarmTimes(tempList);
        trimAlarmTimes(tempList);

        // 3- Merge Alarms
        result = mergeAlarms(tempList);

        // 4- Remove small duration alarms
        // result = removeSmallDurationAlarms(result);
        removeSmallDurationAlarms(result);
        logger.info("original size: " + alarmList.size());
        logger.info("result size: " + result.size());
        logger.info("removed records: " + (alarmList.size() - result.size()) * 100 / alarmList.size() + "%");


        return result;
    }


    private Alarm mergeTwoAlarms(Alarm alarm1, Alarm alarm2) {

        if (!alarm1.getAlarmSource().equals(alarm2.getAlarmSource())) {
            return null;
        }

        //System.out.println("AlarmUtil.mergeTwoAlarms: " + Util.subDate(alarm1.getOccurTime(), alarm2.getOccurTime()));
        if (Util.subDate(alarm1.getClearTime(), alarm2.getOccurTime()) > maxTimeGap) {
            return null;
        } else {
            Alarm returnAlarm = new Alarm();
            returnAlarm.setAlarmName(alarm1.getAlarmName());
            returnAlarm.setAlarmSource(alarm1.getAlarmSource());
            returnAlarm.setOccurTime(alarm1.getOccurTime());
            if (Util.subDate(alarm1.getClearTime(), alarm2.getClearTime()) > 0) {
                // alarm2 clear time is more than alarm1
                returnAlarm.setClearTime(alarm2.getClearTime());
            } else {
                returnAlarm.setClearTime(alarm1.getClearTime());
            }
            return returnAlarm;
        }
    }


    private ArrayList<Alarm> mergeAlarms(ArrayList<Alarm> data) {

        ArrayList<Alarm> tempList = new ArrayList<>();
        tempList.addAll(data);

        ArrayList<Alarm> result = new ArrayList<>();

        while (tempList.size() > 1) {
            // While at least have 2 fields

            // Get first 2 alarms
            Alarm a1 = tempList.get(0);
            Alarm a2 = tempList.get(1);

            logger.debug("1st Alarm: " + a1);
            logger.debug("2nd Alarm: " + a2);


            Alarm a3 = mergeTwoAlarms(a1, a2);
            logger.debug("Merged Alarm: " + a3);
//            System.out.println("a3: " + a3);
            if (a3 == null) {
                // There is nothing happened ... move to other array
                logger.debug("Moving to result: " + tempList.get(0));
                result.add(tempList.get(0));
                logger.debug("Removing from list: " + tempList.get(0));
                tempList.remove(0);
            } else {
                logger.debug("Removing from list: " + tempList.get(0));
                tempList.remove(0);
                logger.debug("Removing from list: " + tempList.get(0));
                tempList.remove(0);

                logger.debug("Adding to list at index 0: " + a3);
                tempList.add(0, a3);
            }
        }

        result.addAll(tempList);

//        for (Alarm alarm : tempList) {
//            result.add(alarm);
//        }

        return result;
    }


    public void trimOccurTime(Alarm alarm) {
        // Trim occur time not to include any occur time less than the specified one
        // Any time less than the specified, will be updated to the minOccurTime
        // Ex: minOccurTime 2017-08-18 00:00:00 ,,,, 2017-08-15 14:34:33 >> 2017-08-18 00:00:00
        // EX: minOccurTime 2017-08-18 00:00:00 ,,,, 2017-08-18 12:11:44 >> 2017-08-18 12:11:44 (After min date, no change)

        if (minOccurTime == null) {
            return;
        }

        if (alarm.getOccurTime().compareTo(minOccurTime) < 0) {
            alarm.setOccurTime(minOccurTime);
        }

    }

    public void trimClearTime(Alarm alarm) {

        if (maxClearTime == null) {
            return;
        }

        logger.error("BEFORE TRIM: " + alarm);
        if (alarm.getClearTime().compareTo(maxClearTime) > 0) {
            alarm.setClearTime(maxClearTime);
        }
        logger.error("AFTER TRIM: " + alarm);

    }


    public void trimAlarmTimes(ArrayList<Alarm> data) {

        // any occur time before minOccur time will be shifted to minOccurTime
        // any clear time after maxOccur time will be shifted to maxOccurTime

        // any occur time after maxClear time ,,, alarm will be removed .. not happen
        // any clear time before minOccur time ,,, alarm will be removed
/*
        ArrayList<Alarm> result = new ArrayList<>();

        if (minOccurTime == null && maxClearTime == null) {
            return null;
        }


        for (Alarm alarm : data) {
            if (alarm.getOccurTime().compareTo(maxClearTime) > 0 || alarm.getClearTime().compareTo(minOccurTime) < 0) {
                // Ignore this alarm
                // logger.error("Ignoring: " + alarm);
                continue;
            } else {
                trimOccurTime(alarm);
                trimClearTime(alarm);
                result.add(alarm);
            }
        }
*/

        // The same but without new array

        if (minOccurTime == null && maxClearTime == null) {
            logger.error("There is nothing done, minOccurTime/maxClearTime not set");
            logger.error("minOccurTime: " + minOccurTime);
            logger.error("maxOccurTime: " + maxClearTime);
            return;
        }


        Iterator<Alarm> i = data.iterator();

        while (i.hasNext()) {
            Alarm alarm = i.next();

            if (alarm.getOccurTime().compareTo(maxClearTime) > 0 || alarm.getClearTime().compareTo(minOccurTime) < 0) {
                // Remove this alarm
                i.remove();
            } else {
                trimOccurTime(alarm);
                trimClearTime(alarm);
            }
        }

    }

    private void removeSmallDurationAlarms(ArrayList<Alarm> data) {

//        ArrayList<Alarm> result = new ArrayList<>();
//
//        for (Alarm alarm : data) {
//            if (Util.subDate(alarm.getOccurTime(), alarm.getClearTime()) >= minDuration) {
//                // Move to result
//                result.add(alarm);
//            }
//        }
//
//        return result;

        // The same with no new array

        // Remove this small duration alarm
        data.removeIf(alarm -> Util.subDate(alarm.getOccurTime(), alarm.getClearTime()) < minDuration);

    }


    public LocalDateTime getMinOccurTime() {
        return minOccurTime;
    }

    public void setMinOccurTime(LocalDateTime minOccurTime) {
        this.minOccurTime = minOccurTime;
    }

    public LocalDateTime getMaxClearTime() {
        return maxClearTime;
    }

    public void setMaxClearTime(LocalDateTime maxClearTime) {
        this.maxClearTime = maxClearTime;
    }

    public void setMinOccurTime(String minOccurTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Alarm.pattern);
        this.minOccurTime = LocalDateTime.parse(minOccurTime, formatter);

    }


    public void setMaxClearTime(String maxClearTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Alarm.pattern);
        this.maxClearTime = LocalDateTime.parse(maxClearTime, formatter);

    }

    public int getMaxTimeGap() {
        return maxTimeGap;
    }

    public void setMaxTimeGap(int maxTimeGap) {
        this.maxTimeGap = maxTimeGap;
    }

    public int getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }
}
