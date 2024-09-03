package th.mfu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMicroserviceApp {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMicroserviceApp.class, args);
    }
}