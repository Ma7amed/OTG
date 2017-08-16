package sample.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by m80028770 on 8/16/2017.
 */
public class Util {

    public static int toUnixTime() {

        Date now = new Date();



        int nowMill = (int) (now.getTime()/1000);

        return nowMill;

    }


}
