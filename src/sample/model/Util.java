package sample.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by m80028770 on 8/16/2017.
 */
public class Util {

//    public static int toUnixTime() {
//
//        Date now = new Date();
//
//        int nowMill = (int) (now.getTime()/1000);
//
//        return nowMill;
//
//    }

    public static long subDate(LocalDateTime date1, LocalDateTime date2) {


        // subtract second date2 from date1 and get seconds diff
        // date1  ........................................
        // date2  ......................................................
        // result                                         ..............


        return ( date2.toEpochSecond(ZoneOffset.UTC) - date1.toEpochSecond(ZoneOffset.UTC) );
    }


}
