package com.archesky.content.resolvers

import com.archesky.content.dto.Content
import com.archesky.content.service.MutationService
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.stereotype.Component

@Component
class Mutation(private val mutationService: MutationService): GraphQLMutationResolver {
    fun createContent(name: String, displayName: String): Content {
        return mutationService.createContent(name, displayName)
    }

    fun createRevision(name: String, content: String, summary: String, html: Boolean): Content? {
        return mutationService.createRevision(name, content, summary, html)
    }

    fun deleteContent(name: String): Boolean {
        return mutationService.deleteContent(name)
    }
}
