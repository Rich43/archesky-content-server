package com.archesky.content.resolvers

import com.archesky.content.dto.Content
import com.archesky.content.service.MutationService
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class Mutation(private val mutationService: MutationService): GraphQLMutationResolver {
    fun createContent(content: String, environment: DataFetchingEnvironment): Content {
        return mutationService.createContent(content)
    }

    fun updateContent(id: String, content: String, environment: DataFetchingEnvironment): Content? {
        return mutationService.updateContent(id, content)
    }

    fun deleteContent(id: String, environment: DataFetchingEnvironment): Content? {
        return mutationService.deleteContent(id)
    }
}
