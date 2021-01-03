package com.archesky.content.resolvers

import com.archesky.content.dto.ContentRevision
import com.archesky.content.service.ContentQueueService
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux.from

@Component
class Subscription(private val contentQueueService: ContentQueueService) : GraphQLSubscriptionResolver {
    fun updateContent(name: String): Publisher<ContentRevision> {
        return from(contentQueueService.getPublisher())
                .filter { content -> content.parent!!.name == name }
    }
}
