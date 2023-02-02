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

    private val database: MutableList<Person> = ArrayList()

    fun getById(id: Long): List<Person> {
        sysLogger.info("Searching person with the provided ID!")

        var returnedPerson = database.filter { p -> p.id == id }

        return returnedPerson
    }

    fun getAll(): List<Person> {
        sysLogger.info("Querying every person registered in the Database.")

        return database
    }

    fun registerNewPerson(person: Person): Person {
        sysLogger.info("Registering new person into the database.")

        this.database.add(person)

        return person
    }
}