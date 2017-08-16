package sample.model.DAO;

import com.sun.org.apache.regexp.internal.RE;
import sample.model.DTO.Result_Alarm;
import sample.model.DTO.Result_Avail2G;
import sample.model.DTO.Result_Avail3G;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by m80028770 on 8/6/2017.
 */
public class U2000_DB extends Sybase_DB {


    public U2000_DB(String db_ip, String db_port, String db_username, String db_password) {
        super(db_ip, db_port, db_username, db_password);
    }

    private String sql_query_2g_avail = "select CONVERT( CHAR( 10 ), r.StartTime, 101 ) 'startTime'," +
            "r.GranulityPeriod 'period'," +
            "o.NeName 'neName'," +
            "o.ObjectMemName0 'site'," +
            "r.Counter_1276069420 'outServiceDuration'," +
            "r.Counter_1276069422 'inServiceDuration' " +
            "from  pmdb..tbl_Result_1275069417_4 r " +
            "left join pmdb..tbl_ObjectInstance o on o.ObjectNo = r.ObjectNo " +
            "where r.StartTime = CONVERT(CHAR(20),dateadd(dd,-1,getdate()),101) " +
            "and o.ObjectTypeId in " +
            "(select distinct ObjTypeId from pmdb..systbl_FunctionSubSet where FunctionSubSetId=1275069417)";



    private String sql_query_3g_avail = "select CONVERT( CHAR( 10 ), r.StartTime, 101 ) 'startTime',\n" +
            "r.GranulityPeriod 'period'," +
            "o.NeName 'neName'," +
            "substring(o.ObjectMemName0,12,100) 'site'," +
            "r.Counter_67203853 'unAvailTime' " +
            "from  pmdb..tbl_Result_67109473_4 r " +
            "left join pmdb..tbl_ObjectInstance o on o.ObjectNo = r.ObjectNo " +
            "where r.StartTime = CONVERT(CHAR(20),dateadd(dd,-1,getdate()),101) " +
            "and o.ObjectTypeId in " +
            "(select distinct ObjTypeId from pmdb..systbl_FunctionSubSet where FunctionSubSetId=67109473)";



