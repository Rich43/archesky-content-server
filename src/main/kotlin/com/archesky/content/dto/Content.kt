package com.archesky.content.dto

import com.arangodb.springframework.annotation.Document
import org.springframework.data.annotation.Id
import java.util.*

@Document("content")
data class Content (
    @Id var name: String? = null,
    var displayName: String? = null,
    var published: Boolean = false,
    var latestRevision: ContentRevision? = null,
    var created: Date? = null,
    var updated: Date? = null
)
