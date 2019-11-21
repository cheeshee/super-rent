package ca.ubc.cs304.model;

public class InsertCustomerQueryModel implements QueryModel {
    private String dlicense;
    private String name;
    private long cellphone;
    private String address;

    public InsertCustomerQueryModel() {}

    public void setDlicense(String dlicense) {
        this.dlicense = dlicense;
    }
    public String getDlicense() {
        return this.dlicense;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setCellphone(long cellphone) {
        this.cellphone = cellphone;
    }
    public long getCellphone(){
        return this.cellphone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return this.address;
    }
}
