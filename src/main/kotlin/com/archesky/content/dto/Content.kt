package com.archesky.content.dto


import com.arangodb.springframework.annotation.Document
import org.springframework.data.annotation.Id

@Document("content")
data class Content (
    @Id var id: String? = null,
    var content: String? = null
)
