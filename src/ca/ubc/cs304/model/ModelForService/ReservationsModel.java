package ca.ubc.cs304.model.ModelForService;
import java.sql.Date;
import java.sql.Time;

/**
 * The intent for this class is to update/store information about a reservation
 */
public class ReservationsModel {
    private int confNo;
    private String vtname;
    private String vlicense;
    private int dlicense;
    private String fromDate;
    private String toDate;

    private int rid;
    private int odometer;
    private String cardName;
    private String cardNo;
    private String expDate;


    public ReservationsModel() {
    }

    public void setConfNo(int confNo) {
        this.confNo = confNo;
    }
    public int getConfNo() {
        return confNo;
    }

    public void setVtname(String vtname) {
        this.vtname = vtname;
    }
    public String getVtname() {
        return vtname;
    }

    public void setVlicense(String vlicense) {
        this.vlicense = vlicense;
    }
    public String getVlicense() {
        return vlicense;
    }

    public void setDlicense(int dlicense) {
        this.dlicense = dlicense;
    }
    public int getDlicense() {
        return dlicense;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    public String getFromDate() {
        return fromDate;
    }


    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    public String getToDate() {
        return toDate;
    }

    // TODO add five more sets for the last five fields
}
