package ps.demo.quicktest;

import org.apache.commons.lang3.RandomStringUtils;
import ps.demo.common.FileUtilTool;
import ps.demo.common.ReadWriteTool;

import java.io.File;

public class RandomGenerateBigFile {

    public static void main(String [] args) {

        File file = FileUtilTool.getFileTsInHomeDir("BigFile.txt");
        for (int i = 0; i < 5*1024; i++) {
            for (int r = 0; r < 8; r++) {
                String k1 = RandomStringUtils.randomAlphabetic(128) + "\n";
                ReadWriteTool.writeFileContent(file, k1, "UTF-8", true);
            }
        }
        System.out.println("File generated file="+file);
    }
}
