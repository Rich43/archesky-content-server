package com.archesky.content.resolvers

import com.archesky.content.dto.Content
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import javax.jms.Queue

class Subscription(private val queue: Queue): GraphQLSubscriptionResolver {
    fun updateContent(): Content {
        return Content("1", "foo")
    }
}
