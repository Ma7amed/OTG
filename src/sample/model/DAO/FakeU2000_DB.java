package sample.model.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.model.DTO.Alarm;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by m80028770 on 8/18/2017.
 */
public class FakeU2000_DB


{

    private static final Logger logger = LogManager.getRootLogger();


    public ArrayList<Alarm> query3GAlm(LocalDateTime startDateTime, LocalDateTime endDateTime) {

        ArrayList<Alarm> queryResult = new ArrayList<>();

        File csvFile = new File("C:\\Users\\m80028770\\3G_Alm.csv");
        String line = "";
        String delimiter = ",";
        int skipLinesCount = 1;
        try {


            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while((line = br.readLine())!=null) {

                // Skip first line
                if(skipLinesCount-->0) {
                    continue;
                }
                String[] lineSplit = line.split(delimiter);

                Alarm alarm = new Alarm();
                alarm.setLogSerialNumber(lineSplit[0]);
                alarm.setAlarmSource(lineSplit[1]);
                alarm.setAlarmID(lineSplit[2]);
                alarm.setAlarmName(lineSplit[3]);
                alarm.setOccurTime(lineSplit[4],"yyyy-MM-dd HH:mm:ss");
                alarm.setClearTime(lineSplit[5],"yyyy-MM-dd HH:mm:ss");

                queryResult.add(alarm);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return queryResult;

    }


}
