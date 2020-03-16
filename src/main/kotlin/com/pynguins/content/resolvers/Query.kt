package com.pynguins.content.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.pynguins.content.repository.ContentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.Collections.emptyList

@Component
class Query: GraphQLQueryResolver {
    @Autowired
    private val repository: ContentRepository? = null

    fun listContent(): List<Content> {
        if (repository != null) {
            val contentList = ArrayList<Content>()
            repository.findAll().forEach {
                val content = Content()
                if (it.id != null) {
                    content.id = it.id!!
                }
                if (it.content != null) {
                    content.content = it.content!!
                }
                contentList.add(content)
            }

            return contentList
        }
        println("No repository configured")
        return emptyList()
    }
}
