package com.archesky.content.service

import com.archesky.content.QUEUE_NAME
import com.archesky.content.dto.Content
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.MessageChannels.queue
import org.springframework.integration.jms.dsl.Jms.messageDrivenChannelAdapter
import org.springframework.messaging.Message
import org.springframework.stereotype.Service
import javax.jms.ConnectionFactory

@Service
class ContentQueueService(@Qualifier("jmsConnectionFactory") private val connectionFactory: ConnectionFactory) {
    @Bean
    fun jmsReactiveSource(): Publisher<Message<Content>> {
        return IntegrationFlows
                .from(messageDrivenChannelAdapter(connectionFactory).destination(QUEUE_NAME))
                .channel(queue())
                .toReactivePublisher()
    }
}
