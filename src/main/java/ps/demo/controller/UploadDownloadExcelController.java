package ps.demo.controller;

import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ps.demo.common.CodeEnum;
import ps.demo.common.ServerErrorException;
import ps.demo.dto.UploadDownloadExcelDto;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Tag(name = "This is an Excel upload & download controller")
@Slf4j
@RestController
@RequestMapping("/api/excel")
public class UploadDownloadExcelController {


    @Operation(summary = "Sample Excel file download")
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


    public String uploadExcel() {

        return ";";
    }

}
