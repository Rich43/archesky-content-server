package com.archesky.content.dto

data class RevisionInput(
    val content: String,
    val summary: String? = null,
    val html: Boolean
)
