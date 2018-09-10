package nearsoft.skytouch.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableRabbit
@Component
public class RabbitMQConfiguration {

    private RabbitMQConfigProperties rabbitMQConfigProperties;

    public RabbitMQConfiguration(RabbitMQConfigProperties rabbitMQConfigProperties) {
        this.rabbitMQConfigProperties = rabbitMQConfigProperties;
    }

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
        return new Queue(rabbitMQConfigProperties.getReceiveProductsQueueName());
    }

    @Bean
    public Queue requestQueueRPC() {
        return new Queue(rabbitMQConfigProperties.getRequestProductsQueueName());
    }

    @Bean
    public Queue storeQueueRPC() {
        return new Queue(rabbitMQConfigProperties.getStoreProductsQueueName());
    }

    @Bean
    public Queue storeReplyQueueRPC() {
        return new Queue(rabbitMQConfigProperties.getStoreReplyProductsQueueName());
    }


    @Bean
    public SimpleMessageListenerContainer rpcReplyMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        simpleMessageListenerContainer.setQueues(replyQueueRPC());
        simpleMessageListenerContainer.setTaskExecutor(taskExecutor());
        return simpleMessageListenerContainer;
    }


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(rabbitMQConfigProperties.getProductsExchangeName());
    }

    @Bean
    public List<Binding> bindings() {
        return Arrays.asList(
                BindingBuilder.bind(requestQueueRPC()).to(directExchange()).with(rabbitMQConfigProperties.getRequestProductsRoutingKey()),
                BindingBuilder.bind(replyQueueRPC()).to(directExchange()).with(rabbitMQConfigProperties.getReplyProductsRoutingKey()),
                BindingBuilder.bind(storeQueueRPC()).to(directExchange()).with(rabbitMQConfigProperties.getStoreProductsRoutingKey()),
                BindingBuilder.bind(storeReplyQueueRPC()).to(directExchange()).with(rabbitMQConfigProperties.getStoreReplyProductsRoutingKey())
        );

    }

}
