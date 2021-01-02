package com.archesky.content.dto

import com.arangodb.springframework.annotation.Document
import com.arangodb.springframework.annotation.Relations
import org.springframework.data.annotation.Id
import java.util.*
import kotlin.reflect.KClass

@Document("content")
data class Content (
    @Id var name: String? = null,
    var displayName: String? = null,
    var published: Boolean = false,
    var latestRevision: ContentRevision? = null,
    var created: Date? = null,
    var updated: Date? = null,
    @Relations(
        edges=[ContentMapping::class],
        direction=Relations.Direction.OUTBOUND
    ) var contentRevision: List<ContentRevision>? = null
)
