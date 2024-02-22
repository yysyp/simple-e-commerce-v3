package ps.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ps.demo.common.BaseResponse;
import ps.demo.common.GzipTool;
import ps.demo.common.BaseController;
import ps.demo.dto.MyBook;
import ps.demo.service.JdbcTemplateService;

@Slf4j
@RestController
@RequestMapping("/jdbc")
public class JdbcTemplateController extends BaseController {

    @Autowired
    private JdbcTemplateService jdbcTemplateService;

    @Operation(summary = "JdbcTemplate snd database to create table")
    @PostMapping("/create-table")
    public BaseResponse createTable(@RequestBody MyBook myBook) {
        String longStr = "Begin bla bla hello this is a random string what ever ... the END";
        byte[] originalBytes = longStr.getBytes();
        myBook.setMyByte(GzipTool.compress(originalBytes));
        Long key = jdbcTemplateService.createTable(myBook);
        MyBook retrivedBook = jdbcTemplateService.findById(key);
        myBook.setId(key);
        byte[] retrivedBytes = GzipTool.uncompress(retrivedBook.getMyByte());
        log.info("originalBytes.eq originalBytes={}", new String(originalBytes).equals(new String(retrivedBytes)));
        log.info("JdbcTemplateService created table and insert data id={}", key);
        log.info("To test update content...");
        myBook.setTitle("new title");
        myBook.setMyByte("Hello world".getBytes());
        int updated = jdbcTemplateService.updateById(myBook);
        log.info("Updated ={}", updated);
        myBook = jdbcTemplateService.findById(myBook.getId());
        log.info("Updated myBook={}", myBook);
        log.info("To test delete");
        int deleted = jdbcTemplateService.deleteById(myBook.getId());
        log.info("Deleted ={}", deleted);
        return BaseResponse.success();
    }



}
