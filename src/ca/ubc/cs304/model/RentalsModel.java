package ca.ubc.cs304.model;
import java.sql.Date;
import java.sql.Time;

/**
 * The intent for this class is to update/store information about a rental
 */
public class RentalsModel {
  private final int rid;
  private final String vlicense;
  private final int dlicense;
  private final Date fromDate;  
  private final Time fromTime;
  private final Date toDate;
  private final Time toTime;
  private final int odometer;
  private final String cardName;
  private final int cardNo;
  private final Date expDate;
  private final int confNo;

  public RentalsModel(int rid, String vlicense, int dlicense, 
        Date fromDate, Time fromTime, Date toDate, Time toTime,
        int odometer, String cardName, int cardNo, Date expDate, int confNo) {
    this.rid = rid;
    this.vlicense = vlicense;
    this.dlicense = dlicense;
    this.fromDate = fromDate;
    this.fromTime = fromTime;
    this.toDate = toDate;
    this.toTime = toTime;
    this.odometer = odometer;
    this.cardName = cardName;
    this.cardNo = cardNo;
    this.expDate = expDate;
    this.confNo = confNo;
  }

  public int getRid() {
    return rid;
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

  public int getOdometer() {
    return odometer;
  }

  public String getCardName() {
    return cardName;
  }

  public int getCardNo() {
    return cardNo;
  }

  public Date getExpDate() {
    return expDate;
  }
  
  public int getConfNo() {
		return confNo;
  }
}