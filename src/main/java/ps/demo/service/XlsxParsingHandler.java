package ps.demo.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ps.demo.dto.XlsbModelA;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class XlsxParsingHandler implements ProcessingHandler<XlsbModelA> {

    @Override
    public List<String> validate(List<XlsbModelA> data) {
        return null;
    }

    @Override
    public List<XlsbModelA> parse(InputStream input) {
        List<XlsbModelA> readList = new ArrayList<>();

        int headerRow = 1;

        try {
            EasyExcel.read(input, XlsbModelA.class, new ReadListener<XlsbModelA>() {
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

            return readList;
        } finally {
            IOUtils.closeQuietly(input);
        }
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

}
