package com.example.controller

import com.example.custom_exceptions.BadRequestExcept
import com.example.models.person.Person
import com.example.services.person.PersonServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class personController {

    @Autowired
    private lateinit var services: PersonServices

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getById(@PathVariable(value = "id") id: Long): List<Person> {
        try {
            return services.getById(id)
        }
        catch(err: Exception) {
            throw err
        }
    }

    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAll(): List<Person> {
        try {
            return services.getAll()
        }
        catch(err: Exception) {
            throw err
        }
    }

    @RequestMapping(method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE],)
    fun register(@RequestBody person: Person): Person {
        try {
            return services.registerNewPerson(person)
        }
        catch(err: Exception) {
            throw err
        }
    }

    @RequestMapping(value = ["/update/{id}"], method = [RequestMethod.PUT], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE],)
    fun update(@RequestBody properties: Person, @PathVariable(value = "id")id: Long): Person {
        try {
            return services.updatePerson(id, properties)
        }
        catch(err: Exception) {
            throw err
        }
    }

    @RequestMapping(value = ["/delete/{id}"])
    fun delete(@PathVariable(value = "id")id: Long) {
        try {
            services.deletePerson(id)
        }
        catch(err: Exception) {
            throw err
        }
    }
}