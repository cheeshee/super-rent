package ca.ubc.cs304.model.ModelForService;

public class CustomerQueryModel {
    private String vtname;
    private String location;
    private String vlicense;
    private long fromDate;
    private long toDate;

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

    public void setFromDate(long fromDate) {
        this.fromDate = fromDate;
    }
    public long getFromDate() {return this.fromDate;}

    public void setToDate(long toDate){
        this.toDate = toDate;
    }
    public long getToDate(long toDate) {
        return this.toDate;
    }
}
