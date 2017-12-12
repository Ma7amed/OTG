package sample.model.DTO.DailyReport;

/**
 * Created by m80028770 on 8/29/2017.
 */
public class Daily3G {

    private String site_code;
    private String site_id;
    private String region;
    private String bsc_region;
    private String bsc;
    private String rnc;
    private String site_name;
    private String office;
    private String m2000;
    private String lmt;
    private String category;
    private String sub_category;
    private String owner;
    private String rot_td_sp;
    private String no_of_cells;
    private String no_of_activated_cells;
    private String first_activation_date;
    private String dt_confirm;
    private String comments_of_3g;
    private String type;
    private String locking_date;
    private String locking_year;
    private String creation_date;
    private String creation_year;

    public String getSite_code() {
        return site_code;
    }

    public void setSite_code(String site_code) {
        this.site_code = site_code;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBsc_region() {
        return bsc_region;
    }

    public void setBsc_region(String bsc_region) {
        this.bsc_region = bsc_region;
    }

    public String getBsc() {
        return bsc;
    }

    public void setBsc(String bsc) {
        this.bsc = bsc;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getM2000() {
        return m2000;
    }

    public void setM2000(String m2000) {
        this.m2000 = m2000;
    }

    public String getLmt() {
        return lmt;
    }

    public void setLmt(String lmt) {
        this.lmt = lmt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRot_td_sp() {
        return rot_td_sp;
    }

    public void setRot_td_sp(String rot_td_sp) {
        this.rot_td_sp = rot_td_sp;
    }

    public String getNo_of_cells() {
        return no_of_cells;
    }

    public void setNo_of_cells(String no_of_cells) {
        this.no_of_cells = no_of_cells;
    }

    public String getNo_of_activated_cells() {
        return no_of_activated_cells;
    }

    public void setNo_of_activated_cells(String no_of_activated_cells) {
        this.no_of_activated_cells = no_of_activated_cells;
    }

    public String getFirst_activation_date() {
        return first_activation_date;
    }

    public void setFirst_activation_date(String first_activation_date) {
        this.first_activation_date = first_activation_date;
    }

    public String getDt_confirm() {
        return dt_confirm;
    }

    public void setDt_confirm(String dt_confirm) {
        this.dt_confirm = dt_confirm;
    }

    public String getComments_of_3g() {
        return comments_of_3g;
    }

    public void setComments_of_3g(String comments_of_3g) {
        this.comments_of_3g = comments_of_3g;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocking_date() {
        return locking_date;
    }

    public void setLocking_date(String locking_date) {
        this.locking_date = locking_date;
    }

    public String getLocking_year() {
        return locking_year;
    }

    public void setLocking_year(String locking_year) {
        this.locking_year = locking_year;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getCreation_year() {
        return creation_year;
    }

    public void setCreation_year(String creation_year) {
        this.creation_year = creation_year;
    }

    @Override
    public String toString() {
        return "Daily3G{" +
                "site_code='" + site_code + '\'' +
                ", site_id='" + site_id + '\'' +
                ", region='" + region + '\'' +
                ", bsc_region='" + bsc_region + '\'' +
                ", bsc='" + bsc + '\'' +
                ", rnc='" + rnc + '\'' +
                ", site_name='" + site_name + '\'' +
                ", office='" + office + '\'' +
                ", m2000='" + m2000 + '\'' +
                ", lmt='" + lmt + '\'' +
                ", category='" + category + '\'' +
                ", sub_category='" + sub_category + '\'' +
                ", owner='" + owner + '\'' +
                ", rot_td_sp='" + rot_td_sp + '\'' +
                ", no_of_cells='" + no_of_cells + '\'' +
                ", no_of_activated_cells='" + no_of_activated_cells + '\'' +
                ", first_activation_date='" + first_activation_date + '\'' +
                ", dt_confirm='" + dt_confirm + '\'' +
                ", comments_of_3g='" + comments_of_3g + '\'' +
                ", type='" + type + '\'' +
                ", locking_date='" + locking_date + '\'' +
                ", locking_year='" + locking_year + '\'' +
                ", creation_date='" + creation_date + '\'' +
                ", creation_year='" + creation_year + '\'' +
                '}';
    }
}
