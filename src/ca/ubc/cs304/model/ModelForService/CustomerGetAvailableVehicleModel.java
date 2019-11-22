package ca.ubc.cs304.model.ModelForService;

public class CustomerGetAvailableVehicleModel {
    private String vtname;
    private String location;
    private String vlicense;
    // DD/MM/YYYY

    private String fromDate;
    private String toDate;

    public void setVtname(String vtname) {
        this.vtname = vtname;
    }
    public String getVtname (){
        return this.vtname;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation(String location) {
        return this.location;
    }

    public void setVlicense(String vlicense) {
        this.vlicense = vlicense;
    }
    public String getVlicense(String vlicense) {
        return this.vlicense;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    public String getFromDate() {return this.fromDate;}

    public void setToDate(String toDate){
        this.toDate = toDate;
    }
    public String getToDate(String toDate) {
        return this.toDate;
    }
}
