package com.archesky.content.kafka

import com.archesky.content.configuration.ApplicationProperties
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.IntegerDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.logging.log4j.LogManager.getLogger
import reactor.core.publisher.Flux
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOptions
import java.util.*

class KafkaConsumer(private val applicationProperties: ApplicationProperties) {
    private val receiverOptions: ReceiverOptions<Int, String>
    private val log = getLogger(this.javaClass.name)

    init {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = applicationProperties.kafkaUrl
        props[ConsumerConfig.CLIENT_ID_CONFIG] = applicationProperties.kafkaTopic
        props[ConsumerConfig.GROUP_ID_CONFIG] = applicationProperties.kafkaTopic
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = IntegerDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"

        receiverOptions = ReceiverOptions.create(props)
    }

    fun getConsumer(): Flux<String> {
        val options = receiverOptions.subscription(setOf(applicationProperties.kafkaTopic))
                .addAssignListener { partitions: Any? -> log.debug("onPartitionsAssigned {}", partitions) }
                .addRevokeListener { partitions: Any? -> log.debug("onPartitionsRevoked {}", partitions) }
        return KafkaReceiver
                .create(options)
                .receive()
                .map { row -> row.value() }
    }
}