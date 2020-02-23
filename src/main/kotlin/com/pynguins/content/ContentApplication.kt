package com.pynguins.content

import graphql.schema.GraphQLSchema
import graphql.schema.StaticDataFetcher
import graphql.schema.idl.RuntimeWiring.newRuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import graphql.schema.idl.TypeRuntimeWiring
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    val runner = arrayOf<Class<*>>(CrudRunner::class.java)
    exitProcess(SpringApplication.exit(SpringApplication.run(runner, args)))
}

@SpringBootApplication
class ContentApplication {
    @Bean
    fun schema(): GraphQLSchema? {
        val schema = "type Query{hello: String}"

        val schemaParser = SchemaParser()
        val typeDefinitionRegistry: TypeDefinitionRegistry = schemaParser.parse(schema)

        val runtimeWiring = newRuntimeWiring()
                .type("Query") {
					builder: TypeRuntimeWiring.Builder ->
						builder.dataFetcher("hello", StaticDataFetcher("world"))
				}
                .build()

        val schemaGenerator = SchemaGenerator()
        return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring)
    }
}
