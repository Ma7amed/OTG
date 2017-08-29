package sample.model.DTO.Avail;

/**
 * Created by m80028770 on 8/6/2017.
 */
public class Result_Avail2G {

    private String startTime;
    private String period;
    private String neName;
    private String site;
    private int inServiceDuration;
    private int outServiceDuration;

    public static String[] HEADERS = {"Start Time","Period (min)", "NE Name", "Site", "In Service Duration","Out Service Duration"};

    public Result_Avail2G() {
    }

    public Result_Avail2G(String startTime, String period, String neName, String site, int inServiceDuration, int outServiceDuration) {
        this.startTime = startTime;
        this.period = period;
        this.neName = neName;
        this.site = site;
        this.inServiceDuration = inServiceDuration;
        this.outServiceDuration = outServiceDuration;
    }

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

    public int getInServiceDuration() {
        return inServiceDuration;
    }

    public void setInServiceDuration(int inServiceDuration) {
        this.inServiceDuration = inServiceDuration;
    }

    public int getOutServiceDuration() {
        return outServiceDuration;
    }

    public void setOutServiceDuration(int outServiceDuration) {
        this.outServiceDuration = outServiceDuration;
    }

    @Override
    public String toString() {
        return "" +
                startTime + ", " +
                period + ", " +
                neName + ", " +
                site + ", " +
                inServiceDuration + ", " +
                outServiceDuration;

    }
}
