package ps.demo.quicktest;

import lombok.SneakyThrows;
import ps.demo.common.GzipTool;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class GzipString {

    public static void main(String[] args) {

        String longStr =
                "\uD83D\uDE0A\uD83D\uDE02\uD83D\uDC95\uD83D\uDE01\uD83D\uDC4D\uD83D\uDE0D\uD83D" +
                "\uDE4C\uD83D\uDE4C\uD83D\uDE01\uD83D\uDE01\uD83D\uDE18\uD83D\uDE0D\uD83D" +
                "\uDE0D\uD83D\uDE18\uD83E\uDD37\u200D\uD83E\uDD37\u200D\uD83D\uDC74\uD83D" +
                "\uDC69\u200D\uD83E\uDDB0\uD83D\uDC69\u200D\uD83E\uDDB0\uD83D\uDC69\u200D\uD83E" +
                "\uDDB0\uD83E\uDDE7\uD83C\uDF8D\uD83C\uDF83\uD83C\uDF83\uD83C\uDF80\uD83C\uDF80" +
                "\uD83E\uDD50\uD83E\uDD57\uD83E\uDD6F\uD83E\uDD56\uD83E\uDD56\uD83E\uDD56\uD83E" +
                "\uDD56\uD83C\uDF54\uD83E\uDD5E\uD83E\uDED3\uD83C\uDF2F\uD83D\uDE96\uD83D\uDE96" +
                "\uD83D\uDE8E\uD83D\uDE8E\uD83D\uDE91\uD83D\uDC94qerqlqkrqwlkrjqw;lkrqwkrlqkrjwq" +
                "qerqerwerqwrqwrwrr3j2lksldl2345pfspokowjerlakrlwqkrq;lk34jq;wlkrjq;3k;q3k44k2wr";
        //longStr = ReadWriteTool.readFileContent(new File("C:\\Users\\Dell\\2023-10-18_210654-BigFile.txt"));
        String compressed = compress(longStr);

        System.out.println("original length="+longStr.length()+"\tcompressed length="+compressed.length());
        System.out.println("-->is equal="+longStr.equals(uncompress(compressed)));

        byte[] bytes = GzipTool.compress(longStr.getBytes());
        byte[] bytes2 = GzipTool.uncompress(bytes);
        System.out.println("-->is equal="+longStr.equals(new String(bytes2)));

        String compressed2 = compress2(longStr);

        System.out.println("original length="+longStr.length()+"\tcompressed2 length="+compressed2.length());
        System.out.println("-->is equal="+longStr.equals(uncompress2(compressed2)));


    }

    @SneakyThrows
    public static String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return new String(Base64.getEncoder().encode(GzipTool.compress(str.getBytes(StandardCharsets.UTF_8))));
    }

    public static String uncompress(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }
        return new String(GzipTool.uncompress(Base64.getDecoder().decode(compressedStr.getBytes(StandardCharsets.UTF_8))));
    }

    @SneakyThrows
    public static String compress2(String str) {
        return new String(GzipTool.compress(str.getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1);
    }

    @SneakyThrows
    public static String uncompress2(String compressedStr) {
        return new String(GzipTool.uncompress(compressedStr.getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1);
    }
}
