package com.pynguins.content.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.pynguins.content.repository.ContentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Mutation: GraphQLMutationResolver {
    @Autowired
    private val repository: ContentRepository? = null

    private fun findById(id: String): Content? {
        val result = repository!!.findById(id)
        return if (result.isPresent) result.get() else null
    }

    fun createContent(content: String): Content {
        val contentData = Content()
        contentData.content = content
        repository!!.save(contentData)
        return contentData
    }

    fun updateContent(id: String, content: String): Content? {
        val result = findById(id)
        if (result != null) {
            result.content = content
            repository!!.save(result)
        } else {
            return null
        }
        return result
    }

    fun deleteContent(id: String): Content? {
        val result = findById(id)
        if (result != null) {
            repository!!.delete(result)
        } else {
            return null
        }
        return result
    }
}
