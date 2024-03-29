package ps.demo.protobuf.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ps.demo.common.BaseController;
import ps.demo.common.BaseResponse;
import ps.demo.common.StringDataResponse;
import ps.demo.protobuf.model.Person;
import ps.demo.service.AsyncService;
import ps.demo.service.CacheService;

@Slf4j
@RestController
@RequestMapping("/protobuf-person")
public class ProtoBufPersonController extends BaseController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/get")
    public BaseResponse findPerson() {


        return StringDataResponse.successWithData(Person.newBuilder()
                .setId(10000)
                .setEmail("testprotobuf@aa.com")
                .setName("xiaohaha")
//                .setNickname(0, "xiao")
//                .setNickname(1, "pi")
                .build());
    }




}
