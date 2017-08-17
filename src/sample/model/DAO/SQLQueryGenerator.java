package sample.model.DAO;

/**
 * Created by m80028770 on 8/17/2017.
 */
public class SQLQueryGenerator {

    public static String sql_query_3g_alm_perTable_get(String tableName, String startDateString, String endDateString) {

        String sql_query_3g_alm_perTable = "select " +
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

        return sql_query_3g_alm_perTable;

    }


    public static String sql_query_tablename_alarmlog_get(String startDateString,String endDateString) {

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
                ") " +
                "order by MinOccurTime desc,MaxOccurTime desc";

        return sql_query_tablename_alarmlog;
    }
}
