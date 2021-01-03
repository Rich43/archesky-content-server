package com.archesky.content.service

import com.archesky.content.dto.Content
import com.archesky.content.dto.ContentRevision
import com.archesky.content.repository.ContentRepository
import com.archesky.content.repository.ContentRevisionRepository
import org.springframework.stereotype.Service
import java.util.ArrayList

@Service
class QueryService(private val contentRepository: ContentRepository,
                   private val contentRevisionRepository: ContentRevisionRepository) {
    fun listContent(): Iterable<Content> {
        return contentRepository.findAll()
    }

    fun listAllRevisions(name: String): ArrayList<ContentRevision>? {
        return contentRepository.findByName(name).contentRevision
    }
}
