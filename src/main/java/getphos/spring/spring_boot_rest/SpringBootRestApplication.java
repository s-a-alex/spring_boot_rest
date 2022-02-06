package getphos.spring.spring_boot_rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages="getphos.spring.spring_boot_rest")
@EntityScan("getphos.spring.spring_boot_rest.entity")
public class SpringBootRestApplication {

    private static Logger logger = LoggerFactory.getLogger(SpringBootRestApplication.class);

    public static void main(String... args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootRestApplication.class, args);
        assert (ctx != null);
        logger.info("Application started...");

        System.in.read();
        ctx.close();
    }
}
