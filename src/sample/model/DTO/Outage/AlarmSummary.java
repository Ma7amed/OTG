package sample.model.DTO.Outage;

/**
 * Created by m80028770 on 8/22/2017.
 */
public class AlarmSummary {
    private String identifier;
    private String moName;
    private long downTime;
    private int originalAlarmCount;
    private int correlatedAlarmCount;


    public AlarmSummary() {
        downTime = 0;
        originalAlarmCount = 0;
    }


    public AlarmSummary(String identifier, String moName, long downTime, int originalAlarmCount, int correlatedAlarmCount) {
        this.identifier = identifier;
        this.moName = moName;
        this.downTime = downTime;
        this.originalAlarmCount = originalAlarmCount;
        this.correlatedAlarmCount = correlatedAlarmCount;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getMoName() {
        return moName;
    }

    public void setMoName(String moName) {
        this.moName = moName;
    }

    public long getDownTime() {
        return downTime;
    }

    public void setDownTime(long downTime) {
        this.downTime = downTime;
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

    @Override
    public String toString() {
        return "AlarmSummary{" +
                "identifier='" + identifier + '\'' +
                ", moName='" + moName + '\'' +
                ", downTime=" + downTime +
                ", originalAlarmCount=" + originalAlarmCount +
                ", correlatedAlarmCount=" + correlatedAlarmCount +
                '}';
    }
}

