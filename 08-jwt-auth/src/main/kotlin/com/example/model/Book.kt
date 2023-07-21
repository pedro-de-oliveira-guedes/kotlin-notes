package com.example.model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "book")
data class Book (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "author", nullable = false, length = 120)
    var author: String = "",

    @Column(name = "launch_date", nullable = true)
    var launchDate: Date? = Date(),

    @Column(name = "price", nullable = false)
    var price: Double = 0.0,

    @Column(name = "title", nullable = false, length = 150)
    var title: String = "",
)