import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.model.DTO.Outage.OutageSummary_3G;
import sample.model.SiteUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static sun.security.krb5.Confounder.intValue;

/**
 * Created by m80028770 on 8/18/2017.
 */
public class OthersTestDrive {

    public static void main2(String[] args) {

        String x = decode("1c1f1d041d00000007", "topov");
        System.out.println(x);
    }


    public static String decode(String input, String key) {
        String tmpDecode = "";
        for (int i = 0; i < input.length() / 2; i++) {
            String tmpStr = input.substring(2 * i, 2 * (i + 1));
            char tmpChar = (char) Integer.parseInt(tmpStr, 16);
            for (int j = key.length() - 1; j >= 0; j--) {
                tmpChar = (char) (tmpChar ^ key.charAt(j));
            }
            tmpDecode = tmpDecode + (char) (tmpChar - '<' - i);
        }
        return tmpDecode;
    }


    public static void main(String[] args) {


        File file = new File("D:\\Work\\OSS\\Temp_Delete\\20170827\\OTG\\test.xlsx");


        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("OutageSummary");

        Row header = sheet1.createRow(0);

        // set headers
        int cl = 0;
        while (cl < OutageSummary_3G.HEADERS.length) {
            header.createCell(cl).setCellValue(OutageSummary_3G.HEADERS[cl]);
            cl++;
        }

        for (int i = 0; i < 4; i++) {
            Row row = sheet1.createRow(i + 1);


            cl = 0;
            row.createCell(cl++).setCellValue("test");

            for (int c = 0; c < row.getLastCellNum(); c++) {
               // row.getCell(c).setCellStyle(getCellStyle(wb));
            }


        }


        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
