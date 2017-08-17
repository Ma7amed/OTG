package sample.model;

import sample.model.DTO.Alarm;

import java.util.ArrayList;

/**
 * Created by m80028770 on 8/17/2017.
 */
public class AlarmUtil {

    private int maxTimeGap = 1800;

    public ArrayList<Alarm> minimizeAlarms(ArrayList<Alarm> alarms) {
        // Merge alarms together that happen at the same time, the same time,
        // or there is little gap between them (ex: 30 minutes)

        return alarms;
    }

}
