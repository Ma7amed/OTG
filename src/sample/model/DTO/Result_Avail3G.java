package sample.model.DTO;

/**
 * Created by m80028770 on 8/6/2017.
 */
public class Result_Avail3G {

    private String startTime;
    private String period;
    private String neName;
    private String site;
    private int unAvailTime;

    public static String[] HEADERS = {"Start Time","Period (min)", "NE Name", "Site", "Unavailable Time"};

    public Result_Avail3G() {
    }

    public Result_Avail3G(String startTime, String period, String neName, String site, int unAvailTime) {
        this.startTime = startTime;
        this.period = period;
        this.neName = neName;
        this.site = site;
        this.unAvailTime = unAvailTime;
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

    public int getUnAvailTime() {
        return unAvailTime;
    }

    public void setUnAvailTime(int unAvailTime) {
        this.unAvailTime = unAvailTime;
    }


    @Override
    public String toString() {
        return "" +
                startTime + ", " +
                period + ", " +
                neName + ", " +
                site + ", " +
                unAvailTime;

    }
}
