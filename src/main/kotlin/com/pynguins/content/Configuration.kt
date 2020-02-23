package com.pynguins.content

import com.arangodb.ArangoDB.Builder
import com.arangodb.springframework.annotation.EnableArangoRepositories
import com.arangodb.springframework.config.ArangoConfiguration
import org.springframework.context.annotation.Configuration

@Configuration
@EnableArangoRepositories(basePackages = ["com.pynguins.content"])
class DemoConfiguration : ArangoConfiguration {
    override fun arango(): Builder {
        return Builder()
                .host(
                        "localhost",
                        8529
                )
                .user("root")
                .password("Password1")
    }

    override fun database(): String {
        return "_system"
    }
}
