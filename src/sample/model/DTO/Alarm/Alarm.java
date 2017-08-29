package sample.model.DTO.Alarm;

import sample.model.AlarmUtil;
import sample.model.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by m80028770 on 8/16/2017.
 */
public class Alarm implements Comparable {

    private String logSerialNumber;
    private String alarmID;
    private String alarmName;
    private String objectType;
    private String moName;
    private String alarmSource;
    private String locationInfo;
    private String remark;
    private int count;
    // private int downTime;

    private LocalDateTime occurTime;
    private LocalDateTime clearTime;

    public static String pattern = "yyyy-MM-dd HH:mm:ss";

    // Correlation Part
   // private String identifier;

    public static String[] HEADERS = {
            "LogSerialNumber",
            "MO Name",
            "Alarm ID",
            "Alarm Name",
            "Occur Time",
            "Clear Time",
            "Remark",
            "Location Info",
            "Count"
    };

//    private void updateIdentifier() {
//        identifier = getAlarmName() + "_" + getMoName();
//    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
//        updateIdentifier();
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getMoName() {
        return moName;
    }

    public void setMoName(String moName) {
        this.moName = moName;
//        updateIdentifier();
    }

    public String getAlarmSource() {
        return alarmSource;
    }

    public void setAlarmSource(String alarmSource) {
        this.alarmSource = alarmSource;
    }

    public String getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(LocalDateTime occurTime) {
        this.occurTime = occurTime;
    }

    public LocalDateTime getClearTime() {
        return clearTime;
    }

    public void setClearTime(LocalDateTime clearTime) {
        this.clearTime = clearTime;
    }

    public String getLogSerialNumber() {
        return logSerialNumber;
    }

    public void setLogSerialNumber(String logSerialNumber) {
        this.logSerialNumber = logSerialNumber;
    }

    public String getAlarmID() {
        return alarmID;
    }

    public void setAlarmID(String alarmID) {
        this.alarmID = alarmID;
    }


    public String getOccurTimeString() {
        return occurTime.format(DateTimeFormatter.ofPattern(pattern));
    }


    public String getClearTimeString() {
        return clearTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public void setOccurTime(String occurTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        this.occurTime = LocalDateTime.parse(occurTime, formatter);

    }

    //08/21/2017 23:55:41

    public void setOccurTime(String occurTime,String otherPattern) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(otherPattern);
        this.occurTime = LocalDateTime.parse(occurTime, formatter);

    }

    public void setClearTime(String clearTime,String otherPattern) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(otherPattern);
        this.clearTime = LocalDateTime.parse(clearTime, formatter);

    }

    public void setClearTime(String clearTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        this.clearTime = LocalDateTime.parse(clearTime, formatter);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getIdentifier() {
        return getAlarmName() + "_" + getMoName();
    }

//    public void setIdentifier(String identifier) {
//        this.identifier = identifier;
//    }

    public long getDownTime() {
        return Util.subDate(occurTime,clearTime);

    }

    @Override
    public String toString() {

        String result = "";
        result += "Alarm: " + getAlarmName();
        result += ", AlarmSource: " + getMoName();
        result += ", OccurTime: " + getOccurTimeString();
        result += ", ClearTime: " + getClearTimeString();
        result += ", Identifier: " + getIdentifier();

        return result;


    }

    public String toCsv() {

        String delimiter=",";
        String result = "";
        result +=getAlarmName() + delimiter;
        result += "\"" + getMoName() + "\"" + delimiter;
        result +=getOccurTimeString() + delimiter;
        result +=getClearTimeString();

        return result;
    }

/*
    @Override
    public int compareTo(Object o) {

        // Compare by AlarmSource, then occur time


        Alarm alarm = (Alarm) o;

       // int alarmSourceCompare = alarm.getMoName().compareTo(getMoName());
        int alarmSourceCompare = getMoName().compareTo(alarm.getMoName());

        if (alarmSourceCompare == 0) {

            long dateDiff = Util.subDate(alarm.getOccurTime(), getOccurTime());
            if (dateDiff == 0) {
                return 0;
            } else if (dateDiff < 0) {
                return -1;
            } else {
                // > 0
                return 1;
            }
        } else {
            return alarmSourceCompare;
        }

    }
    */


    @Override
    public int compareTo(Object o) {

        // Compare by AlarmSource, then occur time


        Alarm alarm = (Alarm) o;

        // int alarmSourceCompare = alarm.getMoName().compareTo(getMoName());
        int alarmSourceCompare = getIdentifier().compareTo(alarm.getIdentifier());

        if (alarmSourceCompare == 0) {

            long dateDiff = Util.subDate(alarm.getOccurTime(), getOccurTime());
            if (dateDiff == 0) {
                return 0;
            } else if (dateDiff < 0) {
                return -1;
            } else {
                // > 0
                return 1;
            }
        } else {
            return alarmSourceCompare;
        }

    }
}
