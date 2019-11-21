package ca.ubc.cs304.model.ModelForService;

import java.sql.Date;
import java.sql.Time;

/**
 * The intent for this class is to update/store information about a return
 */
public class ClerkReturnsVehicleModel {
  private String rid;
  private String date;
  private String time;
  private int odometer;
  private int fulltank;  // either 1 or 0
  private String value;

  public ClerkReturnsVehicleModel() {}

  public void setRid(String rid) {this.rid = rid;}
  public String getRid() {
    return rid;
  }

  public void setDate(String date) {this.date = date;}
  public String getDate() {
    return date;
  }

  public void setTime(String time) {this.time = time;}
  public String getTime() {
    return time;
  }

  public void setOdometer(int odometer) {this.odometer = odometer;}
  public int getOdometer() {
    return odometer;
  }

  public void setFulltank(int fulltank) {this.fulltank = fulltank;}
  public int getFulltank() {
    return fulltank;
  }

  public void setValue(String value) { this.value = value;}
  public String getValue() {
    return value;
  }

}