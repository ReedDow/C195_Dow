package model;

import java.time.LocalDateTime;

public class Division {
        private  int divisionId;
        private  String divisionName;
        private  LocalDateTime createDate;
        private  String author;
        private  LocalDateTime lastUpdate;
        private  String lastUpdateAuthor;
        private  int countryId;

        public Division (int divisionID, String Division, LocalDateTime CreateDate, String Author, LocalDateTime LastUpdate, String LastUpdateAuthor, int CountryId) {
            divisionId = divisionID;
            divisionName = Division;
            createDate = CreateDate;
            author = Author;
            lastUpdate = LastUpdate;
            lastUpdateAuthor = LastUpdateAuthor;
            countryId = CountryId;
        }

    public Division() {
    }

    /**Get method for division id*/
    public int getDivisionId(){
        return divisionId;
    }

    /**Get method for division*/
    public String getDivisionName(){
        return divisionName;
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

    public int getCountryId(){
        return countryId;
    }

    @Override
    public String toString(){
        return getDivisionName();
    }

    /**set method for time*/
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }




}
