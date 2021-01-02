package com.archesky.content.service

import com.archesky.content.dto.Content
import com.archesky.content.dto.ContentRevision
import com.archesky.content.repository.ContentRepository
import com.archesky.content.repository.ContentRevisionRepository
import org.springframework.stereotype.Service

@Service
class QueryService(private val contentRepository: ContentRepository,
                   private val contentRevisionRepository: ContentRevisionRepository) {
    fun listContent(): Iterable<Content> {
        return contentRepository.findAll()
    }

    fun listAllRevisions(): Iterable<ContentRevision> {
        return contentRevisionRepository.
    }
}
