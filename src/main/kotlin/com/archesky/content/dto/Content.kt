package com.archesky.content.dto

import com.arangodb.springframework.annotation.Document
import org.springframework.data.annotation.Id

@Document("content")
data class Content (
    @Id var name: String? = null,
    var displayName: String? = null,
    var published: Boolean = false,
    var latestRevision: ContentRevision? = null,
    var created: String? = null,
    var updated: String? = null
)
