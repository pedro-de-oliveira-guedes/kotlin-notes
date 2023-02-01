package com.example.services.person

import com.example.models.person.Person

class PersonServices {
    fun getById(id: Long): Person {
        val returnedPerson = Person(id, "Mockerson Mockelson de Mockers", 17, "Mooocked Street, Mockest Wirginia, Mockers")

        return returnedPerson
    }
}