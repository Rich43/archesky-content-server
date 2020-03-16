package com.pynguins.content.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.pynguins.content.repository.ContentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Mutation: GraphQLMutationResolver {
    @Autowired
    private val repository: ContentRepository? = null

    fun createContent(content: String): Boolean {
        val contentData = Content()
        contentData.content = content
        repository!!.save(contentData)
        return true
    }

    fun updateContent(id: String, content: String): Boolean {
        val result = repository!!.findById(id)
        if (result.isPresent) {
            result.get().content = content
            repository.save(result.get())
        } else {
            return false
        }
        return true
    }

    fun deleteContent(id: String): Boolean {
        val result = repository!!.findById(id)
        if (result.isPresent) {
            repository.delete(result.get())
        } else {
            return false
        }
        return true
    }
}
