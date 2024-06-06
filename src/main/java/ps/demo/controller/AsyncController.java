package ps.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import ps.demo.common.BaseController;
import ps.demo.common.BaseResponse;
import ps.demo.common.StringDataResponse;
import ps.demo.entity.Product;
import ps.demo.service.AsyncService;
import ps.demo.service.CacheService;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Tag(name = "AsyncController", description = "AsyncController")
@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController extends BaseController {


    @Autowired
    private AsyncService asyncService;

    @SneakyThrows
    @Operation(summary = "Asynchronous demo")
    @GetMapping("/call")
    public BaseResponse asyncCall(@RequestParam(name = "sleepSeconds") Long sleepSeconds,
                                  @RequestParam(name = "random") Boolean random) {


        String traceId = MDC.get("traceId");
//        CompletableFuture<String> task1 = asyncService.asyncronizedCall(sleepSeconds);
//        CompletableFuture<String> task2 = asyncService.asyncronizedCall(sleepSeconds);
//        CompletableFuture<String> task3 = asyncService.asyncronizedCall(sleepSeconds);
//        CompletableFuture<String> task4 = asyncService.asyncronizedCall(sleepSeconds);
//
//        if (wait) {
//            CompletableFuture.allOf(task1, task2, task3, task4).join();
//            String result = task1.get();
//            return StringDataResponse.successWithData(result);
//        }
//        // str.get();
        Map<String, String> contextMap = MDC.getCopyOfContextMap();

        CompletableFuture<CompletableFuture<String>> completableFuture0 = CompletableFuture
                .supplyAsync(() -> {
                    if (!CollectionUtils.isEmpty(contextMap)) {
                        MDC.setContextMap(contextMap);
                    }
                    try {
                        String uuid = UUID.randomUUID().toString();
                        log.info("Step1 ... save file, uuid={}", uuid);

                        if (random && RandomUtils.nextBoolean()) {
                            throw new RuntimeException("Random error1...");
                        }
                        return uuid;
                    } finally {
                        MDC.clear();
                    }
                })
                .thenApply(uuid -> {
                    if (!CollectionUtils.isEmpty(contextMap)) {
                        MDC.setContextMap(contextMap);
                    }
                    try {
                        log.info("Step2 ... processing file, uuid={}", uuid);
                        if (random && RandomUtils.nextBoolean()) {
                            throw new RuntimeException("Random error2...");
                        }
                        return asyncService.asyncronizedCall2(sleepSeconds);
                    } finally {
                        MDC.clear();
                    }
                })
                .exceptionally((ex) -> {
                    /*
                    Here can catch the Random error1 and Random error2, but not able to capture the error
                    from asyncService.asyncronizedCall2 even it throws out because it's an async method
                     */
                    log.error("===>>computeFuture0 error.", ex);
                    return CompletableFuture.failedFuture(ex);
                });


        //get means run completableFuture0, so actually here no need to use completableFuture0
        final CompletableFuture<String> completableFuture1 = completableFuture0.get();

        completableFuture1.exceptionally((ex) -> {
            log.error("--->>computerFuture1 error from service async method, ex={}", ex.getMessage(), ex);
            return null;
        });



        return StringDataResponse.successWithData("You don't wait. traceId=" + traceId);
    }

}
