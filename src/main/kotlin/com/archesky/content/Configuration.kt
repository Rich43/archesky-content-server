package com.archesky.content

import com.arangodb.ArangoDB.Builder
import com.arangodb.springframework.annotation.EnableArangoRepositories
import com.arangodb.springframework.config.ArangoConfiguration
import org.springframework.context.annotation.Configuration

@Configuration
@EnableArangoRepositories(basePackages = ["com.archesky.content"])
class Configuration(val arangoDBProperties: ArangoDBProperties) : ArangoConfiguration {
    override fun arango(): Builder {
        return Builder()
    }

    override fun database(): String {
        return arangoDBProperties.arangodbDatabase
    }
}
