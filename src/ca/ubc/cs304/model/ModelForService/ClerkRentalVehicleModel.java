package ca.ubc.cs304.model.ModelForService;
import java.sql.Date;
import java.sql.Time;

/**
 * The intent for this class is to update/store information about a rental
 */
public class ClerkRentalVehicleModel {
    // TODO: check fields and corresponding getter and setter

    private int rid;
    private String vlicense;
    private int dlicense;
    private Date fromDate;
    private Time fromTime;
    private Date toDate;
    private Time toTime;
    private int odometer;
    private String cardName;
    private int cardNo;
    private Date expDate;
    private int confNo;

    public ClerkRentalVehicleModel() {}

    public void setRid(int rid) {this.rid = rid;}
    public int getRid() {
    return rid;
    }

    public void setVlicense(String vlicense) {this.vlicense = vlicense;}
    public String getVlicense() {
    return vlicense;
    }

    public void setDlicense(int dlicense) {this.dlicense = dlicense;}
    public int getDlicense() {
    return dlicense;
    }

    public void setFromDate(Date fromDate) {this.fromDate = fromDate;}
    public Date getFromDate() {
    return fromDate;
    }

    public void setFromTime(Time fromTime) {this.fromTime = fromTime;}
    public Time getFromTime() {
    return fromTime;
    }

    public void setToDate(Date toDate) {this.toDate = toDate;}
    public Date getToDate() {
    return toDate;
    }

    public void setToTime(Time toTime) {this.toTime = toTime;}
    public Time getToTime() {
    return toTime;
    }

    public void setOdometer(int odometer) {this.odometer = odometer;}
    public int getOdometer() {
    return odometer;
    }

    public void setCardName(String cardName) {this.cardName = cardName;}
    public String getCardName() {
    return cardName;
    }

    public void setCardNo(int cardNo) {this.cardNo = cardNo;}
    public int getCardNo() {
    return cardNo;
    }

    public void setExpDate(Date expDate) {this.expDate = expDate;}
    public Date getExpDate() {
    return expDate;
    }

    public void setConfNo(int confNo) {this.confNo=confNo;}
    public int getConfNo() {
        return confNo;
    }
}