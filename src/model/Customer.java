package model;

import java.time.LocalDateTime;

/**Customer model*/
public class Customer {

    private final Integer customerId;
    private final String name;
    private final String address;
    private final String postalCode;
    private final String phone;
    private final LocalDateTime time;
    private final String author;
    private LocalDateTime lastUpdate;
    private String lastUpdateAuthor;
    private final String division;
    private final Integer divisionId;
    private final String country;
    private final Integer countryId;



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

    /**Get method for customer id*/
    public int getCustomerId(
    ){ return customerId;}

//    /**Set method for customer id*/
//    public void setCustomerId(int customerId){
//        this.customerId = customerId;
//    }

    /**Get method for name*/
    public String getName(
    ){ return name;}

//    /**Set method for name*/
//    public void setName(String name){
//        this.name = name;
//    }

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


    /**Get method for division*/
    public String getDivision(
    ){ return division;}

    /**Get method for division ID*/
    public Integer getDivisionId(
    ){ return divisionId;}

    /**Get method for country*/
    public String getCountry(
    ){ return country;}

    public int getCountryId(
    ){ return countryId;}


}
