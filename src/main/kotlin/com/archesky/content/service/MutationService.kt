package com.archesky.content.service

import com.archesky.content.dto.Content
import com.archesky.content.dto.ContentRevision
import com.archesky.content.repository.ContentRepository
import com.archesky.content.repository.ContentRevisionRepository
import graphql.GraphQLException
import org.springframework.stereotype.Service
import java.util.*

@Service
class MutationService(private val contentRepository: ContentRepository,
                      private val contentRevisionRepository: ContentRevisionRepository,
                      private val contentQueueService: ContentQueueService) {
//    @PreAuthorize("hasAuthority('archesky.create_content')")
    fun createContent(name: String, displayName: String): Content {
        try {
            contentRepository.findByName(name)
            throw GraphQLException("Content already exists")
        } catch (e: Exception) { // TODO change me
            // Ignore
        }
        return contentRepository.createContent(name, displayName, Date(), Date())
    }

    @PreAuthorize("hasAuthority('archesky.update_content')")
    fun createRevision(name: String, content: String, summary: String, html: Boolean): ContentRevision {
        val contentByName = contentRepository.findByName(name)
        val contentRevision = ContentRevision(null, content, summary, html, Date(), contentByName)
        contentRevisionRepository.save(contentRevision)
        contentQueueService.push(contentRevision)
        return contentRevision
    }

    @PreAuthorize("hasAuthority('archesky.delete_content')")
    fun deleteContent(name: String): Boolean {
        contentRepository.deleteContentByName(name)
        return true
    }
}
