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




    public static void main(String[] args) {

        File f = new File("D:/Work/OSS/Temp_Delete/20171212/OTG/a/b/c/d/e/f");
        if(!f.exists()) {
            System.out.println("no");
            f.mkdirs();
        }else {
            System.out.println("yes");
        }
    }
}
