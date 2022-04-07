package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Log {

    private static final String logPath = "audit_log.txt";

    /** This method keeps a record of log in attempts by writing user info to a file for each log in attempt. */

    public static void auditLog(String username, Boolean success) throws IOException {
        try {

            BufferedWriter log = new BufferedWriter(new FileWriter(logPath, true));
            log.append(ZonedDateTime.now(ZoneOffset.UTC).toString() + " UTC-LOGIN ATTEMPT-USERNAME: " + username +
                    " LOGIN SUCCESSFUL: " + success.toString() + "\n");
            log.flush();
            log.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }

    }
}
