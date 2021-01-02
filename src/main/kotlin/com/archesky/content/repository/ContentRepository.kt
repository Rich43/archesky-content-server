package com.archesky.content.repository

import com.arangodb.springframework.annotation.Query
import com.arangodb.springframework.repository.ArangoRepository
import com.archesky.content.dto.Content
import org.springframework.data.repository.query.Param
import java.util.*

interface ContentRepository : ArangoRepository<Content, String> {
    @Query(value =
            "INSERT { name: @name, displayName: @displayName, " +
            "created: @created, updated: @updated, published: false } INTO #collection RETURN NEW"
    )
    fun createContent(@Param("name") name: String, @Param("displayName") displayName: String,
                      @Param("created") created: Date, @Param("updated") updated: Date): Content

    @Query(value =
            "FOR c IN #collection FILTER c._key == @key " +
            "UPDATE { _key: c._key, displayName: @displayName } IN #collection RETURN NEW"
    )
    fun updateDisplayName(@Param("key") name: String, @Param("displayName") displayName: String): Content

    fun deleteContentByName(name: String)
}
