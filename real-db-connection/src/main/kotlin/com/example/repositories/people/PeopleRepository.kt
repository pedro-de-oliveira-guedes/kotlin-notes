package com.example.repositories.people

import com.example.models.person.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PeopleRepository : JpaRepository<Person, Long?>