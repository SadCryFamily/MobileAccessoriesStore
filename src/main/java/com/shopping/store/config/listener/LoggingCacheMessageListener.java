package com.shopping.store.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingCacheMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("RedisCacheManager -> [{} ---> {}]", new String(message.getBody()).toUpperCase(), new String(message.getChannel()));
    }

}
