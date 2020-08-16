package com.archesky.content.service

import com.archesky.content.dto.Content
import com.archesky.content.repository.ContentRepository
import com.google.gson.Gson
import org.springframework.jms.core.JmsTemplate
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import javax.jms.Queue

@Service
class MutationService(private val repository: ContentRepository,
                      private val queue: Queue,
                      private val jmsTemplate: JmsTemplate) {
    @PreAuthorize("hasAuthority('archesky.create_content')")
    fun createContent(content: String): Content {
        return repository.createContent(content)
    }

    @PreAuthorize("hasAuthority('archesky.update_content')")
    fun updateContent(id: String, content: String): Content? {
        val result = repository.updateContentById(id, content)
        jmsTemplate.convertAndSend(queue, Gson().toJson(result))
        return result
    }

    @PreAuthorize("hasAuthority('archesky.delete_content')")
    fun deleteContent(id: String): Boolean {
        repository.deleteContentById(id)
        return true
    }
}
