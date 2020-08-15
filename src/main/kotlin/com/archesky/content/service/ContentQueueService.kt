package com.archesky.content.service

import com.archesky.content.dto.Content
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

@Service
class ContentQueueService {
    private var emitter: FluxSink<Content>? = null
    private val content: Flux<Content> = Flux.create<Content>({ emit: FluxSink<Content> ->
        emitter = emit
    }, FluxSink.OverflowStrategy.BUFFER)

    fun getContentFlux(): Flux<Content> {
        return content
    }
}
