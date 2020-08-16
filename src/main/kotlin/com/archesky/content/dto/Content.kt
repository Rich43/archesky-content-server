package com.archesky.content.dto

import com.arangodb.springframework.annotation.Document
import org.springframework.data.annotation.Id

const val CONTENT = "content"
@Document(CONTENT)
data class Content (
    @Id var id: String? = null,
    var content: String? = null
)
