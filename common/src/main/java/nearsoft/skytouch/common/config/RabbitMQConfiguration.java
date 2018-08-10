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
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@ComponentScan(basePackages = {"nearsoft.skytouch"})
@EnableRabbit
@Configuration
public class RabbitMQConfiguration {

    public static final String PRODUCTS_EXCHANGE = "productsExchange";
    public static final String CREATE_PRODUCTS_CHANNEL_NAME = "createProductsChannel";
    public static final String REQUEST_PRODUCTS_CHANNEL_NAME = "requestProductsChannel";
    public static final String RECEIVE_PRODUCTS_CHANNEL_NAME = "receiveProductsChannel";

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
        return new Queue("receiveProductsQueue");
    }

    @Bean
    public Queue requestQueueRPC() {
        return new Queue("requestProductsQueue");
    }

    @Bean
    public Queue storeQueueRPC() {
        return new Queue("storeProductsQueue");
    }

    @Bean
    public Queue storeReplyQueueRPC() {
        return new Queue("storeReplyProductsQueue");
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
                "productsExchange2" + "/" + "reply.products");
        return asyncRabbitTemplate;
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("productsExchange2");
    }

    @Bean
    public List<Binding> bindings() {
        return Arrays.asList(
                BindingBuilder.bind(requestQueueRPC()).to(directExchange()).with("request.products"),
                BindingBuilder.bind(replyQueueRPC()).to(directExchange()).with("reply.products"),
                BindingBuilder.bind(storeQueueRPC()).to(directExchange()).with("store.product"),
                BindingBuilder.bind(storeReplyQueueRPC()).to(directExchange()).with("store.reply.product")
        );

    }

}
