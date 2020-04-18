package com.pynguins.content.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.pynguins.auth.library.CheckTokenQuery
import com.pynguins.auth.library.TokenService
import com.pynguins.content.repository.ContentRepository
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class Mutation: GraphQLMutationResolver {
    @Autowired
    private val repository: ContentRepository? = null

    private fun findById(id: String): Content? {
        val result = repository!!.findById(id)
        return if (result.isPresent) result.get() else null
    }

    private fun getToken(environment: DataFetchingEnvironment): CheckTokenQuery.CheckToken? {
//        val propertiesMap = environment.getContext<DefaultGraphQLServletContext>()
//        propertiesMap.subject
//        if (propertiesMap.containsKey("token")) {
//            return propertiesMap["token"] as CheckTokenQuery.CheckToken
//        }
        return null
    }

    fun setToken(token: String, environment: DataFetchingEnvironment): Boolean {
        val tokenObj = TokenService().validateToken(token, "http://localhost:9090/graphql")
        environment.getContext<ConcurrentHashMap<String, Any>>()["token"] = tokenObj
        return true
    }

    fun createContent(content: String, environment: DataFetchingEnvironment): Content {
//        assert(getToken(environment) != null)
        val contentData = Content()
        contentData.content = content
        repository!!.save(contentData)
        return contentData
    }

    fun updateContent(id: String, content: String, environment: DataFetchingEnvironment): Content? {
        val result = findById(id)
        if (result != null) {
            result.content = content
            repository!!.save(result)
        } else {
            return null
        }
        return result
    }

    fun deleteContent(id: String, environment: DataFetchingEnvironment): Content? {
        val result = findById(id)
        if (result != null) {
            repository!!.delete(result)
        } else {
            return null
        }
        return result
    }
}
