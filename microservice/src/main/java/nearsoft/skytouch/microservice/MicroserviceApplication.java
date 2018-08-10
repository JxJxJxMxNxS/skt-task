package nearsoft.skytouch.microservice;

import nearsoft.skytouch.microservice.subscriber.Subscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = {"nearsoft.skytouch"})
public class MicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceApplication.class, args);
    }

    @Bean
    public Subscriber subscriber() {
        return new Subscriber();
    }
}
