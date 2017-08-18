package sample.model.DTO;

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
    private String alarmSource;
    private String moName;
    private String locationInfo;
    private String remark;

    private LocalDateTime occurTime;
    private LocalDateTime clearTime;

    // Correlation Part
    private String identifier;

    public static String[] HEADERS = {
            "LogSerialNumber",
            "Alarm Source",
            "Alarm ID",
            "Alarm Name",
            "Occur Time",
            "Clear Time",
            "Remark",
            "Location Info"
    };

    private void updateIdentifier() {
        identifier = getAlarmName() + "_" + getAlarmSource();
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
        updateIdentifier();
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getAlarmSource() {
        return alarmSource;
    }

    public void setAlarmSource(String alarmSource) {
        this.alarmSource = alarmSource;
        updateIdentifier();
    }

    public String getMoName() {
        return moName;
    }

    public void setMoName(String moName) {
        this.moName = moName;
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
        return occurTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    public String getClearTimeString() {
        return clearTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setOccurTime(String occurTime, String pattern) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        this.occurTime = LocalDateTime.parse(occurTime, formatter);

    }

    public void setClearTime(String clearTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        this.clearTime = LocalDateTime.parse(clearTime, formatter);
    }


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {

        String result = "";
        result += "Alarm: " + getAlarmName();
        result += ", AlarmSource: " + getAlarmSource();
        result += ", OccurTime: " + getOccurTimeString();
        result += ", ClearTime: " + getClearTimeString();
        result += ", Identifier: " + getIdentifier();

        return result;


    }

    public String toCsv() {

        String delimiter=",";
        String result = "";
        result +=getAlarmName() + delimiter;
        result +=getAlarmSource() + delimiter;
        result +=getOccurTimeString() + delimiter;
        result +=getClearTimeString();

        return result;
    }

/*
    @Override
    public int compareTo(Object o) {

        // Compare by AlarmSource, then occur time


        Alarm alarm = (Alarm) o;

       // int alarmSourceCompare = alarm.getAlarmSource().compareTo(getAlarmSource());
        int alarmSourceCompare = getAlarmSource().compareTo(alarm.getAlarmSource());

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

        // int alarmSourceCompare = alarm.getAlarmSource().compareTo(getAlarmSource());
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
