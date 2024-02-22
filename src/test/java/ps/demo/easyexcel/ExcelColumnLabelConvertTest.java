package ps.demo.easyexcel;

import org.apache.commons.lang3.StringUtils;

import static ps.demo.common.StringTool.excelColToNum;
import static ps.demo.common.StringTool.excelNumToCol;

public class ExcelColumnLabelConvertTest {

    public static void main(String[] args) {

        int index = 0;
        String name = "";

        name = getExcelColumnName(index);
        index = getExcelColumnIndex(name);
        System.out.println("name="+name+", index="+index);

        index = 1;
        name = getExcelColumnName(index);
        index = getExcelColumnIndex(name);
        System.out.println("name="+name+", index="+index);

        index = 2;
        name = getExcelColumnName(index);
        index = getExcelColumnIndex(name);
        System.out.println("name="+name+", index="+index);

        index = 26;
        name = getExcelColumnName(index);
        index = getExcelColumnIndex(name);
        System.out.println("name="+name+", index="+index);

        index = 27;
        name = getExcelColumnName(index);
        index = getExcelColumnIndex(name);
        System.out.println("name="+name+", index="+index);

        index = 51;
        name = getExcelColumnName(index);
        index = getExcelColumnIndex(name);
        System.out.println("name="+name+", index="+index);

        index = 52;
        name = getExcelColumnName(index);
        index = getExcelColumnIndex(name);
        System.out.println("name="+name+", index="+index);

        index = 53;
        name = getExcelColumnName(index);
        index = getExcelColumnIndex(name);
        System.out.println("name="+name+", index="+index);

        System.out.println("---------------------------------");
        index = 0;
        name = excelNumToCol(index);
        index = excelColToNum(name);
        System.out.println("name="+name+", index="+index);

        index = 1;
        name = excelNumToCol(index);
        index = excelColToNum(name);
        System.out.println("name="+name+", index="+index);

        index = 2;
        name = excelNumToCol(index);
        index = excelColToNum(name);
        System.out.println("name="+name+", index="+index);

        index = 26;
        name = excelNumToCol(index);
        index = excelColToNum(name);
        System.out.println("name="+name+", index="+index);

        index = 27;
        name = excelNumToCol(index);
        index = excelColToNum(name);
        System.out.println("name="+name+", index="+index);

        index = 51;
        name = excelNumToCol(index);
        index = excelColToNum(name);
        System.out.println("name="+name+", index="+index);

        index = 52;
        name = excelNumToCol(index);
        index = excelColToNum(name);
        System.out.println("name="+name+", index="+index);

        index = 53;
        name = excelNumToCol(index);
        index = excelColToNum(name);
        System.out.println("name="+name+", index="+index);

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

    public static int getExcelColumnIndex(String col) {
        int number = 0;
        if (StringUtils.isBlank(col)) {
            return number;
        }
        col = col.toUpperCase();
        char[] chars = col.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            number = number * 26 + (chars[i] - ('A' - 1));
        }
        return number;
    }

//    /**
//     * A - 1, B - 2, .. Z - 26, AA - 27
//     * @param name
//     * @return
//     */
//    public static int excelexcelColToNum(String name) {
//        int number = 0;
//        for (int i = 0; i < name.length(); i++) {
//            number = number * 26 + (name.charAt(i) - ('A' - 1));
//        }
//        return number;
//    }
//
//    /**
//     * 1 - A, 2 - B, .. 26 - Z, 27 - AA
//     * @param number
//     * @return
//     */
//    public static String excelexcelNumToCol(int number) {
//        StringBuilder sb = new StringBuilder();
//        while (number-- > 0) {
//            sb.append((char)('A' + (number % 26)));
//            number /= 26;
//        }
//        return sb.reverse().toString();
//    }
}
