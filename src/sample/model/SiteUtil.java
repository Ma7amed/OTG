package sample.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by m80028770 on 8/18/2017.
 */
public class SiteUtil {

    public static String siteName2ID(String siteName) {

        String pattern = "[0-9]{3,4}";

        return searchByPattern(pattern, siteName);
    }

    public static String deviceName2SiteName(String deviceName) {

        String pattern,siteName;

        pattern = "(LALX|LDEL|LSIN|LUPP|LCAI|ALX|DEL|SIN|UPP)[0-9]{4}";

        siteName = searchByPattern(pattern,deviceName);
        if(siteName!=null) {
            return siteName;
        }


        pattern = "(LALX|LDEL|LSIN|LUPP|LCAI|ALX|DEL|SIN|UPP)[0-9]{3}";
        siteName = searchByPattern(pattern,deviceName);
        if(siteName!=null) {
            return siteName.substring(0,siteName.length()-3) + "0" + siteName.substring(siteName.length()-3,siteName.length()) ;
        }

        return searchByPattern(pattern, deviceName);
    }

    private static String searchByPattern(String patternString, String text) {


        Matcher matcher;

        Pattern pattern = Pattern.compile(patternString);
        matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return null;
        }


    }

}
