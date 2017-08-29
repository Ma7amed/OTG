package sample.model.DTO.Outage;

/**
 * Created by m80028770 on 8/27/2017.
 */
public class OutageSummary_3G {

    private String startTime;
    private String period;
    private String neName;
    private String site;
    private long unAvailTime;
    private int originalAlarmCount;
    private int correlatedAlarmCount;
    private long totalDownTime;
    // gap between down time and unavail time,,,, just get ,, calculate onair


    public static String[] HEADERS = {"Start Time", "Period (min)", "NE Name", "Site", "Original Alarm Count",
            "Correlated Alarm Count", "Unavailable Time", "Total Down Time", "Alarm/Avail Gap","Unavailable Time [Date]", "Total Down Time", "Alarm/Avail Gap", "Alarm/Avail Gap Sign"};

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getNeName() {
        return neName;
    }

    public void setNeName(String neName) {
        this.neName = neName;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public long getUnAvailTime() {
        return unAvailTime;
    }

    public void setUnAvailTime(int unAvailTime) {
        this.unAvailTime = unAvailTime;
    }

    public int getOriginalAlarmCount() {
        return originalAlarmCount;
    }

    public void setOriginalAlarmCount(int originalAlarmCount) {
        this.originalAlarmCount = originalAlarmCount;
    }

    public int getCorrelatedAlarmCount() {
        return correlatedAlarmCount;
    }

    public void setCorrelatedAlarmCount(int correlatedAlarmCount) {
        this.correlatedAlarmCount = correlatedAlarmCount;
    }

    public long getTotalDownTime() {
        return totalDownTime;
    }

    public void setTotalDownTime(long totalDownTime) {
        this.totalDownTime = totalDownTime;
    }

    public long getAlarmAvailGap() {

        // The original/correct value is availablity
        // so + means alarm down time is more
        //    - means alarm down time is less than availability
        return getTotalDownTime() - getUnAvailTime();

    }

    @Override
    public String toString() {
        return "OutageSummary_3G{" +
                "startTime='" + startTime + '\'' +
                ", period='" + period + '\'' +
                ", neName='" + neName + '\'' +
                ", site='" + site + '\'' +
                ", unAvailTime=" + unAvailTime +
                ", originalAlarmCount=" + originalAlarmCount +
                ", correlatedAlarmCount=" + correlatedAlarmCount +
                ", totalDownTime=" + totalDownTime +
                '}';
    }
}
