package com.archesky.content.service

import com.archesky.content.dto.ContentRevision
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.observables.ConnectableObservable
import org.reactivestreams.Publisher
import org.springframework.stereotype.Service

@Service
class ContentQueueService {
    private val publisher: Publisher<ContentRevision>
    private var emitter: ObservableEmitter<ContentRevision>? = null

    init {
        val observable: Observable<ContentRevision> = Observable.create {
            emitter = it
        }
        val connectableObservable: ConnectableObservable<ContentRevision> = observable.share().publish()
        connectableObservable.connect()
        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun push(contentRevision: ContentRevision) {
        emitter!!.onNext(contentRevision)
    }

    fun getPublisher(): Publisher<ContentRevision> {
        return publisher
    }
}
