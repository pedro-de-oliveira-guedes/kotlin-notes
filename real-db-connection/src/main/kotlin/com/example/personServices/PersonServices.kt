package com.example.personServices

import com.example.exceptionHandling.badRequest.NotFoundException
import com.example.models.person.Person
import com.example.repositories.people.PeopleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonServices {
    private val sysLogger: Logger = Logger.getLogger(PersonServices::class.java.name)

    @Autowired
    private lateinit var peopleRepo : PeopleRepository

    fun createNewPerson(personInfo: Person): Person {
        sysLogger.info("Creating new person.")

        return peopleRepo.save(personInfo)
    }

    fun getAllPeople(): List<Person> {
        sysLogger.info("Getting all people.")

        return peopleRepo.findAll()
    }

    fun getSinglePerson(id: Long): Person {
        sysLogger.info("Getting person by ID.")

        return peopleRepo.findById(id).orElseThrow {
                                            NotFoundException("This person does not exists.")
                                        }
    }

    fun updatePerson(id: Long, info: Person): Person {
        sysLogger.info("Updating a person's info by its ID.")

        return info
    }

    fun deletePerson(id: Long) {
        sysLogger.info("Deleting a person by its ID.")
    }
}