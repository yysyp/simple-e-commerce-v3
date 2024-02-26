package ps.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ps.demo.common.BaseResponse;
import ps.demo.common.ClientErrorException;
import ps.demo.common.CodeEnum;
import ps.demo.common.ServerErrorException;
import ps.demo.dto.UploadDownloadExcelDto;
import ps.demo.service.UploadDownloadExcelService;

import java.awt.event.InputEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Tag(name = "This is an Excel upload & download controller")
@Slf4j
@RestController
@RequestMapping("/api/excel")
public class UploadDownloadExcelController {

    @Autowired
    private UploadDownloadExcelService uploadDownloadExcelService;

    @Operation(summary = "Download a sample Excel file")
    @GetMapping("/download")
    public String downloadExcel(HttpServletRequest request, HttpServletResponse response) {

        try {
            response.setContentType("application/octet-stream");
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=sampleExcel.xlsx");

            OutputStream out = response.getOutputStream();
            EasyExcel.write(out, UploadDownloadExcelDto.class).sheet().needHead(true).doWrite(() -> {
                ArrayList list = new ArrayList<>();
                list.add(UploadDownloadExcelDto.builder().firstName("f1").lastName("l1").age(12).score(new BigDecimal("23.3")).build());
                list.add(UploadDownloadExcelDto.builder().firstName("f2").lastName("l2").age(22).score(new BigDecimal("32.3")).build());
                list.add(UploadDownloadExcelDto.builder().firstName("f3").lastName("l3").age(23).score(new BigDecimal("4.3")).build());
                return list;
            });
            out.flush();
        } catch (IOException e) {
            throw new ServerErrorException(CodeEnum.INTERNAL_SERVER_ERROR, e);
            //e.printStackTrace();
        }
        return null;

    }


    @Operation(summary = "Upload Excel file for parsing and store into database")
    @PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseBody
    public BaseResponse uploadExcel(@RequestParam("file") MultipartFile file,
                              HttpServletRequest request) {
        if (file == null) {
            throw new ClientErrorException(CodeEnum.BAD_REQUEST);
        }
        List<UploadDownloadExcelDto> list = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             BufferedInputStream bis = new BufferedInputStream(inputStream);
        ) {
            EasyExcel.read(bis, UploadDownloadExcelDto.class, new ReadListener<UploadDownloadExcelDto>() {
                @Override
                public void invoke(UploadDownloadExcelDto o, AnalysisContext analysisContext) {
                    list.add(o);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                }
            }).sheet().headRowNumber(1).doRead();

            uploadDownloadExcelService.saveExcelDtoList(list);
        } catch (IOException e) {
            //e.printStackTrace();
            throw new ServerErrorException(CodeEnum.INTERNAL_SERVER_ERROR, e);
        }
        return BaseResponse.success();
    }

}
