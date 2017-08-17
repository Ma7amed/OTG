package sample.model.DTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by m80028770 on 8/16/2017.
 */
public class Alarm {

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


    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
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

    public void setOccurTime(String occurTime,String pattern) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        this.occurTime =  LocalDateTime.parse(occurTime, formatter);

    }

    public void setClearTime(String clearTime,String pattern)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        this.clearTime =  LocalDateTime.parse(clearTime, formatter);    }


    @Override
    public String toString() {

        String result= "";
        result += "Alarm: " + getAlarmName();
        result += ", AlarmSource: " + getAlarmSource();
        result += ", OccurTime: " + getOccurTimeString();
        result += ", ClearTime: " + getClearTimeString();

        return result;


    }
}
