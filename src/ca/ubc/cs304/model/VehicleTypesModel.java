package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a vehicle type
 */
public class VehicleTypesModel {
  private final String vtname;
  private final String features;
  private final float wrate;
  private final float drate;
  private final float hrate;
  private final float wirate;
  private final float dirate;
  private final float hirate;
  private final float krate;

  public VehicleTypesModel(String vtname, String features, 
        Float wrate, Float drate, Float hrate,
        Float wirate, Float dirate, Float hirate, Float krate) {
    this.vtname = vtname;
    this.features = features;
    this.wrate = wrate;
    this.drate = drate;
    this.hrate = hrate;
    this.wirate = wirate;
    this.dirate = dirate;
    this.hirate = hirate;
    this.krate = krate;
  }
  
  public String getVtname() {
    return vtname;
  }

  public String getFeatures() {
    return features;
  }

  public float getWrate() {
    return wrate;
  }

  public float getDrate() {
    return drate;
  }

  public float getHrate() {
    return hrate;
  }

  public float getWirate() {
    return wirate;
  }

  public float getDirate() {
    return dirate;
  }

  public float getHirate() {
    return hirate;
  }
  
  public float getkrate() {
    return krate;
  }

}