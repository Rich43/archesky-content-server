package com.archesky.content.kafka

import com.archesky.content.configuration.ApplicationProperties
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.IntegerSerializer
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.logging.log4j.LogManager.getLogger
import reactor.core.publisher.Flux
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions
import reactor.kafka.sender.SenderRecord
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

class KafkaProducer(private val applicationProperties: ApplicationProperties) {
    private val sender: KafkaSender<Int, String>
    private val log = getLogger(this.javaClass.name)

    init {
        val props: MutableMap<String, Any> = HashMap()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = applicationProperties.kafkaUrl
        props[ProducerConfig.CLIENT_ID_CONFIG] = applicationProperties.kafkaTopic
        props[ProducerConfig.ACKS_CONFIG] = "all"
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = IntegerSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        val senderOptions = SenderOptions.create<Int, String>(props)

        sender = KafkaSender.create(senderOptions)
    }

    @Throws(InterruptedException::class)
    fun sendMessage(rowId: Int, message: String) {
        val latch = CountDownLatch(1)
        sender.send<Int>(Flux.range(rowId, 1)
                .map { SenderRecord.create(ProducerRecord(applicationProperties.kafkaTopic, rowId, message), rowId) })
                .doOnError { e -> log.error("Failed to send event to kafka, rowID: $rowId", e) }
                .subscribe {
                    log.info("Added event to kafka, rowId: $rowId");
                    latch.countDown()
                }
        latch.await(10, SECONDS)
    }

    fun close() {
        sender.close()
    }
}
