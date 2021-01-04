package com.archesky.content.dto

import com.arangodb.springframework.annotation.Edge
import com.arangodb.springframework.annotation.From
import com.arangodb.springframework.annotation.To
import org.springframework.data.annotation.Id

@Edge("content_mapping")
data class ContentMapping(
    @Id var id: String? = null,
    @From var from: Content? = null,
    @To var to: ContentRevision? = null
)
