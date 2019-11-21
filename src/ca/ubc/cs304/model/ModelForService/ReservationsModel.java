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
  private Date fromDate;
  private Time fromTime;
  private Date toDate;
  private Time toTime;

  public ReservationsModel() {}

  public void setConfNo(int confNo) {this.confNo = confNo;}
  public int getConfNo() {
		return confNo;
  }

  public void setVtname(String vtname) {this.vtname = vtname;}
  public String getVtname() {
    return vtname;
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
}