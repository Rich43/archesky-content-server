package com.archesky.content.resolvers

import com.archesky.content.dto.Content
import com.archesky.content.dto.ContentRevision
import com.archesky.content.service.QueryService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component
import java.util.ArrayList

@Component
class Query(private val queryService: QueryService): GraphQLQueryResolver {
    fun listContent(): Iterable<Content> {
        return queryService.listContent()
    }

    fun listAllRevisions(name: String): ArrayList<ContentRevision>? {
        return queryService.listAllRevisions(name)
    }
}
