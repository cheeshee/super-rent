package ca.ubc.cs304.model.ModelForManipulation;

/**
 * The intent for this class is to update/store information about a single customer entry
 */
public class ManipulateCustomersModel {
  private String dlicense;
  private String name;
  private String cellphone;
  private String address;

  public ManipulateCustomersModel() { }

  public void setDlicense(String dlicense) {this.dlicense = dlicense;}
  public String getDlicense() {
    return dlicense;
  }

  public void setName(String name) {this.name = name;}
  public String getName() {
    return name;
  }

  public void setCellphone(String cellphone) {this.cellphone = cellphone;}
  public String getCellphone() {
    return cellphone;
  }

  public void setAddress(String address) {this.address = address;}
  public String getAddress() {
    return address;
  }
}