package ca.ubc.cs304.model;

import java.sql.Date;
import java.sql.Time;

/**
 * The intent for this class is to update/store information about a return
 */
public class ReturnsModel {
  private final int rid;
  private final Date date;
  private final Time time;
  private final int odometer;
  private final int fulltank;  // either 1 or 0
  private final float value;

  public ReturnsModel(int rid, Date date, Time time, int odometer, 
        int fulltank, float value) {
    this.rid = rid;
    this.date = date;
    this.time = time;
    this.odometer = odometer;
    this.fulltank = fulltank;
    this.value = value;
  }

  public int getRid() {
    return rid;
  }

  public Date getDate() {
    return date;
  }

  public Time getTime() {
    return time;
  }

  public int getOdometer() {
    return odometer;
  }

  public int getFulltank() {
    return fulltank;
  }

  public float getValue() {
    return value;
  }

}