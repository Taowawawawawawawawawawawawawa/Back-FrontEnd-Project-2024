package th.mfu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableDiscoveryClient
public class CustomerMicroserviceApp {
    public static void main(String[] args) {
        SpringApplication.run(CustomerMicroserviceApp.class, args);
    }
}