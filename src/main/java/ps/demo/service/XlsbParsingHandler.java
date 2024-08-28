package ps.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.binary.XSSFBSharedStringsTable;
import org.apache.poi.xssf.binary.XSSFBSheetHandler;
import org.apache.poi.xssf.binary.XSSFBStylesTable;
import org.apache.poi.xssf.eventusermodel.XSSFBReader;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import ps.demo.common.StringTool;
import ps.demo.dto.XlsbModelA;
import ps.demo.excel.CustomizedDataFormatter;
import ps.demo.excel.XlsbXSSFSheetContentHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
public class XlsbParsingHandler implements ProcessingHandler<XlsbModelA> {

    @Override
    public List<String> validate(List<XlsbModelA> data) {
        return null;
    }

    @Override
    public List<XlsbModelA> parse(InputStream input) throws IOException {
        log.info("This is xlsb parsing");
        try (OPCPackage opcPackage = OPCPackage.open(input, true);) {
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

            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
