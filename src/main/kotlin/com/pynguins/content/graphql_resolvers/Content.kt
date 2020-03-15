package com.pynguins.content.graphql_resolvers


import org.springframework.data.annotation.Id

class Content {
    @Id
    var id = ""
        get() = field

    var content = ""
        get() = field
}
