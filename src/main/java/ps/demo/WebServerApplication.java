package ps.demo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;
import ps.demo.config.ApiClientConfiguration;

@Slf4j
@SpringBootApplication
@MapperScan("ps.demo.repository")
@EnableCaching
@EnableAsync
public class WebServerApplication implements ApplicationRunner {

    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        //long freeMemory = Runtime.getRuntime().freeMemory();
        log.info("--->>maxMemory={}m, totalMemory={}m, usedMemory={}m",
                maxMemory / 1024 / 1024, totalMemory / 1024 / 1024,
                (maxMemory - totalMemory) / 1024 / 1024);
        log.info("System.getenv() = {}", System.getenv());
        SpringApplication.run(WebServerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationArguments is: {}", args);
    }

    @Bean
    public ApiClientConfiguration apiClientConfiguration(ConfigurableEnvironment environment) {
        return new ApiClientConfiguration(environment);
    }

}
