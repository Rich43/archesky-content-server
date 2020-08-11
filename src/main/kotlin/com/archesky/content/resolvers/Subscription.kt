package com.archesky.content.resolvers

import com.archesky.content.dto.Content
import com.archesky.content.service.ContentQueueService
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.messaging.Message
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux.from

@Component
class Subscription(private val contentQueueService: ContentQueueService): GraphQLSubscriptionResolver {
    fun updateContent(): Publisher<Content> {
        return from<Message<Content>>(contentQueueService.jmsReactiveSource()).map {
            obj: Message<Content> -> obj.payload
        }
    }
}
