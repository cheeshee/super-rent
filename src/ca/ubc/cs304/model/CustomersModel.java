package ca.ubc.cs304.model;

import oracle.net.aso.n;

/**
 * The intent for this class is to update/store information about a single customer entry
 */
public class CustomersModel {
  private final int dlicense;
  private final String name;
  private final int cellphone;
  private final String address;

  public CustomersModel(int dlicense, String name, int cellphone, String address) {
    this.dlicense = dlicense;
    this.name = name;
    this.cellphone = cellphone;
    this.address = address;
  }

  public int getDlicense() {
    return dlicense;
  }

  public String getName() {
    return name;
  }

  public int getCellphone() {
    return cellphone;
  }

  public String getAddress() {
    return address;
  }
}