package ca.ubc.cs304.model;
import java.sql.Date;
import java.sql.Time;

/**
 * The intent for this class is to update/store information about a reservation
 */
public class ReservationsModel {
  private final int confNo;
  private final String vtname;
  private final String vlicense;
  private final int dlicense;
  private final Date fromDate;  
  private final Time fromTime;
  private final Date toDate;
  private final Time toTime;

  public ReservationsModel(int confNo, String vtname, String vlicense, int dlicense, Date fromDate, Time fromTime, Date toDate, Time toTime) {
    this.confNo = confNo;
    this.vtname = vtname;
    this.vlicense = vlicense;
    this.dlicense = dlicense;
    this.fromDate = fromDate;
    this.fromTime = fromTime;
    this.toDate = toDate;
    this.toTime = toTime;
  }

  public int getConfNo() {
		return confNo;
  }
  
  public String getVtname() {
    return vtname;
  }

  public String getVlicense() {
    return vlicense;
  }

  public int getDlicense() {
    return dlicense;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public Time getFromTime() {
    return fromTime;
  }

  public Date getToDate() {
    return toDate;
  }
  
  public Time getToTime() {
    return toTime;
  }
}