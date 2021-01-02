package com.archesky.content.dto

import com.arangodb.springframework.annotation.Document
import org.springframework.data.annotation.Id

@Document("content_revision")
data class ContentRevision(
    // TODO: Is null needed?
    @Id var id: String? = null,
    var content: String? = null,
    var summary: String? = null,
    var html: Boolean = false,
    var created: String? = null
)
