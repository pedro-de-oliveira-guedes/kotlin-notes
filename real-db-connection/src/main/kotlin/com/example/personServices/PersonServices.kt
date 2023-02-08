package com.example.personServices

import com.example.exceptionHandling.BadRequestException
import com.example.models.person.Person
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonServices {
    private val sysLogger: Logger = Logger.getLogger(PersonServices::class.java.name)

    private val idAutoGen: AtomicLong = AtomicLong()

    fun createNewPerson(personInfo: Person): Person {
        sysLogger.info("Creating new person.")

        return personInfo
    }

    fun getAllPeople(): List<Person> {
        sysLogger.info("Getting all people.")

        val people: MutableList<Person> = ArrayList()
        people.add(
            Person(
                1,
                "",
                21,
                "abubublé street"
            )
        )

        return people
    }

    fun getSinglePerson(id: Long): Person {
        sysLogger.info("Getting person by ID.")

        return Person(
                    1,
                    "Abubublé",
                    21,
                    "abubublé street"
                    )
    }

    fun updatePerson(id: Long, info: Person): Person {
        sysLogger.info("Updating a person's info by its ID.")

        return info
    }

    fun deletePerson(id: Long) {
        sysLogger.info("Deleting a person by its ID.")
    }
}