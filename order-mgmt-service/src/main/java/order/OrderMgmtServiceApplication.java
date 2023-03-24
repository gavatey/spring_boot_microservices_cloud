package order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class OrderMgmtServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderMgmtServiceApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate() {
        System.out.println("test");
        System.out.println("test");
        return new RestTemplate();
    }

}
