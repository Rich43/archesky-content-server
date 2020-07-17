package com.archesky.content

import com.arangodb.ArangoDB.Builder
import com.arangodb.springframework.annotation.EnableArangoRepositories
import com.arangodb.springframework.config.ArangoConfiguration
import org.springframework.context.annotation.Configuration

@Configuration
@EnableArangoRepositories(basePackages = ["com.archesky.content"])
class Configuration(val applicationProperties: ApplicationProperties) : ArangoConfiguration {
    override fun arango(): Builder {
        return Builder()
                .host(applicationProperties.arangodbHost, applicationProperties.arangodbPort)
                .user(applicationProperties.arangodbUser)
                .password(applicationProperties.arangodbPassword)
    }

    override fun database(): String {
        return applicationProperties.arangodbDatabase
    }
}
