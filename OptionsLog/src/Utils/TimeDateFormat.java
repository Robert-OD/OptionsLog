package Utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeDateFormat {

    public static String convertToDateTimeFormat(LocalDateTime ldt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = ldt.format(formatter);
        return formatDateTime;
    }

    public static LocalDateTime stringToLocalDateTime(String date){
        String[] dateString = date.split(" ")[0].split("-");
        String[] timeString = date.split(" ")[1].split(":");
        int year = Integer.parseInt(dateString[0]);
        int month = Integer.parseInt(dateString[1]);
        int day = Integer.parseInt(dateString[2]);
        int hour = Integer.parseInt(timeString[0]);
        int minute = Integer.parseInt(timeString[1]);
        int second = 0;
        LocalDate tempLocalDate = LocalDate.of(year, month, day);
        LocalTime tempLocalTime = LocalTime.of(hour, minute, second);
        return LocalDateTime.of(tempLocalDate, tempLocalTime);
    }
}
