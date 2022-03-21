package model;

/**Customer model*/
public class Customer {

    private Integer customerId;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private String division;
    private Integer divisionId;
    private String country;


    /**Constructor for customer*/
    public Customer(Integer customerId, String name, String address, String postalCode, String phone, String division, Integer divisionId, String country){
        customerId = customerId;
        name = name;
        address = address;
        postalCode = postalCode;
        phone = phone;
        division = division;
        divisionId = divisionId;
        country = country;
    }

    /**Get method for customer ID*/
    public Integer getCustomerId(
    ){ return customerId;}

    /**Set method for id*/
    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }

    /**Get method for name*/
    public String getName(
    ){ return name;}

    /**Set method for name*/
    public void setName(String name){
        this.name = name;
    }

    /**Get method for address*/
    public String getAddress(
    ){ return address;}

    /**Get method for postalCode*/
    public String getPostalCode(
    ){ return postalCode;}

    /**Get method for phone*/
    public String getPhone(
    ){ return phone;}

    /**Get method for division*/
    public String getDivision(
    ){ return division;}

    /**Get method for division ID*/
    public Integer getDivisionId(
    ){ return divisionId;}

    /**Get method for country*/
    public String getCountry(
    ){ return country;}


}
