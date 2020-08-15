package com.archesky.content.resolvers

import com.archesky.content.configuration.ApplicationProperties
import com.archesky.content.dto.Content
import com.archesky.content.kafka.KafkaConsumer
import com.google.gson.Gson
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component

@Component
class Subscription(private val applicationProperties: ApplicationProperties) : GraphQLSubscriptionResolver {
    fun updateContent(id: String): Publisher<Content> {
        return KafkaConsumer(applicationProperties).getConsumer()
                .map { row -> Gson().fromJson(row, Content::class.java) }
    }
}
