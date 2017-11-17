package afyapepe.mobile.activity;

/**
 * Created by Millie Agallo on 11/6/2017.
 */

public class Stock {
    public String Drugname,Quantity,Name, Id, Total,FacilityName,Pharmacy,County,Manufacturer,Totalq, Qprice ,Count;


    public String getName(){
        return Name;
    }
    public void setName(String name){
        Name= name;
    }
    public String getDrugname(){
        return Drugname;
    }
    public void setDrugname(String drugname){
        Drugname = drugname;
    }
    public String getQuantity(){
        return Quantity;
    }
    public void setQuantity(String quantity){
        Quantity = quantity;
    }
    public String getId(){
        return Id;
    }
    public void setId(String id){
        Id = id;
    }
    public String getTotal(){
        return Total;
    }
    public void setTotal(String total){
        Total = total;
    }
    //"FacilityName","pharmacy","county"},
    public String getFacilityName(){
        return FacilityName;
    }
    public void setFacilityName(String facilityName){
        FacilityName = facilityName;
    }
    public String getPharmacy(){
        return Pharmacy;
    }
    public void setPharmacy(String pharmacy){
        Pharmacy = pharmacy;
    }
    public String getCounty(){
        return County;
    }
    public void setCounty(String county){
        County = county;
    }
    public String getManufacturer(){
        return Manufacturer;
    }
    public void setManufacturer(String manufacturer){
        Manufacturer= manufacturer;
    }
    public String getTotalq(){
        return Totalq;
    }
    public void setTotalq(String totalq) {
        Totalq = totalq;
    }
    public String getQprice(){
        return  Qprice;
    }
    public void setQprice(String qprice){
        Qprice = qprice;
    }

}
