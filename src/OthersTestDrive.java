import jdk.internal.org.xml.sax.ContentHandler;
import jdk.internal.org.xml.sax.InputSource;
import jdk.internal.org.xml.sax.SAXException;
import jdk.internal.org.xml.sax.XMLReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.helpers.XMLReaderFactory;
import sample.model.DAO.U2000_DB;
import sample.model.DTO.Avail.Result_Avail3G;
import sample.model.DTO.DailyReport.Daily3G;
import sample.model.DTO.Outage.OutageSummary_3G;
import sample.model.ExcelReader;
import sample.model.SiteUtil;

import java.io.*;
import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.apache.logging.log4j.core.impl.ThrowableFormatOptions.FILE_NAME;
import static sun.security.krb5.Confounder.intValue;

/**
 * Created by m80028770 on 8/18/2017.
 */
public class OthersTestDrive {

    private static final String FILE_NAME = "D:\\Work\\OSS\\Temp_Delete\\20171001\\OTG\\Huawei NOC Daily Report  01-Oct-2017 V1.xlsx";



    public static void main(String[] args) {

//        String db_ip;
//        String db_port;
//        String db_username;
//        String db_password;
        Connection con=null;
        Statement stmt;

        stmt= null;


        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
//            con = DriverManager.getConnection("jdbc:sybase:Tds:10.76.2.55:4100", "sa", "emsems");
            con = DriverManager.getConnection("jdbc:sybase:Tds:10.74.123.2:4110/POS1?charset=utf8","root","Fmos_001");
            System.out.println("connect: " + con);

            // Query

            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery( "select count(*)  from status");
            while ( rs.next( ) ) {
                System.out.println( "Count of TTs in POS1: " + rs.getString(1) );
            }
            // ResultSet rs = stmt.executeQuery(sql_select_solly_tt);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
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
}
