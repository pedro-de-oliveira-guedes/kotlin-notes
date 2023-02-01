package com.example.services.person

import com.example.models.person.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonServices {

    private val sysLogger: Logger = Logger.getLogger(PersonServices::class.java.name)

    private val autoId: AtomicLong = AtomicLong()

    fun getById(id: Long): Person {
        sysLogger.info("Searching person with the provided ID!")

        val returnedPerson = Person(id, "Mockerson Mockelson de Mockers", 17, "Mooocked Street, Mockest Wirginia, Mockers")

        return returnedPerson
    }

    fun getAll(): List<Person> {
        var people: MutableList<Person> = ArrayList()

        for (id in 1..10)
            people.add(this.getById(id.toLong()))

        return people
    }
}