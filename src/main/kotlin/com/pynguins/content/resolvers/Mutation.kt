package com.pynguins.content.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.pynguins.content.repository.ContentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Mutation: GraphQLMutationResolver {
    @Autowired
    private val repository: ContentRepository? = null

    fun createContent(content: String): Content {
        val contentData = Content()
        contentData.content = content
        repository!!.save(contentData)
        return contentData
    }

    fun updateContent(id: String, content: String): Content? {
        val result = repository!!.findById(id)
        val resultGet = result.get()
        if (result.isPresent) {
            resultGet.content = content
            repository.save(resultGet)
        } else {
            return null
        }
        return resultGet
    }

    fun deleteContent(id: String): Content? {
        val result = repository!!.findById(id)
        val resultGet = result.get()
        if (result.isPresent) {
            repository.delete(resultGet)
        } else {
            return null
        }
        return resultGet
    }
}
