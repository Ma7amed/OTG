package sample.model.DTO.Outage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static sample.model.DTO.Alarm.Alarm.pattern;

/**
 * Created by m80028770 on 8/18/2017.
 */
public class OutageRecord_3G {

    private String date;
    private String region;
    private String rnc;
    private String siteName;
    private String siteCode;
    private String siteId;
    private String siteCategory;
    private String technicalArea;
    private String siteLayerQism;
    private LocalDateTime alarmOccurrenceTime;
    private LocalDateTime faultOccurrenceTime;
    private LocalDateTime faultClearanceTime;
    private long mttr;
    private long downTime;
    private String siteType;
    private String slaStatus;
    private String reasonCategory;
    private String reasonSubCategory;
    private String comment;
    private String owner;
    private String accessType;
    private String bbt;
    private String bbtJustification;
    private String cascadedTo;
    private String downSiteStatus;
    private String status;
    private String tt;

    public static String[] HEADERS = {
            "date",
            "region",
            "rnc",
            "siteName",
            "siteCode",
            "siteId",
            "siteCategory",
            "technicalArea",
            "siteLayerQism",
            "alarmOccurrenceTime",
            "faultOccurrenceTime",
            "faultClearanceTime",
            "mttr",
            "downTime",
            "siteType",
            "slaStatus",
            "reasonCategory",
            "reasonSubCategory",
            "comment",
            "owner",
            "accessType",
            "bbt",
            "bbtJustification",
            "cascadedTo",
            "downSiteStatus",
            "status",
            "tt"
    };



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteCategory() {
        return siteCategory;
    }

    public void setSiteCategory(String siteCategory) {
        this.siteCategory = siteCategory;
    }

    public String getTechnicalArea() {
        return technicalArea;
    }

    public void setTechnicalArea(String technicalArea) {
        this.technicalArea = technicalArea;
    }

    public String getSiteLayerQism() {
        return siteLayerQism;
    }

    public void setSiteLayerQism(String siteLayerQism) {
        this.siteLayerQism = siteLayerQism;
    }

    public LocalDateTime getAlarmOccurrenceTime() {
        return alarmOccurrenceTime;
    }



    public void setAlarmOccurrenceTime(LocalDateTime alarmOccurrenceTime) {
        this.alarmOccurrenceTime = alarmOccurrenceTime;
    }

    public LocalDateTime getFaultOccurrenceTime() {
        return faultOccurrenceTime;
    }

    //.format(DateTimeFormatter.ofPattern(pattern));

    public String getFaultOccurrenceTimeString() {
        return faultOccurrenceTime.format(DateTimeFormatter.ofPattern(pattern));
    }


    public void setFaultOccurrenceTime(LocalDateTime faultOccurrenceTime) {
        this.faultOccurrenceTime = faultOccurrenceTime;
    }

    public LocalDateTime getFaultClearanceTime() {
        return faultClearanceTime;
    }

    public void setFaultClearanceTime(LocalDateTime faultClearanceTime) {
        this.faultClearanceTime = faultClearanceTime;
    }

    public long getMttr() {
        return mttr;
    }

    public void setMttr(long mttr) {
        this.mttr = mttr;
    }

    public long getDownTime() {
        return downTime;
    }

    public void setDownTime(long downTime) {
        this.downTime = downTime;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getSlaStatus() {
        return slaStatus;
    }

    public void setSlaStatus(String slaStatus) {
        this.slaStatus = slaStatus;
    }

    public String getReasonCategory() {
        return reasonCategory;
    }

    public void setReasonCategory(String reasonCategory) {
        this.reasonCategory = reasonCategory;
    }

    public String getReasonSubCategory() {
        return reasonSubCategory;
    }

    public void setReasonSubCategory(String reasonSubCategory) {
        this.reasonSubCategory = reasonSubCategory;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getBbt() {
        return bbt;
    }

    public void setBbt(String bbt) {
        this.bbt = bbt;
    }

    public String getBbtJustification() {
        return bbtJustification;
    }

    public void setBbtJustification(String bbtJustification) {
        this.bbtJustification = bbtJustification;
    }

    public String getCascadedTo() {
        return cascadedTo;
    }

    public void setCascadedTo(String cascadedTo) {
        this.cascadedTo = cascadedTo;
    }

    public String getDownSiteStatus() {
        return downSiteStatus;
    }

    public void setDownSiteStatus(String downSiteStatus) {
        this.downSiteStatus = downSiteStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

    public String getDateString() {
//        if(null == date) {
//            return "";
//        }
//        return date.format(DateTimeFormatter.ofPattern(pattern));
            return date;
    }

    public String getFaultClearanceTimeString() {
        if(null == faultClearanceTime) {
            return "";
        }
        return faultClearanceTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public String getAlarmOccurrenceTimeString() {
        if(null == alarmOccurrenceTime) {
            return "";
        }
        return alarmOccurrenceTime.format(DateTimeFormatter.ofPattern(pattern));
    }




    @Override
    public String toString(){

        String result = "";
        result += getSiteName() + ", ";
        result += getSiteCode() + ", ";
        result += getSiteId() + ", ";
        result += getDownTime() ;

        return  result;
    }
}
