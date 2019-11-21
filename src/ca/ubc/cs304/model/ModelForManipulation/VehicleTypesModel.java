package ca.ubc.cs304.model.ModelForManipulation;

/**
 * The intent for this class is to update/store information about a vehicle type
 */
public class VehicleTypesModel {
  private String vtname;
  private String features;
  private float wrate;
  private float drate;
  private float hrate;
  private float wirate;
  private float dirate;
  private float hirate;
  private float krate;

  public VehicleTypesModel() { }

  public void setVtname(String vtname) {this.vtname = vtname;  }
  public String getVtname() {
    return vtname;
  }

  public void setFeatures(String features) { this.features = features; }
  public String getFeatures() {
    return features;
  }

  public void setWrate(Float wrate) { this.wrate = wrate; }
  public float getWrate() {
    return wrate;
  }

  public void setDrate (Float drate) { this.drate = drate; }
  public float getDrate() {
    return drate;
  }

  public void setHrate(Float hrate) { this.hrate = hrate; }
  public float getHrate() {
    return hrate;
  }

  public void setWirate(Float wirate) { this.wirate = wirate; }
  public float getWirate() {
    return wirate;
  }

  public void setDirate(Float dirate) { this.dirate = dirate; }
  public float getDirate() {
    return dirate;
  }

  public void setHirate(Float hirate)  { this.hirate = hirate; }
  public float getHirate() {
    return hirate;
  }

  public void setKrate(Float krate) { this.krate = krate;  }
  public float getkrate() {
    return krate;
  }

}