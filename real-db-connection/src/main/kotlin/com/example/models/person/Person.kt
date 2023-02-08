package com.example.models.person

import jakarta.persistence.*

@Entity
@Table(name = "people")
class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "name", nullable = false, length = 100)
    var name: String,

    @Column(name = "age", nullable = false)
    var age: Int,

    @Column(name = "address", nullable = false)
    var address: String,
)