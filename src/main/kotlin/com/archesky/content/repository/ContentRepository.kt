package com.archesky.content.repository

import com.arangodb.springframework.annotation.Query
import com.arangodb.springframework.repository.ArangoRepository
import com.archesky.content.dto.Content
import org.springframework.data.repository.query.Param

interface ContentRepository : ArangoRepository<Content, String> {
    @Query(value = "INSERT { content: @content } INTO #collection RETURN NEW")
    fun createContent(@Param("content") content: String): Content

    @Query(value =
            "FOR c IN #collection FILTER c._key == @key " +
            "UPDATE { _key: c._key, content: @content } IN #collection RETURN NEW"
    )
    fun updateContentById(@Param("key") id: String, @Param("content") content: String): Content

    fun deleteContentById(id: String)
}
