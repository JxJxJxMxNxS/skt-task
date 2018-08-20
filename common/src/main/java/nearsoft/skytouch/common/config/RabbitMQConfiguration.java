package nearsoft.skytouch.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableRabbit
@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbit-names-config.products-exchange-name:productExchange}")
    private String productsExchangeName;
    @Value("${rabbit-names-config.receive-products-queue-name:receiveProductsQueue}")
    private String receiveProductsQueueName;
    @Value("${rabbit-names-config.request-products-queue-name:requestProductsQueue}")
    private String requestProductsQueueName;
    @Value("${rabbit-names-config.store-products-queue-name:storeProductsQueue}")
    private String storeProductsQueueName;
    @Value("${rabbit-names-config.store-reply-products-queue-name:storeReplyProductsQueue}")
    private String storeReplyProductsQueueName;

    @Value("${rabbit-names-config.reply-products-routing-key:reply.products}")
    private String replyProductsRoutingKey;
    @Value("${rabbit-names-config.request-products-routing-key:request.products}")
    private String requestProductsRoutingKey;
    @Value("${rabbit-names-config.store-products-routing-key:store.product}")
    private String storeProductsRoutingKey;
    @Value("${rabbit-names-config.store-reply-products-routing-key:store.reply.product}")
    private String storeReplyProductsRoutingKey;


    @Bean
    public Executor taskExecutor() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleMessageListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                                      SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setTaskExecutor(taskExecutor());
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        template.setReplyTimeout(20000);

        return template;
    }

    @Bean
    public org.springframework.amqp.support.converter.MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue replyQueueRPC() {
        return new Queue(receiveProductsQueueName);
    }

    @Bean
    public Queue requestQueueRPC() {
        return new Queue(requestProductsQueueName);
    }

    @Bean
    public Queue storeQueueRPC() {
        return new Queue(storeProductsQueueName);
    }

    @Bean
    public Queue storeReplyQueueRPC() {
        return new Queue(storeReplyProductsQueueName);
    }


    @Bean
    public SimpleMessageListenerContainer rpcReplyMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        simpleMessageListenerContainer.setQueues(replyQueueRPC());
        simpleMessageListenerContainer.setTaskExecutor(taskExecutor());
        return simpleMessageListenerContainer;
    }


    @Bean
    public AsyncRabbitTemplate asyncRabbitTemplate(ConnectionFactory connectionFactory) {

        AsyncRabbitTemplate asyncRabbitTemplate = new AsyncRabbitTemplate(rabbitTemplate(connectionFactory),
                rpcReplyMessageListenerContainer(connectionFactory),
                productsExchangeName + "/" + replyProductsRoutingKey);
        return asyncRabbitTemplate;
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(productsExchangeName);
    }

    @Bean
    public List<Binding> bindings() {
        return Arrays.asList(
                BindingBuilder.bind(requestQueueRPC()).to(directExchange()).with(requestProductsRoutingKey),
                BindingBuilder.bind(replyQueueRPC()).to(directExchange()).with(replyProductsRoutingKey),
                BindingBuilder.bind(storeQueueRPC()).to(directExchange()).with(storeProductsRoutingKey),
                BindingBuilder.bind(storeReplyQueueRPC()).to(directExchange()).with(storeReplyProductsRoutingKey)
        );

    }

}
