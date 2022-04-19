package model;

import java.time.LocalDateTime;

/**Customer model*/
public class Customer {

    private Integer customerId;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime time;
    private String author;
    private LocalDateTime lastUpdate;
    private String lastUpdateAuthor;
    private String division;
    private int divisionId;
    private String country;
    private int countryId;



    /**Constructor for customer*/
    public Customer(Integer customerID, String cName, String cAddress, String cPostalCode, String cPhone, LocalDateTime cTime, String cAuthor, LocalDateTime cLastUpdate, String cLastUpdateAuthor, String cDivision, Integer cDivisionId, String cCountry, Integer cCountryId){
        customerId = customerID;
        name = cName;
        address = cAddress;
        postalCode = cPostalCode;
        phone = cPhone;
        time = cTime;
        author = cAuthor;
        lastUpdate = cLastUpdate;
        lastUpdateAuthor = cLastUpdateAuthor;
        division = cDivision;
        divisionId = cDivisionId;
        country = cCountry;
        countryId = cCountryId;
    }

    public Customer() {
    }

    /**Get method for customer id*/

    public Integer getCustomerId() {
        return customerId;
    }

    /**Get method for name*/
    public String getName(
    ){ return name;}

    /**Get method for address*/
    public String getAddress(
    ){ return address;}

    /**Get method for postalCode*/
    public String getPostalCode(
    ){ return postalCode;}

    /**Get method for phone*/
    public String getPhone(
    ){ return phone;}

    public LocalDateTime getTime(
    ){return time;}

    public String getAuthor(
    ){return author;}

    public LocalDateTime getLastUpdate(
    ){return lastUpdate;}

    public String getLastUpdateAuthor(
    ){return lastUpdateAuthor;}


    /**Get method for division
     * @return*/
    public String getDivision(
    ){ return division;}

    /**Get method for division ID*/
    public Integer getDivisionId(
    ){ return divisionId;}

    /**Get method for country
     * @return*/
    public String getCountry(
    ){ return country;}

    /**Get method for country ID*/
    public int getCountryId(
    ){ return countryId;}

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**set method for customer ID*/
    public void setName(String name) {
        this.name = name;
    }

    /**set method for address*/
    public void setAddress(String address) {
        this.address = address;
    }

    /**set method for postal code*/
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**set method for phone*/
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**set method for time*/
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**set method for author*/
    public void setAuthor(String author) {
        this.author = author;
    }

    /**set method for last update time*/
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**set method for last update author*/
    public void setLastUpdateAuthor(String lastUpdateAuthor) {
        this.lastUpdateAuthor = lastUpdateAuthor;
    }

    /**set method for division id*/
    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    /**set method for division*/
    public void setDivision(String division){
        this.division = division;
    }

    /**set method for country*/
    public void setCountry(String time) {
        this.country = country;
    }

    /**set method for country id*/
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString(){
        return getName();
    }


}
