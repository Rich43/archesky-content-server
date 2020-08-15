package com.archesky.content.service

import com.archesky.content.configuration.QUEUE_NAME
import com.archesky.content.dto.Content
import com.google.gson.Gson
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.observables.ConnectableObservable
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service
import javax.jms.ConnectionFactory


@Service
@EnableJms
class ContentQueueService(@Qualifier("jmsConnectionFactory") private val connectionFactory: ConnectionFactory) {
    private val publisher: Publisher<Content>
    private var emitter: ObservableEmitter<Content>? = null

    init {
        val observable: Observable<Content> = Observable.create {
            emitter = it
        }
        val connectableObservable: ConnectableObservable<Content> = observable.share().publish()
        connectableObservable.connect()
        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER)
    }

    @JmsListener(destination = QUEUE_NAME)
    private fun listener(message: String) {
        emitter!!.onNext(Gson().fromJson(message, Content::class.java))
    }

    fun getPublisher(): Publisher<Content> {
        return publisher
    }
}
