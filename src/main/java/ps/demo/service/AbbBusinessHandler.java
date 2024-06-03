package ps.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AbbBusinessHandler implements BusinessHandler {
    @Override
    public void process(String input) {
        log.info("This is processing input = {}", input);
    }

}
