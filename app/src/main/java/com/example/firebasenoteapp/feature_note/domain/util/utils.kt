package com.example.firebasenoteapp.feature_note.domain.util

import kotlin.random.Random
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties

fun Any.toMap(): Map<String, Any> {
    return this::class.memberProperties.filter { it.visibility == KVisibility.PUBLIC }
        .associate { it.name to it.getter.call(this)!! }
}

fun generateRandomIntId(): Int {
    return Random.nextInt(Int.MAX_VALUE)
}
