package br.com.medtech.ms_historico.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String USUARIOS_EXCHANGE = "usuarios.exchange";
    public static final String USUARIOS_QUEUE = "usuarios.fila";
    public static final String USUARIOS_ROUTING_KEY = "usuario.criado";

    public static final String CONSULTAS_EXCHANGE = "consultas.exchange";
    public static final String CONSULTAS_QUEUE = "consultas.fila.historico";
    public static final String CONSULTAS_ROUTING_KEY = "consulta.*";

    @Bean
    public TopicExchange usuariosExchange() {
        return new TopicExchange(USUARIOS_EXCHANGE);
    }

    @Bean
    public Queue usuariosQueue() {
        return new Queue(USUARIOS_QUEUE);
    }

    @Bean
    public Binding usuariosBinding(Queue usuariosQueue, TopicExchange usuariosExchange) {
        return BindingBuilder.bind(usuariosQueue).to(usuariosExchange).with(USUARIOS_ROUTING_KEY);
    }

    @Bean
    public TopicExchange consultasExchange() {
        return new TopicExchange(CONSULTAS_EXCHANGE);
    }

    @Bean
    public Queue consultasQueue() {
        return new Queue(CONSULTAS_QUEUE);
    }

    @Bean
    public Binding consultasBinding(Queue consultasQueue, TopicExchange consultasExchange) {
        return BindingBuilder.bind(consultasQueue).to(consultasExchange).with(CONSULTAS_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
