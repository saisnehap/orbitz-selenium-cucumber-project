package calendar;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
public class DepartureArrivalDates {


    @Test
        public static String departDate (int weeks) {

            LocalDate departDate = LocalDate.now();
            log.info(" >> departDate() current calendar date: " + departDate);
            LocalDate returnvalue
                    = departDate.plusWeeks(weeks);
            DateTimeFormatter departformatedDate = DateTimeFormatter.ofPattern("d", Locale.ENGLISH);
            log.info(" >> departDate() formatted departure date: "+ returnvalue.format(departformatedDate));
            return returnvalue.format(departformatedDate);
        }

        @Test
        public static String departMonthDate () {

            LocalDate departDate = LocalDate.now();
            log.info(" >> departMonthDate() current calendar month and date: " + departDate);
            LocalDate returnvalue
                    = departDate.plusWeeks(2);
            DateTimeFormatter departformatedDate = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);
           log.info (" >> departMonthDate() formatted departure month date: "+returnvalue.format(departformatedDate));
            return returnvalue.format(departformatedDate);
        }

        @Test
        public static String arrivalDate () throws ParseException {

            LocalDate arrivalDate = LocalDate.now();
            log.info(" >> arrivalDate() current calendar month and date: " + arrivalDate);
            LocalDate returnvalue
                    = arrivalDate.plusWeeks(3);
            DateTimeFormatter arrivalFormattedDate = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);
            log.info(" >> arrivalDate() formatted arrival month and date: "+returnvalue.format(arrivalFormattedDate));
            return returnvalue.format(arrivalFormattedDate);
        }

        @Test
        public static String arrivalDay (int weeks) throws ParseException {

            LocalDate arrivalDate = LocalDate.now();
            LocalDate returnvalue
                    = arrivalDate.plusWeeks(weeks);
//        DateTimeFormatter arrivalformattedDate = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
            DateTimeFormatter arrivalDay = DateTimeFormatter.ofPattern("d", Locale.ENGLISH);
            String day = returnvalue.format(arrivalDay);
            log.info(" >> arrivalDay() formatted arrival calendar date: "+ day);
            return returnvalue.format(arrivalDay);

        }

    }

