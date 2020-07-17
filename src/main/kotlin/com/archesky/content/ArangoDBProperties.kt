package com.archesky.content

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:arangodb.properties")
data class ArangoDBProperties (
    @Value("\${arangodb.database}") val arangodbDatabase: String
)
