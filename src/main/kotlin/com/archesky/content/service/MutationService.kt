package com.archesky.content.service

import com.archesky.content.dto.Content
import com.archesky.content.repository.ContentRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class MutationService(private val repository: ContentRepository) {
    private fun findById(id: String): Content? {
        val result = repository.findById(id)
        return if (result.isPresent) result.get() else null
    }

    @PreAuthorize("hasAuthority('archesky.create_content')")
    fun createContent(content: String): Content {
        val contentData = Content()
        contentData.content = content
        repository.save(contentData)
        return contentData
    }

    @PreAuthorize("hasAuthority('archesky.update_content')")
    fun updateContent(id: String, content: String): Content? {
        val result = findById(id)
        if (result != null) {
            result.content = content
            repository.save(result)
        } else {
            return null
        }
        return result
    }

    @PreAuthorize("hasAuthority('archesky.delete_content')")
    fun deleteContent(id: String): Content? {
        val result = findById(id)
        if (result != null) {
            repository.delete(result)
        } else {
            return null
        }
        return result
    }
}
