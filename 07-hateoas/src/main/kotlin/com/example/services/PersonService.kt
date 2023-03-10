package com.example.services

import com.example.controller.PersonController
import com.example.data.vo.v1.PersonVO
import com.example.exceptions.ResourceNotFoundException
import com.example.mapper.DozerMapper
import com.example.model.Person
import com.example.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<PersonVO> {
        logger.info("Finding all people!")
        val persons = repository.findAll()
        return DozerMapper.parseListObjects(persons, PersonVO::class.java)
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding one person!")
        val person = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        val selfRel = linkTo(PersonController::class.java).slash(id).withSelfRel()

        return DozerMapper.parseObject(person, PersonVO::class.java).add(selfRel)
    }

    fun create(person: PersonVO) : PersonVO {
        logger.info("Creating one person with name ${person.firstName}!")
        val entity: Person = DozerMapper.parseObject(person, Person::class.java)
        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun update(person: PersonVO) : PersonVO {
        logger.info("Updating one person with ID ${person.key}!")
        val entity = repository.findById(person.key)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender
        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun delete(id: Long) {
        logger.info("Deleting one person with ID $id!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }
}