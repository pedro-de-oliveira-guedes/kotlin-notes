package com.example.person

import java.util.concurrent.atomic.AtomicLong

data class Person(
    var id: Long,
    var name: String,
    var age: Int,
    var address: String,
)