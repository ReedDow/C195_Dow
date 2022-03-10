package model;

/**Customer model*/
public class Customer {

    private Integer customerID;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private String division;
    private Integer divisionID;
    private String country;


    /**Constructor for customer*/
    public Customer(Integer customerID, String name, String address, String postalCode, String phone, String division, Integer divisionID, String country){
        customerID = customerID;
        name = name;
        address = address;
        postalCode = postalCode;
        phone = phone;
        division = division;
        divisionID = divisionID;
        country = country;
    }

    /**Get method for customer ID*/
    public Integer getCustomerID(
    ){ return customerID;}

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
    ){ return divisionID;}

    /**Get method for country*/
    public String country(
    ){ return country;}
}
