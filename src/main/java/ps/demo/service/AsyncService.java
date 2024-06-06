package ps.demo.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import ps.demo.entity.Product;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Service
public class AsyncService {

    @Async("taskExecutor")
    @SneakyThrows
    public CompletableFuture<String> asyncronizedCall(Long sleep) {
        log.info("AsyncService to sleep = {} seconds", sleep);
        Thread.sleep(sleep*1000);
        String random = RandomStringUtils.randomAlphanumeric(25);
        log.info("AsyncService return the random string={}, after sleep={}", random, sleep);
        //return AsyncResult.forValue(random);
        return CompletableFuture.completedFuture(random);
    }

    @Async("taskExecutor")
    @SneakyThrows
    public CompletableFuture<String> asyncronizedCall2(Long sleep) {
        try {
            log.info("AsyncService call2 to sleep = {} seconds", sleep);
            if (sleep == 999) {
                throw new RuntimeException("Not allow to sleep 999");
            }
            Thread.sleep(sleep * 1000);
            String random = RandomStringUtils.randomAlphanumeric(10);
            log.info("AsyncService call2 return the random string={}, after sleep={}", random, sleep);
        } catch (Exception ex) {
            log.info("This is an asynchronous method, so exception will not be able to throw out, so have to capture in here", ex);
            return CompletableFuture.failedFuture(ex);
        }
        //return AsyncResult.forValue("string1")
        return new CompletableFuture<>();
    }


}
