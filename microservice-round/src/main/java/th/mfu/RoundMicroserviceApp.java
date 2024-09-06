package th.mfu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "th.mfu.client")
public class RoundMicroserviceApp {
    public static void main(String[] args) {
        SpringApplication.run(RoundMicroserviceApp.class, args);
    }
}