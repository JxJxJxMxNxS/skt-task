package nearsoft.skytouch.microservice;

import nearsoft.skytouch.microservice.subscriber.Subscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = {"nearsoft.skytouch", "nearsoft.skytouch.common.model"})
@EntityScan({"nearsoft.skytouch", "nearsoft.skytouch.common.model"})
public class MicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceApplication.class, args);
    }

    @Bean
    public Subscriber subscriber() {
        return new Subscriber();
    }
}
