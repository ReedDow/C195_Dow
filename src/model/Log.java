package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Log {

    /** This method keeps a record of log in attempts by writing user info to a file for each log in attempt, including date, time, and location of the user. */

    public static void loginActivity(String username, Boolean success) {
        try {

            BufferedWriter log = new BufferedWriter(new FileWriter("login_activity.txt", true));
            log.append(ZonedDateTime.now() + " Log-in attempt by: " + username +
                    ".   Success? " + success.toString() + "\n \n");
            log.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }
}
