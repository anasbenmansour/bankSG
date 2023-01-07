package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * util methods
 */
public class Util {

    // date formatter
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     *  wait message
     */
    public static void pressAnyKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    /**
     *  format local date time
     * @return formatted date
     */
    public static String formatDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(FORMATTER);
    }
}
