package nearsoft.skytouch.management.repository.Impl;

import nearsoft.skytouch.common.ProductJSONSerializer;
import nearsoft.skytouch.common.config.RabbitMQConfigProperties;
import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.ManagementAppException;
import nearsoft.skytouch.management.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
class ProductRepositoryImpl implements ProductRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    private ProductJSONSerializer productJSONSerializer;
    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;
    private RabbitMQConfigProperties rabbitMQConfigProperties;

    public ProductRepositoryImpl(ProductJSONSerializer productJSONSerializer, RabbitTemplate rabbitTemplate, DirectExchange directExchange, RabbitMQConfigProperties rabbitMQConfigProperties) {
        this.productJSONSerializer = productJSONSerializer;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
        this.rabbitMQConfigProperties = rabbitMQConfigProperties;
    }

    public List<Product> retrieveProducts() throws ManagementAppException {
        Object products = null;
        try {
            products = rabbitTemplate.convertSendAndReceive(directExchange.getName(), rabbitMQConfigProperties.getRequestProductsRoutingKey(), "request");
            return productJSONSerializer.deserializeList(products.toString());
        } catch (IOException e) {
            throw new ManagementAppException("Failed parsing: " + products.toString(), e);
        } catch (AmqpException e) {
            throw new ManagementAppException("Failed retrieving the products while trying to connect with the queue", e);
        }

    }

    @Override
    public Product storeProduct(Product product) throws ManagementAppException {
        Object storedProduct = null;
        try {
            storedProduct = rabbitTemplate.convertSendAndReceive(directExchange.getName(), rabbitMQConfigProperties.getStoreProductsRoutingKey(), productJSONSerializer.serializeObject(product));
            return productJSONSerializer.deserializeObject(product.toString());
        } catch (IOException e) {
            throw new ManagementAppException("Failed parsing: " + storedProduct, e);
        } catch (AmqpException e) {
            throw new ManagementAppException("Failed storing the product while trying to connect with the queue", e);
        }

    }

}
