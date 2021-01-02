package com.archesky.content.dto

import com.arangodb.springframework.annotation.Edge
import com.arangodb.springframework.annotation.From
import com.arangodb.springframework.annotation.To

@Edge("content_mapping")
data class ContentMapping(
    @From var from: Content? = null,
    @To var to: ContentRevision? = null
)
