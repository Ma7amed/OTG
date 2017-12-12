package sample.model.DAO;

import sample.model.DTO.Alarm.Alarm;
import sample.model.DTO.Avail.Result_Avail2G;
import sample.model.DTO.Avail.Result_Avail3G;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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


    public ArrayList<Result_Avail2G> query2GAvail() {


        ArrayList<Result_Avail2G> queryResult = new ArrayList<>();

        stmt = null;


        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            con = DriverManager.getConnection(
                    "jdbc:sybase:Tds:" + db_ip + ":" + db_port, db_username, db_password);

            // System.out.println("ModelDB.connect: " + con);

            // Query

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql_query_2g_avail);
            while (rs.next()) {

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
        } finally {
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

    public ArrayList<Result_Avail3G> query3GAvail() {


        ArrayList<Result_Avail3G> queryResult = new ArrayList<>();

        stmt = null;


        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            con = DriverManager.getConnection(
                    "jdbc:sybase:Tds:" + db_ip + ":" + db_port, db_username, db_password);

            // System.out.println("ModelDB.connect: " + con);

            // Query

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql_query_3g_avail);
            while (rs.next()) {

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
        } finally {
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

    public ArrayList<Result_Avail3G> query3GAvail(LocalDateTime date) {


        String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        System.out.println("Date: " + dateString);

        ArrayList<Result_Avail3G> queryResult = new ArrayList<>();

        stmt = null;


        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            con = DriverManager.getConnection(
                    "jdbc:sybase:Tds:" + db_ip + ":" + db_port, db_username, db_password);

            // System.out.println("ModelDB.connect: " + con);

            // Query

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(SQLQueryGenerator.query_avail_3g_daily(dateString));
            while (rs.next()) {

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
        } finally {
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

        stmt = null;


        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            con = DriverManager.getConnection(
                    "jdbc:sybase:Tds:" + db_ip + ":" + db_port, db_username, db_password);

            // System.out.println("ModelDB.connect: " + con);

            // Query

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sqlString);
            System.out.println("U2000_DB.query2GAvail: ");
            while (rs.next()) {

                Result_Avail2G result__avail2G = new Result_Avail2G();
                result__avail2G.setStartTime(rs.getString(1));
                result__avail2G.setPeriod(rs.getString(2));

                System.out.println("U2000_DB.query2GAvail: " + result__avail2G);

                // System.out.println( rs.getString("test") );
            }
            // ResultSet rs = stmt.executeQuery(sql_select_solly_tt);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
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

    public ArrayList<String> getAlarmLogTableList(LocalDateTime startDateTime, LocalDateTime endDateTime) {


        String startDateString = startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String endDateString = endDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        System.out.println("Start Date: " + startDateString);
        System.out.println("End Date: " + endDateString);


        ArrayList<String> queryResult = new ArrayList<>();


        // Generate SQL String

        String sql_query_tablename_alarmlog = SQLQueryGenerator.query_tablename_alarmlog(startDateString,endDateString);

        System.out.println("sql: " + sql_query_tablename_alarmlog);

        stmt = null;


        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            con = DriverManager.getConnection(
                    "jdbc:sybase:Tds:" + db_ip + ":" + db_port, db_username, db_password);

            // System.out.println("ModelDB.connect: " + con);

            // Query

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql_query_tablename_alarmlog);
            while (rs.next()) {


                String tableName = rs.getString("TableName");
                queryResult.add(tableName);

                System.out.println("Found one table: " + tableName);


            }
            System.out.println("U2000 " + this.db_ip + " ... found " + queryResult.size() + " results.");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
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


    public ArrayList<Alarm> query3GAlm(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        // Query 3G NodeB Unavailable Alarm


        String startDateString = startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String endDateString = endDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ArrayList<String> almTableList = getAlarmLogTableList(startDateTime, endDateTime);



        StringBuilder sql_query_3g_alm = new StringBuilder();

        for (int i = 0; i < almTableList.size(); i++) {

            if (i == 0) {
                sql_query_3g_alm = new StringBuilder(SQLQueryGenerator.query_alm_3g_perTable(almTableList.get(i), startDateString, endDateString));
            } else {
                sql_query_3g_alm.append(" union ").append(SQLQueryGenerator.query_alm_3g_perTable(almTableList.get(i), startDateString, endDateString));
            }
        }

//        sql_query_3g_alm.append(" union ").append(SQLQueryGenerator.query_alm_3g_perTable("tbl_alm_log_2000000000", startDateString, endDateString));
        sql_query_3g_alm.append(" union ").append(SQLQueryGenerator.query_alm_3g_current());



        ArrayList<Alarm> queryResult = new ArrayList<>();

        stmt = null;


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
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

            System.out.println("");
            System.out.println("sql_query_3g_alm: " + sql_query_3g_alm);
            System.out.println("");


            ResultSet rs = stmt.executeQuery(sql_query_3g_alm.toString());
            while (rs.next()) {

                Alarm _alarm = new Alarm();
                _alarm.setLogSerialNumber(rs.getString("logSerialNumber"));
                _alarm.setMoName(rs.getString("alarmSource"));
                _alarm.setAlarmID(rs.getString("alarmID"));
                _alarm.setAlarmName(rs.getString("alarmName"));
                _alarm.setAcutalOccurTime(LocalDateTime.parse(rs.getString("occurTime"), formatter));
                _alarm.setOccurTime(LocalDateTime.parse(rs.getString("occurTime"), formatter));
                _alarm.setClearTime(LocalDateTime.parse(rs.getString("clearTime"), formatter));
                _alarm.setRemark(rs.getString("remark"));
                _alarm.setLocationInfo(rs.getString("ExtendInfo"));
                _alarm.setCount(1);


                queryResult.add(_alarm);


            }
            // ResultSet rs = stmt.executeQuery(sql_select_solly_tt);
            System.out.println("U2000 " + this.db_ip + " ... found " + queryResult.size() + " results.");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
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
