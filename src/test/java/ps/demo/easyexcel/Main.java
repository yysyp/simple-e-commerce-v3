package ps.demo.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import ps.demo.common.RegexTool;
import ps.demo.common.StringTool;

import java.io.File;
import java.util.*;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        String filename = "C:\\Users\\Dell\\myexcel002.xlsx";

//        EasyExcel.write(filename, DemoData.class).sheet("Data list").needHead(true).doWrite(Main.getData());
//
//        List<DemoData> list = new ArrayList<>();
//        EasyExcel.read(filename, DemoData.class, new ReadListener<DemoData>() {
//            @Override
//            public void invoke(DemoData o, AnalysisContext analysisContext) {
//                list.add(o);
//            }
//
//            @Override
//            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//                //done!
//            }
//        }).sheet().headRowNumber(1).doRead();
//
//        System.out.println("-->(1)Read excel result=" + list);

        List<LinkedHashMap<String, Object>> dataList = new ArrayList<>();
        EasyExcel.read(filename, new AnalysisEventListener<Map<String, Object>>() {
            private Map<Integer, String> headMap;

            @Override
            public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                this.headMap = headMap;
            }

            @Override
            public void invoke(Map<String, Object> rowData, AnalysisContext analysisContext) {
                LinkedHashMap<String, Object> dataMap = new LinkedHashMap<>();
                for (int i = 0, n = rowData.size(); i < n; i++) {
                    //String key = RegexTool.removeSymbols(""+headMap.get(i))+"_"+getExcelColumnName(i+1);
                    String key = StringTool.excelNumToCol(i + 1);
                    Object value = rowData.get(i);
//                    int keyPrefix = 0;
//                    String key1 = key;
//                    while (dataMap.containsKey(key1)) {
//                        keyPrefix++;
//                        key1 = key+keyPrefix;
//                    }
                    dataMap.put(key, value);
                }
                dataList.add(dataMap);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().headRowNumber(1).doRead();

        System.out.println("------------------------------------");
        System.out.println("-->(2)Read excel dataList.0=" + dataList.get(0));
        System.out.println("-->(2)Read excel dataList.size=" + dataList.size());
        System.out.println("-->(2)Read excel dataList=" + dataList);
        //Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Gson gson = new Gson();
        String json1 = gson.toJson(dataList);
        System.out.println("-->(2)Read excel json1=" + json1);

        //FileUtils.delete(new File(filename));
    }

    public static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("Test" + i);
            demoData.setAge(RandomUtils.nextInt(10, 90));
            list.add(demoData);
        }
        return list;
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
