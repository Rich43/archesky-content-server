package com.archesky.content.configuration

import org.apache.activemq.command.ActiveMQQueue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.jms.Queue

const val QUEUE_NAME = "content-queue"

@Configuration
class JmsConfig {
    @Bean
    fun queue(): Queue {
        return ActiveMQQueue(QUEUE_NAME)
    }
}