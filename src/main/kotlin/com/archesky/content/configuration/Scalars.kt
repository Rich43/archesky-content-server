package com.archesky.content.configuration

import graphql.schema.Coercing
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import graphql.schema.GraphQLScalarType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.String.valueOf
import java.time.Instant
import java.time.format.DateTimeFormatter.ISO_INSTANT
import java.util.*

@Configuration
class Scalars {
    @Bean
    fun dateTimeScalar(): GraphQLScalarType {
        return GraphQLScalarType.newScalar()
            .name("DateTime")
            .description("Convert a Date object to a ISO 8601 date")
            .coercing(object : Coercing<Any?, Any?> {
                override fun serialize(dataFetcherResult: Any?): Any? {
                    try {
                        val date = valueOf(dataFetcherResult)
                        return Date.from(Instant.from(ISO_INSTANT.parse(date)))
                    } catch (e: Exception) {
                        throw CoercingSerializeException("Unable to serialize $dataFetcherResult as a date", e)
                    }
                }

                override fun parseValue(input: Any?): Any? {
                    if (input is Date) {
                        val date: Date = input
                        return ISO_INSTANT.format(date.toInstant())
                    } else {
                        throw CoercingParseValueException("Unable to parse variable value $input as a date")
                    }
                }

                override fun parseLiteral(input: Any?): Any? {
                    if (input is Date) {
                        val date: Date = input
                        return ISO_INSTANT.format(date.toInstant())
                    } else {
                        throw CoercingParseValueException("Unable to parse variable value $input as a date")
                    }
                }
            })
            .build()
    }
}