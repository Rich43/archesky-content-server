package com.archesky.content.resolvers

import graphql.kickstart.tools.GraphQLSubscriptionResolver
import javax.jms.Queue

class Subscription(private val queue: Queue): GraphQLSubscriptionResolver {
}
