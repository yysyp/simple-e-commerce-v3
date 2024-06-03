package ps.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ps.demo.common.BaseResponse;
import ps.demo.common.StringDataResponse;
import ps.demo.dto.ApiClient;
import ps.demo.entity.Product;
import ps.demo.service.BusinessHandler;

import java.util.Map;

@Tag(name = "BusinessHandlerController", description = "BusinessHandlerController")
@Slf4j
@RestController
@RequestMapping("/bh")
public class BusinessHandlerController {

    @Autowired
    private Map<String, BusinessHandler> businessHandlerMap;
    @Autowired
    private ApplicationContext context;


    @Operation(summary = "Dynamic get handler base on the parameters")
    @GetMapping("/get")
    public BaseResponse processByType(@RequestParam(name = "type", defaultValue = "aaa") String type) {
        String traceId = MDC.get("traceId");
        log.info("This is the traceId either passed in from frontend traceparent or generated by io.micrometer");


        //Solution A:
        BusinessHandler businessHandler = businessHandlerMap.get(type+"BusinessHandler");
        businessHandler.process(type + System.currentTimeMillis());

        //Solution B: dynamic register bean
        ApiClient apiClient = (ApiClient) context.getBean("apiClient_clienta");
        log.info("--->>ApiClient = {}", apiClient);


        return StringDataResponse.successWithData(apiClient);
    }

}