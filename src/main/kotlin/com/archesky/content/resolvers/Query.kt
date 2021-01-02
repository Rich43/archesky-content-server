package com.archesky.content.resolvers

import com.archesky.content.dto.Content
import com.archesky.content.service.QueryService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class Query(private val queryService: QueryService): GraphQLQueryResolver {
    fun listContent(): Iterable<Content> {
        return queryService.listContent()
    }
}
