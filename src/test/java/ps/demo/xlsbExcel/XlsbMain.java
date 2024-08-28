package ps.demo.xlsbExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.binary.XSSFBSharedStringsTable;
import org.apache.poi.xssf.binary.XSSFBSheetHandler;
import org.apache.poi.xssf.binary.XSSFBStylesTable;
import org.apache.poi.xssf.eventusermodel.XSSFBReader;
import ps.demo.common.StringTool;
import ps.demo.dto.XlsbModelA;
import ps.demo.excel.CustomizedDataFormatter;
import ps.demo.excel.XlsbXSSFSheetContentHandler;
import ps.demo.validator.JakartaValidator;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class XlsbMain {

    @SneakyThrows
    public static void main(String[] args) {

        String str = """
                3
                42|3,23.276564 564647
                485 87755
                """ ;
        String result = str.replaceAll(",|\\s", "");
        System.out.println("result=" + result);

        File xlsb = new File("C:\\Users\\yysyp\\Desktop\\tmp\\mytest.xlsb");
        //File xlsxFile = new File("mytest-xlsb-"+System.currentTimeMillis()+".xlsx");

        try (OPCPackage opcPackage = OPCPackage.open(xlsb, PackageAccess.READ);) {
            XSSFBReader xssfbReader = new XSSFBReader(opcPackage);
            XSSFBSharedStringsTable xssfbSharedStringsTable = new XSSFBSharedStringsTable(opcPackage);
            XSSFBStylesTable xssfbStylesTable = xssfbReader.getXSSFBStylesTable();
            DataFormatter dataFormatter = new CustomizedDataFormatter();
            XSSFBReader.SheetIterator it = (XSSFBReader.SheetIterator)xssfbReader.getSheetsData();

            XlsbXSSFSheetContentHandler<XlsbModelA> handler = new XlsbXSSFSheetContentHandler<XlsbModelA>(XlsbModelA.class, 1);
            try(InputStream is = it.next();) {
                String sheetName = it.getSheetName();
                XSSFBSheetHandler sheetHandler = new XSSFBSheetHandler(is, xssfbStylesTable, it.getXSSFBSheetComments(), xssfbSharedStringsTable
                        , handler, dataFormatter, false);
                sheetHandler.parse();
            }
            List<XlsbModelA> data = handler.getList();

            //log.info("data={}", data);
            System.out.println("xlsb file result:");
            StringTool.printOut(data);
        }


        //file
        File xlsx = new File("C:\\Users\\yysyp\\Desktop\\tmp\\mytest.xlsx");

        //Read the Excel file to List<DemoData>
        List<XlsbModelA> readList = new ArrayList<>();

        int headerRow = 1;
        EasyExcel.read(xlsx, XlsbModelA.class, new ReadListener<XlsbModelA>() {
            int r = headerRow;
            @Override
            public void invoke(XlsbModelA o, AnalysisContext analysisContext) {
                r++;
                if (lineNull(o)) {
                    return;
                }
                o.setRowNum(r);
                readList.add(o);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                //done!
            }
        }).charset(StandardCharsets.UTF_8).excelType(ExcelTypeEnum.XLSX).sheet(0).headRowNumber(headerRow).doRead();
//
        System.out.println("xlsx file result:");
        StringTool.printOut(readList);

        //Validation...
        JakartaValidator<XlsbModelA> xlsbModelAJakartaValidator = new JakartaValidator<>();

        List<String> validationMsgs = new ArrayList<>();
        readList.forEach( dto -> {
            List<String> msg = xlsbModelAJakartaValidator.validate(dto);
            validationMsgs.addAll(msg);
        });

        System.out.println("validationMsgs result");
        StringTool.printOut(validationMsgs);

    }

    public static <T> boolean lineNull(T line) {
        if (line instanceof String) {
            return StringUtils.isEmpty((String)line);
        }
        try {
            Set<Field> fieldSet = Arrays.stream(line.getClass().getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(ExcelProperty.class)).collect(Collectors.toSet());
            for (Field field : fieldSet) {
                field.setAccessible(true);
                if(field.get(line) != null) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String getExcelColumnName(int col) {
        StringBuilder columnName = new StringBuilder();
        while (col > 0) {
            int rem = col % 26;
            if (rem == 0) {
                columnName.append("Z");
                col = (col / 26) - 1;
            } else {
                columnName.append((char)((rem - 1) + 'A'));
                col = col / 26;
            }
        }
        return columnName.reverse().toString();
    }


}