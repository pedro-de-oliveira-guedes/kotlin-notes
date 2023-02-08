package com.example.models.person

import jakarta.persistence.*

@Entity
@Table(name = "people")
class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "name", nullable = false, length = 100)
    val name: String,

    @Column(name = "age", nullable = false)
    val age: Int,

    @Column(name = "address", nullable = false)
    val address: String,
)