package model;

import java.time.LocalDateTime;

    public class User {
        private int userId;
        private static String username;
        private String password;
        private LocalDateTime createDate;
        private String author;
        private LocalDateTime lastUpdate;
        private String updatedBy;

        public User(int userId, String username, String password, LocalDateTime createDate, String author, LocalDateTime lastUpdate, String lastUpdateAuthor) {
        }

        public User() {

        }


        //set methods

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }


        public void setCreateDate(LocalDateTime createDate) {
            this.createDate = createDate;
        }

        public void setCreatedBy(String author) {
            this.author = author;
        }

        public void setLastUpdate(LocalDateTime lastUpdate) {
            this.lastUpdate = lastUpdate;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        //get methods
        public int getUserId() {
            return userId;
        }

        public static String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public LocalDateTime getCreateDate() {
            return createDate;
        }

        public String getCreatedBy() {
            return author;
        }

        public LocalDateTime getLastUpdate() {
            return lastUpdate;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

}
