package com.archesky.content.resolvers

import com.archesky.content.dto.Content
import com.archesky.content.dto.ContentRevision
import com.archesky.content.dto.RevisionInput
import com.archesky.content.service.MutationService
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.stereotype.Component
import java.util.*
import java.util.Collections.singletonList

@Component
class Mutation(private val mutationService: MutationService): GraphQLMutationResolver {
    fun createContent(name: String, displayName: String): Content {
        return mutationService.createContent(name, displayName)
    }

    fun createRevision(name: String, revisionInput: RevisionInput): ContentRevision {
        return mutationService.createRevision(
            name, revisionInput.content,
            revisionInput.summary, revisionInput.html
        )
    }

    fun deleteContent(name: String): Boolean {
        return mutationService.deleteContent(name)
    }

    fun updateDisplayName(name: String, displayName: String): Content {
        val revision = ContentRevision("123", "blah", "blah")
        return Content(
            null, "Dummy", "dummy", false,
            revision, null, null,
            singletonList(revision) as ArrayList<ContentRevision>?
        )
    }

    fun revertContent(name: String, revisionId: String): ContentRevision {
        return ContentRevision("123", "blah", "blah")
    }

    fun togglePublished(name: String): Boolean {
        return true
    }
}
