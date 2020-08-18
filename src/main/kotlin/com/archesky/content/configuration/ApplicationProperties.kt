package com.archesky.content.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:application.properties")
data class ApplicationProperties (
    @Value("\${arangodb.database}") val arangodbDatabase: String,
    @Value("\${arangodb.host}") val arangodbHost: String,
    @Value("\${arangodb.port}") val arangodbPort: Int,
    @Value("\${arangodb.user}") val arangodbUser: String,
    @Value("\${arangodb.password}") val arangodbPassword: String,
    @Value("#{T(java.lang.Integer).parseInt(\${server.http.port})}") val httpPort: Int,
    @Value("#{T(java.lang.Integer).parseInt(\${server.port})}") val httpsPort: Int
)
