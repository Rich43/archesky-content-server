package com.pynguins.content.resolvers


import com.arangodb.springframework.annotation.Document
import org.springframework.data.annotation.Id

@Document("content")
class Content {
    @Id
    var id = ""
    var content = ""

    override fun toString(): String {
        return "Content [id=$id, content=$content]"
    }
}
