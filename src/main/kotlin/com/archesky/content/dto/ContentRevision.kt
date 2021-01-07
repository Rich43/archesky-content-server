package com.archesky.content.dto

import com.arangodb.springframework.annotation.Document
import com.arangodb.springframework.annotation.Ref
import org.springframework.data.annotation.Id
import java.util.*

@Document("content_revision")
data class ContentRevision(
    // TODO: Is null needed?
    @Id var id: String? = null,
    var content: String? = null,
    var summary: String? = null,
    var html: Boolean = false,
    var created: Date? = null,
    @Ref var parent: Content? = null
)
