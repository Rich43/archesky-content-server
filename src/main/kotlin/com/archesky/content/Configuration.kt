package com.archesky.content

import com.arangodb.ArangoDB.Builder
import com.arangodb.springframework.annotation.EnableArangoRepositories
import com.arangodb.springframework.config.ArangoConfiguration
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
@EnableArangoRepositories(basePackages = ["com.archesky.content"])
class Configuration : ArangoConfiguration {
    override fun arango(): Builder {
        return Builder()
    }

    override fun database(): String {
        val prop = Properties()
        prop.load(javaClass.classLoader.getResourceAsStream("arangodb.properties"))
        return prop.getProperty("arangodb.database")
    }
}
