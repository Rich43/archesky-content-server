package com.archesky.content.service

import com.archesky.content.dto.Content
import com.archesky.content.repository.ContentRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class MutationService(private val repository: ContentRepository,
                      private val contentQueueService: ContentQueueService) {
    @PreAuthorize("hasAuthority('archesky.create_content')")
    fun createContent(content: String): Content {
        return repository.createContent(content)
    }

    @PreAuthorize("hasAuthority('archesky.update_content')")
    fun updateContent(id: String, content: String): Content? {
        val result = repository.updateContentById(id, content)
        contentQueueService.push(result)
        return result
    }

    @PreAuthorize("hasAuthority('archesky.delete_content')")
    fun deleteContent(id: String): Boolean {
        repository.deleteContentById(id)
        return true
    }
}
