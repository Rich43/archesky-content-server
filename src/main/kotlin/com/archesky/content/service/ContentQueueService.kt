package com.archesky.content.service

import com.archesky.content.configuration.QUEUE_NAME
import com.archesky.content.dto.Content
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import javax.jms.ConnectionFactory


@Service
@EnableJms
class ContentQueueService(@Qualifier("jmsConnectionFactory") private val connectionFactory: ConnectionFactory) {
    private var emitter: FluxSink<Content>? = null
    private val content: Flux<Content> = Flux.create<Content>({ emit: FluxSink<Content> ->
        emitter = emit
    }, FluxSink.OverflowStrategy.BUFFER)

    @JmsListener(destination = QUEUE_NAME)
    private fun listener(message: String) {
        emitter!!.next(Gson().fromJson(message, Content::class.java))
    }

    fun getContentFlux(): Flux<Content> {
        return content
    }
}
