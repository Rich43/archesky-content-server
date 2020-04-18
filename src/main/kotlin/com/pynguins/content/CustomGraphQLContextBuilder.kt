package com.pynguins.content

import graphql.kickstart.execution.context.DefaultGraphQLContext
import graphql.kickstart.execution.context.GraphQLContext
import graphql.kickstart.execution.context.GraphQLContextBuilder
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Configuration
@Component
@Primary
class CustomGraphQLContextBuilder : GraphQLContextBuilder {
    override fun build(): GraphQLContext {
        return DefaultGraphQLContext()
    }
}
