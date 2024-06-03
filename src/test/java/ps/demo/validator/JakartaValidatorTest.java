package ps.demo.validator;

import cn.hutool.core.lang.Console;
import org.junit.jupiter.api.Test;
import ps.demo.dto.UploadDownloadExcelDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JakartaValidatorTest {

    @Test
    void validate() {
        JakartaValidator<UploadDownloadExcelDto> downloadExcelDtoJakartaValidator = new JakartaValidator<>();

        UploadDownloadExcelDto uploadDownloadExcelDto = UploadDownloadExcelDto.builder()
                .firstName(" ")
                .lastName("")
                .age(-1).build();
        List<String> msg = downloadExcelDtoJakartaValidator.validate(uploadDownloadExcelDto);

        Console.log("msg : {}", msg);
    }
}