    public  ArrayList<Result_Avail2G> query2GAvail() {


        ArrayList<Result_Avail2G> queryResult = new ArrayList<Result_Avail2G>();

        stmt= null;


        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            con = DriverManager.getConnection(
                    "jdbc:sybase:Tds:" + db_ip + ":" + db_port, db_username, db_password);

           // System.out.println("ModelDB.connect: " + con);

            // Query

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery( sql_query_2g_avail);
            while ( rs.next( ) ) {

                Result_Avail2G result_avail2G = new Result_Avail2G();
                result_avail2G.setStartTime(rs.getString("startTime"));
                result_avail2G.setPeriod(rs.getString("period"));
                result_avail2G.setNeName(rs.getString("neName"));
                result_avail2G.setSite(rs.getString("site"));
                result_avail2G.setInServiceDuration(rs.getInt("inServiceDuration"));
                result_avail2G.setOutServiceDuration(rs.getInt("outServiceDuration"));


                queryResult.add(result_avail2G);


            //    System.out.println("U2000_DB.query2GAvail: " + result_avail2G);

               // System.out.println( rs.getString("test") );
            }
            // ResultSet rs = stmt.executeQuery(sql_select_solly_tt);
            System.out.println("U2000 " + this.db_ip + " ... found " + queryResult.size() + " results.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return queryResult;
    }

    public  ArrayList<Result_Avail3G> query3GAvail() {


        ArrayList<Result_Avail3G> queryResult = new ArrayList<Result_Avail3G>();

        stmt= null;


        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            con = DriverManager.getConnection(
                    "jdbc:sybase:Tds:" + db_ip + ":" + db_port, db_username, db_password);

            // System.out.println("ModelDB.connect: " + con);

            // Query

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery( sql_query_3g_avail);
            while ( rs.next( ) ) {

                Result_Avail3G result_avail3G = new Result_Avail3G();
                result_avail3G.setStartTime(rs.getString("startTime"));
                result_avail3G.setPeriod(rs.getString("period"));
                result_avail3G.setNeName(rs.getString("neName"));
                result_avail3G.setSite(rs.getString("site"));
                result_avail3G.setUnAvailTime(rs.getInt("unAvailTime"));


                queryResult.add(result_avail3G);


                //System.out.println("U2000_DB.query2GAvail: " + result_avail3G);

                // System.out.println( rs.getString("test") );
            }
            // ResultSet rs = stmt.executeQuery(sql_select_solly_tt);
            System.out.println("U2000 " + this.db_ip + " ... found " + queryResult.size() + " results.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return queryResult;
    }

    public void doSQL(String sqlString) {

        stmt= null;


        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            con = DriverManager.getConnection(
                    "jdbc:sybase:Tds:" + db_ip + ":" + db_port, db_username, db_password);

            // System.out.println("ModelDB.connect: " + con);

            // Query

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery( sqlString);
            System.out.println("U2000_DB.query2GAvail: " );
            while ( rs.next( ) ) {

                Result_Avail2G result__avail2G = new Result_Avail2G();
                result__avail2G.setStartTime(rs.getString(1));
                result__avail2G.setPeriod(rs.getString(2));

                System.out.println("U2000_DB.query2GAvail: " + result__avail2G);

                // System.out.println( rs.getString("test") );
            }
            // ResultSet rs = stmt.executeQuery(sql_select_solly_tt);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public   ArrayList<String> getAlarmLogTableList(LocalDateTime startDateTime,LocalDateTime endDateTime) {


        String startDateString = startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS"));
        String endDateString = startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS"));


        System.out.println("Start Date: " + startDateString);
        System.out.println("End Date: " + endDateString);




        ArrayList<String> queryResult = new ArrayList<String>();


        // Generate SQL String

        String sql_query_tablename_alarmlog = "select   TableName " +
                "from fmdb..tbl_log_table_info " +
                "where PreTableName='NewTable' " +
                "and TableName like 'tbl_alm_log%' " +
                "and (" +
                "MinOccurTime <=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + startDateString + "',101)) " + //" -- starttime" +
                " and " +
                "MaxOccurTime >=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + startDateString + "',101)) " + // -- starttime\n" +
                " or " +
                "MinOccurTime <=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + endDateString + "',101)) " + // -- endtime\n" +
                " and " +
                "MaxOccurTime >=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + endDateString + "',101)) " + // -- endtime\n" +
                " or " +
                "MinOccurTime>=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + startDateString + "',101))  " + //-- starttime\n" +
                " and " +
                "MaxOccurTime >=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + endDateString + "',101)) " + // -- endtime\n" +
                ") "  +
                "order by MinOccurTime desc,MaxOccurTime desc";

        System.out.println("sql: " + sql_query_tablename_alarmlog);

        stmt= null;



        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            con = DriverManager.getConnection(
                    "jdbc:sybase:Tds:" + db_ip + ":" + db_port, db_username, db_password);

            // System.out.println("ModelDB.connect: " + con);

            // Query

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery( sql_query_tablename_alarmlog);
            while ( rs.next( ) ) {


                String tableName = rs.getString("TableName");
                queryResult.add(tableName);

                System.out.println("Found one table: " + tableName);



            }
            System.out.println("U2000 " + this.db_ip + " ... found " + queryResult.size() + " results.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return queryResult;
    }




    public  ArrayList<Result_Alarm> query3GAlm(LocalDateTime startDateTime,LocalDateTime endDateTime) {
    // Query 3G NodeB Unavailable Alarm


        String startDateString = startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS"));
        String endDateString = startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS"));

        ArrayList<String> almTableList  = getAlarmLogTableList(startDateTime,endDateTime);

        if(almTableList.size() == 1) {

        }else {
            // TODO if more than one table, construct new sql
        }

        String sql_query_3g_alm = "select " +
                "ReleateLogId \"logSerialNumber\"," +
                "substring(ExtendInfo,patindex('%NodeB Name=%',ExtendInfo)+11,patindex('%, Alarm Cause=%',ExtendInfo)-11-patindex('%NodeB Name=%',ExtendInfo)) \"alarmSource\"," +
                "Id 'alarmID'," +
                "'NodeB Unavailable' 'alarmName'," +
                "dateadd(second,OccurTime,'Jan 1 1970') 'occurTime'," +
                "dateadd(second,ClearTime,'Jan 1 1970') 'clearTime'," +
                "shortComment 'remark'," +
                "ExtendInfo\n" +
                "from fmdb..tbl_alm_log_14203 " +
                "where " +
                "Id=22214 " +
                "and OccurTime  >=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'2017-08-15 00:00:00',101)) " +
                "and OccurTime  <=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'2017-08-19 00:00:00',101))";

        String sql_query_3g_alm_v2 = "select " +
                "ReleateLogId \"logSerialNumber\"," +
                "substring(ExtendInfo,patindex('%NodeB Name=%',ExtendInfo)+11,patindex('%, Alarm Cause=%',ExtendInfo)-11-patindex('%NodeB Name=%',ExtendInfo)) \"alarmSource\"," +
                "Id 'alarmID'," +
                "'NodeB Unavailable' 'alarmName'," +
                "dateadd(second,OccurTime,'Jan 1 1970') 'occurTime'," +
                "dateadd(second,ClearTime,'Jan 1 1970') 'clearTime'," +
                "shortComment 'remark'," +
                "ExtendInfo\n" +
                "from " +
                //"fmdb..tbl_alm_log_14203" +
                almTableList.get(0) +
                " where " +
                "Id=22214 " +
                "and OccurTime  >=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'2017-08-15 00:00:00',101)) " +
                "and OccurTime  <=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'2017-08-19 00:00:00',101))";

        String sql_query_3g_alm_v3 = "select " +
                "ReleateLogId \"logSerialNumber\"," +
                "substring(ExtendInfo,patindex('%NodeB Name=%',ExtendInfo)+11,patindex('%, Alarm Cause=%',ExtendInfo)-11-patindex('%NodeB Name=%',ExtendInfo)) \"alarmSource\"," +
                "Id 'alarmID'," +
                "'NodeB Unavailable' 'alarmName'," +
                "dateadd(second,OccurTime,'Jan 1 1970') 'occurTime'," +
                "dateadd(second,ClearTime,'Jan 1 1970') 'clearTime'," +
                "shortComment 'remark'," +
                "ExtendInfo\n" +
                "from " +
                //"fmdb..tbl_alm_log_14203" +
                almTableList.get(0) +
                " where " +
                "Id=22214 " +
                "and OccurTime  >=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + startDateString + "',101)) " +
                "and OccurTime  <=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + endDateString + "',101))";

        ArrayList<Result_Alarm> queryResult = new ArrayList<Result_Alarm>();

        stmt= null;


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS");
        //LocalDate expireDate = LocalDate.parse("2018-01-01",formatter);


        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            con = DriverManager.getConnection(
                    "jdbc:sybase:Tds:" + db_ip + ":" + db_port, db_username, db_password);

            // System.out.println("ModelDB.connect: " + con);

            // Query

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery( sql_query_3g_alm);
            while ( rs.next( ) ) {

                Result_Alarm result_alarm = new Result_Alarm();
                result_alarm.setLogSerialNumber(rs.getString("logSerialNumber"));
                result_alarm.setAlarmSource(rs.getString("alarmSource"));
                result_alarm.setAlarmID(rs.getString("alarmID"));
                result_alarm.setAlarmName(rs.getString("alarmName"));
                result_alarm.setOccurTime(LocalDateTime.parse(rs.getString("occurTime"),formatter));
                result_alarm.setClearTime(LocalDateTime.parse(rs.getString("clearTime"),formatter));
                result_alarm.setRemark(rs.getString("remark"));
                result_alarm.setLocationInfo(rs.getString("ExtendInfo"));



                queryResult.add(result_alarm);



            }
            // ResultSet rs = stmt.executeQuery(sql_select_solly_tt);
            System.out.println("U2000 " + this.db_ip + " ... found " + queryResult.size() + " results.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return queryResult;
    }




}
