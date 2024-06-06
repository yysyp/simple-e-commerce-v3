package ps.demo.validator;

import cn.hutool.core.lang.Console;
import org.junit.jupiter.api.Test;
import ps.demo.dto.UploadDownloadExcelDto;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JakartaValidatorTest {

    @Test
    void validate() {
        JakartaValidator<UploadDownloadExcelDto> downloadExcelDtoJakartaValidator = new JakartaValidator<>();

        UploadDownloadExcelDto uploadDownloadExcelDto = UploadDownloadExcelDto.builder()
                .firstName("a ")
                .lastName("s")
                //.age(-1)
                .score(new BigDecimal("123.12")) //###.##
                .build();
        List<String> msg = downloadExcelDtoJakartaValidator.validate(uploadDownloadExcelDto);

        Console.log("msg : {}", msg);
    }
}