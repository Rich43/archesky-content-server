package com.archesky.content.service

import com.archesky.content.dto.Content
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.observables.ConnectableObservable
import org.reactivestreams.Publisher
import org.springframework.stereotype.Service

@Service
class ContentQueueService {
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

    fun push(content: Content) {
        emitter!!.onNext(content)
    }

    fun getPublisher(): Publisher<Content> {
        return publisher
    }
}
