package model;

import java.time.LocalDateTime;

public class Division {
        private final int divisionId;
        private final String divisionName;
        private final LocalDateTime createDate;
        private final String author;
        private final LocalDateTime lastUpdate;
        private final String lastUpdateAuthor;
        private final int countryId;

        public Division (Integer divisionID, String cDivision, LocalDateTime cCreateDate, String cAuthor, LocalDateTime cLastUpdate, String cLastUpdateAuthor, int cCountryId) {
            divisionId = divisionID;
            divisionName = cDivision;
            createDate = cCreateDate;
            author = cAuthor;
            lastUpdate = cLastUpdate;
            lastUpdateAuthor = cLastUpdateAuthor;
            countryId = cCountryId;
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

    /**Get method for countryId*/
    public int getCountryId(){
        return countryId;
    }

}
