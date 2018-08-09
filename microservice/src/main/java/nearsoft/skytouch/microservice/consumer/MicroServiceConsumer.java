package nearsoft.skytouch.microservice.consumer;

public interface MicroServiceConsumer {
    void storeProduct(String productJSON);

    void getProducts();
}
