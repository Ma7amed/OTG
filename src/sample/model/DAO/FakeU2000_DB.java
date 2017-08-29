package sample.model.DAO;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.model.DTO.Alarm.Alarm;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by m80028770 on 8/18/2017.
 */
public class FakeU2000_DB


{


    private String almFileLocation = "D:\\Work\\OSS\\Temp_Delete\\20170820\\OTG\\U2000.csv";

    private static final Logger logger = LogManager.getRootLogger();


//    public ArrayList<Alarm> query3GAlm(LocalDateTime startDateTime, LocalDateTime endDateTime) {
//
//        ArrayList<Alarm> queryResult = new ArrayList<>();
//
//        File csvFile = new File(almFileLocation);
//        String line = "";
//        String delimiter = ",";
//        int skipLinesCount = 1;
//        try {
//
//
//            BufferedReader br = new BufferedReader(new FileReader(csvFile));
//            while((line = br.readLine())!=null) {
//
//                // Skip first line
//                if(skipLinesCount-->0) {
//                    continue;
//                }
//                String[] lineSplit = line.split(delimiter);
//
//                System.out.println("Start -------------");
//                for(int i=0;i<lineSplit.length;i++) {
//                    System.out.println(lineSplit[i]);
//                }
//
//                Alarm alarm = new Alarm();
//                alarm.setLogSerialNumber(lineSplit[0]);
//                alarm.setMoName(lineSplit[1]);
//                alarm.setAlarmID(lineSplit[2]);
//                alarm.setAlarmName(lineSplit[3]);
//                alarm.setOccurTime(lineSplit[4]);
//                alarm.setClearTime(lineSplit[5]);
//
//                queryResult.add(alarm);
//            }
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return queryResult;
//
//    }


    public ArrayList<Alarm> query3GAlm(LocalDateTime startDateTime, LocalDateTime endDateTime) {

        ArrayList<Alarm> queryResult = new ArrayList<>();

        File csvFile = new File(almFileLocation);
        // String line = "";
        int skipLinesCount = 8;

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext() ) != null) {

                if (skipLinesCount-- > 0 || line.length<=1) {
                    continue;
                }

                System.out.println(line.length);
                for(int i=0;i<line.length;i++) {
                    System.out.println("i: " + i + ", " + line[i]);
                }

                Alarm alarm = new Alarm();
                alarm.setLogSerialNumber(line[1]);
                alarm.setMoName(line[2]);
                alarm.setAlarmID(line[3]);
                alarm.setAlarmName(line[4]);
                alarm.setOccurTime(line[5],"MM/dd/yyyy HH:mm:ss");
                String clearTime = line[6];
                if(clearTime.equals("-")){
                    alarm.setClearTime("01/01/2020 00:00:00", "MM/dd/yyyy HH:mm:ss");
                }else {
                    alarm.setClearTime(line[6], "MM/dd/yyyy HH:mm:ss");
                }
                alarm.setCount(1);

                queryResult.add(alarm);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return queryResult;

    }
//
//    public ArrayList<Alarm> query3GAlm(LocalDateTime startDateTime, LocalDateTime endDateTime) {
//
//        ArrayList<Alarm> queryResult = new ArrayList<>();
//
//        File csvFile = new File(almFileLocation);
//        // String line = "";
//        String delimiter = ",";
//        int skipLinesCount = 1;
//
//        CSVReader reader = null;
//        try {
//            reader = new CSVReader(new FileReader(csvFile));
//            String[] line;
//            while ((line = reader.readNext()) != null) {
//
//                if (skipLinesCount-- > 0) {
//                    continue;
//                }
//
//                for(int i=0;i<line.length;i++) {
//                    System.out.println("i: " + i + ", " + line[i]);
//                }
//
//                Alarm alarm = new Alarm();
//                alarm.setLogSerialNumber(line[0]);
//                alarm.setMoName(line[1]);
//                alarm.setAlarmID(line[2]);
//                alarm.setAlarmName(line[3]);
//                alarm.setOccurTime(line[4]);
//                alarm.setClearTime(line[5]);
//                alarm.setOriginalAlarmCount(1);
//
//                queryResult.add(alarm);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return queryResult;
//
//    }



    public String getAlmFileLocation() {
        return almFileLocation;
    }

    public void setAlmFileLocation(String almFileLocation) {
        this.almFileLocation = almFileLocation;
    }


}
