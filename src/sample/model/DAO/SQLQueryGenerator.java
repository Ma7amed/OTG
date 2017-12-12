package sample.model.DAO;

/**
 * Created by m80028770 on 8/17/2017.
 */
public class SQLQueryGenerator {

    public static String query_alm_3g_perTable(String tableName, String startDateString, String endDateString) {

        return "select " +
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
                "fmdb.." + tableName +
                " where " +
                "Id=22214 " +
                "and OccurTime  >=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + startDateString + "',101)) " +
                "and OccurTime  <=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + endDateString + "',101))";

    }

    public static String query_alm_3g_current() {

        return "select " +
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
                "fmdb..tbl_alm_log_2000000000" +
                " where " +
                "Id=22214 ";
//                "and OccurTime  >=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + startDateString + "',101)) " +
//                "and OccurTime  <=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + endDateString + "',101))";

    }



    public static String query_tablename_alarmlog(String startDateString, String endDateString) {

        return "select   TableName " +
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
                "and " +
                "MinOccurTime <=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + endDateString + "',101)) " + //-- endtime
                "or " +
                 "MinOccurTime>=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + startDateString + "',101)) " + // -- starttime
                "and " +
                 "MaxOccurTime<=Datediff(SECOND, '1970-01-01', CONVERT(CHAR(20),'" + endDateString + "',101)) " + // -- endtime
                ") " +
                "order by MinOccurTime desc,MaxOccurTime desc";

    }

    public static String query_avail_3g_daily(String dateString) {

        return "select CONVERT( CHAR( 10 ), r.StartTime, 101 ) 'startTime',\n" +
                "r.GranulityPeriod 'period'," +
                "o.NeName 'neName'," +
                "substring(o.ObjectMemName0,12,100) 'site'," +
                "r.Counter_67203853 'unAvailTime' " +
                "from  pmdb..tbl_Result_67109473_4 r " +
                "left join pmdb..tbl_ObjectInstance o on o.ObjectNo = r.ObjectNo " +
                //"where r.StartTime = CONVERT(CHAR(20),dateadd(dd,-1,getdate()),101) " +
                "where  r.StartTime  = CONVERT(CHAR(20),'" + dateString + "',101) " +
                "and o.ObjectTypeId in " +
                "(select distinct ObjTypeId from pmdb..systbl_FunctionSubSet where FunctionSubSetId=67109473)";

    }
}
