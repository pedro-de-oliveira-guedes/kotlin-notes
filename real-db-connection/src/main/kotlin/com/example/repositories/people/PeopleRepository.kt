package com.example.repositories.people

import com.example.models.person.Person
import org.springframework.data.jpa.repository.JpaRepository


interface PeopleRepository : JpaRepository<Person, Long?>