package com.pynguins.content.data

import com.arangodb.springframework.annotation.Document
import org.springframework.data.annotation.Id


@Document("content")
class Content {
    @Id
    private var id: String? = null
    private var content: String? = null

    constructor() : super() {}
    constructor(content: String) : super() {
        this.content = content
    }

    override fun toString(): String {
        return "Content [id=$id, content=$content]"
    }
}
