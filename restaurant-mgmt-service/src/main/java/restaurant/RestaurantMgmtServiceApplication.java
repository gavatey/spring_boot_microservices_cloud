package restaurant;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RestaurantMgmtServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(RestaurantMgmtServiceApplication.class, args);
    }
}

