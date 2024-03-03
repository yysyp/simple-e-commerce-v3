package pslab;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import ps.demo.common.*;
import ps.demo.common.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Code generator
 * template: tfsource
 */
@Slf4j
public class MyCodeGenerator {

    private static Properties properties;

    private static File contentmd5checkFile;
    private static Properties contentmd5check;

    public static void main(String[] args) throws Exception {
        Map<String, String> argsMap = MyArgsTool.assemberArgs(args);
        String tfsource = StringUtils.isBlank(argsMap.get("tfsource")) ? "tfsource" : argsMap.get("tfsource").trim();

        properties = ReadWriteTool.readProperties(new File(tfsource, "tf.properties"));
        File targetDir = new File(properties.getProperty("tftargetPath"));
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        String contentmd5checkPath = properties.getProperty("contentmd5check");
        contentmd5checkFile = new File(contentmd5checkPath);
        if (!contentmd5checkFile.exists()) {
            contentmd5checkFile.getParentFile().mkdirs();
            contentmd5checkFile.createNewFile();
        }
        contentmd5check = ReadWriteTool.readProperties(contentmd5checkFile);

        Collection<File> tfs = FileUtils.listFiles(new File(tfsource), new String[]{"tf"}, false);

        String encoding = properties.getProperty("encoding");
        if (StringUtils.isBlank(encoding)) {
            encoding = Charset.defaultCharset().name();
        }
        //String targetPath = properties.getProperty("tftargetPath");
        File header = new File(tfsource, "header_tf");
        File footer = new File(tfsource, "footer_tf");
        String headerContent = ReadWriteTool.readFileContent(header, encoding);
        String footerContent = ReadWriteTool.readFileContent(footer, encoding);
        Map propMap = new HashMap(properties);
        propMap.put("tfUtil", TfUtil.getInstance());
        String entityJson = properties.getProperty("entityJson");
        JSONObject jsonObject = new JSONObject(entityJson);
        propMap.put("entityJson", jsonObject);
        String uncEntityName = StringUtils.uncapitalize(jsonObject.getString("name"));
        String tableName = StringTool.toDbName(uncEntityName);
        propMap.put("tableName", tableName);
        String uriName = StringTool.toUriName(uncEntityName);
        propMap.put("uriName", uriName);
        for (File tf : tfs) {
            String fileName = tf.getName();
            String relativePath = properties.getProperty(fileName);
            if (StringUtils.isBlank(relativePath)) {
                log.info("MyCodeGenerator file: {} ignored as no relativePath in tf.properties for it", tf);
                continue;
            }
            try {
                relativePath = ThymeleafTool.processText(relativePath, propMap);
                StringBuffer content = new StringBuffer(headerContent);
                content.append(System.lineSeparator());
                content.append(ReadWriteTool.readFileContent(tf, encoding));
                content.append(System.lineSeparator());
                content.append(footerContent);
                String result = ThymeleafTool.processText(content.toString(), propMap);
                File absPath = new File(targetDir, relativePath);
                FileUtilTool.createDir(absPath.getParent());
                //ReadWriteTool.writeFileContent(absPath, result, encoding);
                fileCheckToOverwrite(absPath, result);
                ReadWriteTool.writeProperties(contentmd5checkFile, contentmd5check, false);
            } catch (Exception e) {
                log.error("MyCodeGenerator processing err, msg={}", e.getMessage(), e);
            }
        }

        log.info("MyCodeGenerator processing Done!");
    }

    private static void fileCheckToOverwrite(File file, String newContent) throws IOException {
        String key = file.getAbsolutePath();
        String savedMd5 = (String) contentmd5check.get(key);
        contentmd5check.setProperty(key, MD5Tool.getMD5(newContent+"".trim()));

        if (!file.exists()) {
            writeFileContent(newContent, file);
            return;
        }
        String contentMd5 = MD5Tool.getMD5(readFileContent(file)+"".trim());
        if (contentMd5.equals(savedMd5)) {
            writeFileContent(newContent, file);
            return;
        }

        //Not matching create _fix file
        File fixFile = new File(file.getAbsolutePath()+".kmfix");
        writeFileContent(newContent, fixFile);
    }

    private static String readFileContent(File file) throws IOException {
        if (StringUtils.isEmpty(properties.getProperty("encoding"))) {
            return FileUtils.readFileToString(file, Charset.defaultCharset());
        }
        return FileUtils.readFileToString(file, properties.getProperty("encoding"));
    }

    private static void writeFileContent(String content, File file) throws IOException {
        if (StringUtils.isEmpty(properties.getProperty("encoding"))) {
            FileUtils.writeStringToFile(file, content, Charset.defaultCharset());
        } else {
            FileUtils.writeStringToFile(file, content, properties.getProperty("encoding"));
        }
    }

}
