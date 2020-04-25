package com.pynguins.content.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.pynguins.content.service.MutationService
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Mutation: GraphQLMutationResolver {
    @Autowired
    private val mutationService: MutationService? = null

    fun createContent(content: String, environment: DataFetchingEnvironment): Content {
        return mutationService!!.createContent(content)
    }

    fun updateContent(id: String, content: String, environment: DataFetchingEnvironment): Content? {
        return mutationService!!.updateContent(id, content)
    }

    fun deleteContent(id: String, environment: DataFetchingEnvironment): Content? {
        return mutationService!!.deleteContent(id)
    }
}
