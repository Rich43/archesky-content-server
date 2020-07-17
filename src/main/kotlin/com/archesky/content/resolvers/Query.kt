package com.archesky.content.resolvers

import com.archesky.content.dto.Content
import com.archesky.content.repository.ContentRepository
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class Query(private val repository: ContentRepository): GraphQLQueryResolver {
    fun listContent(): MutableIterable<Content> {
        return repository.findAll()
    }
}
