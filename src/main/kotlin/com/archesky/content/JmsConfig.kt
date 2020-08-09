package com.archesky.content

import org.apache.activemq.command.ActiveMQQueue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.jms.Queue

@Configuration
class JmsConfig {
    @Bean
    fun queue(): Queue {
        return ActiveMQQueue("content-queue")
    }
}