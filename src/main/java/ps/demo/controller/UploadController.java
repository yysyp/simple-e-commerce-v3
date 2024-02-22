package ps.demo.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ps.demo.common.ClientErrorException;
import ps.demo.common.CodeEnum;
import ps.demo.common.ServerErrorException;
import ps.demo.dto.UploadHi;


import jakarta.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunpeng.song on 5/15/2020.
 */
@Tag(name = "This is a file upload controller")
@Slf4j
@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @PostMapping(value = "/hi", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String hi(@RequestBody UploadHi uploadHi) {
        log.info("--->> uploadHi.index={}, num={}", uploadHi.getIndex(), uploadHi.getNum());
        try {
            Thread.sleep(uploadHi.getNum()*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int size = uploadHi.getFileStr().getBytes(StandardCharsets.UTF_8).length;
        List<Object> multipleStr = new ArrayList<>();
        for (int i = 0; i < uploadHi.getNum(); i++) {
            multipleStr.add(new byte[size]);
        }
        return LocalDateTime.now().toString() + uploadHi.getLength();
    }

    @Operation(summary = "File upload with multipart form data")
    @PostMapping(value = "/file", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam(value = "key", required = false) String key,
                                   HttpServletRequest req) {
        if (file == null) {
            throw new ClientErrorException(CodeEnum.BAD_REQUEST);
        }
        try {
            String fileName = file.getOriginalFilename();
            if (StringUtils.isEmpty(key)) {
                key = fileName;
            }

            String uploadFolder = System.getProperty("user.dir") + "/upload-folder/";
            String destFileName = uploadFolder + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + File.separator
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmssSSS")) + fileName;

            File destFile = Paths.get(destFileName).toFile();
            log.info("--------->>File uploaded to file={}", destFile);
            destFile.getParentFile().mkdirs();

            //Use inputStream/outputStream to read write file.
            try (
                    BufferedInputStream bi = new BufferedInputStream(file.getInputStream());
                    OutputStream fout = new FileOutputStream(destFile);
                    BufferedOutputStream bo = new BufferedOutputStream(fout);
//                    InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
//                    BufferedReader br = new BufferedReader(inputStreamReader);
//                    FileWriter fileWriter = new FileWriter(destFile);
                    ) {

                int len = -1;
                byte[] buf = new byte[1024];
                while ((len = bi.read(buf)) != -1) {
                    bo.write(buf, 0, len);
                }

            }

            //Or use file transferTo method to transfer file.
            //file.transferTo(destFile);

        } catch (Exception e) {
            throw new ServerErrorException(CodeEnum.INTERNAL_SERVER_ERROR, e);
        }
        return "SUCCESS";
    }

//    @GetMapping
//    public ModelAndView navigateToUploadPage(Model model) {
//        model.addAttribute("title", "Upload File.");
//        return new ModelAndView("upload/fileupload", "model", model);
//    }
//
//    @PostMapping("/file")
//    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(value = "key", required = false) String key,
//                                   HttpServletRequest req, Model model) {
//        if (file == null) {
//            throw new BadRequestException(CodeEnum.BAD_REQUEST, false, "File is required.");
//        }
//        try {
//            String fileName = file.getOriginalFilename();
//            if (StringUtils.isEmpty(key)) {
//                key = fileName;
//            }
//
//            String uploadFolder = System.getProperty("user.dir") + "/upload-folder/";
//            String destFileName = uploadFolder + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + File.separator
//                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmssSSS")) + fileName;
//
//            File destFile = Paths.get(destFileName).toFile();
//            log.info("--------->>File uploaded to file={}", destFile);
//            destFile.getParentFile().mkdirs();
//            file.transferTo(destFile);
//
//        } catch (Exception e) {
//            throw new ServerApiException(CodeEnum.INTERNAL_SERVER_ERROR, true, "--------->>File upload failed, ex:" + e.getMessage());
//        }
//        model.addAttribute("title", "Upload Success!");
//        return new ModelAndView("upload/uploadsuccess", "model", model);
//    }

}
