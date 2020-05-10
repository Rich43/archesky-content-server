package com.archesky.content.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.archesky.content.repository.ContentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.Collections.emptyList

@Component
class Query: GraphQLQueryResolver {
    @Autowired
    private val repository: ContentRepository? = null

    fun listContent(): MutableIterable<Content> {
        return repository!!.findAll()
    }
}
