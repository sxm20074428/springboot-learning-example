package com.sxm.springboot.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * 注意 扫描监听 否则无法接收消息
 */
@Component
public class RedisListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisListener.class);
    /**
     *redis 邮件频道配置
     */
    @Value("${spring.mail.channel}")
    public String channel;

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        LOGGER.info("启动监听");
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(connectionFactory);
        redisMessageListenerContainer.addMessageListener(listenerAdapter, new PatternTopic(channel));
        return redisMessageListenerContainer;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveTextMessage");
    }

    @Bean
    Receiver receiver(CountDownLatch countDownLatch) {
        return new Receiver(countDownLatch);
    }

    @Bean
    CountDownLatch countDownLatch() {
        return new CountDownLatch(1);
    }

}
