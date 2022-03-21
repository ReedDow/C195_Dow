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

    /**Get method for name*/
    public String name(
    ){ return name;}

    /**Get method for address*/
    public String address(
    ){ return address;}

    /**Get method for postalCode*/
    public String postalCode(
    ){ return postalCode;}

    /**Get method for phone*/
    public String phone(
    ){ return phone;}

    /**Get method for division*/
    public String division(
    ){ return division;}

    /**Get method for division ID*/
    public Integer divisionID(
    ){ return divisionId;}

    /**Get method for country*/
    public String country(
    ){ return country;}
}
