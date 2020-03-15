package com.pynguins.content.arango_data

import com.arangodb.springframework.annotation.Document
import org.springframework.data.annotation.Id


@Document("content")
class ArangoContent {
    @Id
    var id: String? = null
    var content: String? = null

    constructor(content: String) : super() {
        this.content = content
    }

    override fun toString(): String {
        return "Content [id=$id, content=$content]"
    }
}
