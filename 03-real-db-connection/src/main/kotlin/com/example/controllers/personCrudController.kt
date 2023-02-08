package com.example.controllers

import com.example.models.person.Person
import com.example.personServices.PersonServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
@RequestMapping("/person")
class personCrudController {

    @Autowired
    private lateinit var service: PersonServices

    @PostMapping(value = ["/"],
                 produces = [MediaType.APPLICATION_JSON_VALUE],
                 consumes = [MediaType.APPLICATION_JSON_VALUE],)
    fun createPerson(@RequestBody info: Person): Person {
        try {
            return service.createNewPerson(info)
        }
        catch (err: Exception) {
            throw err
        }
    }

    @GetMapping(value = ["/"],
                produces = [MediaType.APPLICATION_JSON_VALUE],)
    fun getPeople(): List<Person> {
        try {
            return service.getAllPeople()
        }
        catch (err: Exception) {
            throw err
        }
    }

    @GetMapping(value = ["/{id}"],
                produces = [MediaType.APPLICATION_JSON_VALUE],)
    fun getPerson(@PathVariable(value = "id")id: Long): Person {
        try {
            return service.getSinglePerson(id)
        }
        catch (err: Exception) {
            throw err
        }
    }

    @PutMapping(value = ["/{id}"],
                consumes = [MediaType.APPLICATION_JSON_VALUE],
                produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updatePerson(@PathVariable(value = "id") id: Long, @RequestBody info: Person): Person {
        try {
            return service.updatePerson(id, info)
        }
        catch (err: Exception) {
            throw err
        }
    }

    @DeleteMapping(value = ["/{id}"],)
    fun deletePerson(@PathVariable(value = "id") id: Long) : ResponseEntity<*> {
        try {
            service.deletePerson(id)

            return ResponseEntity.noContent().build<Any>()
        }
        catch (err: Exception) {
            throw err
        }
    }
}