package nearsoft.skytouch.microservice;

import nearsoft.skytouch.common.ProductJSONSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceApplication.class, args);
    }

    @Bean
    public ProductJSONSerializer getProductJSONSerializer() {
        return new ProductJSONSerializer();
    }
}
