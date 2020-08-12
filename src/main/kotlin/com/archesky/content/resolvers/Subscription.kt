package com.archesky.content.resolvers

import com.archesky.content.dto.Content
import com.archesky.content.service.ContentQueueService
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component

@Component
class Subscription(private val contentQueueService: ContentQueueService) : GraphQLSubscriptionResolver {
    fun updateContent(id: String): Publisher<Content> {
        return contentQueueService.getContentFlux()
                .filter { content -> content.id == id }
    }
}
