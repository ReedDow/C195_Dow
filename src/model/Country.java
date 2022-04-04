package model;

import java.time.LocalDateTime;

public class Country {
    private final int countryId;
    private final String countryName;
    private final LocalDateTime createDate;
    private final String author;
    private final LocalDateTime lastUpdate;
    private final String lastUpdateAuthor;

    public Country(Integer countryID, String cCountry, LocalDateTime cCreateDate, String cAuthor, LocalDateTime cLastUpdate, String cLastUpdateAuthor){
        countryId = countryID;
        countryName= cCountry;
        createDate = cCreateDate;
        author = cAuthor;
        lastUpdate = cLastUpdate;
        lastUpdateAuthor = cLastUpdateAuthor;
    }

    /**Get method for countryId*/
    public int getCountryId(){
        return countryId;
    }

    /**Get method for country*/
    public String getCountryName(){
        return countryName;
    }

    /**Get method for crateDate*/
    public LocalDateTime getCreateDate(){
        return createDate;
    }

    /**Get method for author*/
    public String getAuthor(){
        return author;
    }

    /**Get method for lastUpdate*/
    public LocalDateTime getLastUpdate(){
        return lastUpdate;
    }

    /**Get method for lastUpdateAuthor*/
    public String getLastUpdateAuthor(){
        return lastUpdateAuthor;
    }



}
