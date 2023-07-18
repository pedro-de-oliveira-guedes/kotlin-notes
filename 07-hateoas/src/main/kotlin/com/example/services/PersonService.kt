package com.example.services

import com.example.controller.PersonController
import com.example.data.vo.v1.PersonVO
import com.example.exceptions.NullObjectException
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
        var personsVO = DozerMapper.parseListObjects(persons, PersonVO::class.java);

        for (person in personsVO) {
            val withSelfRel = linkTo(PersonController::class.java).slash(person.key).withSelfRel();
            person.add(withSelfRel);
        }

        return personsVO;
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding one person, with ID $id!")
        var person = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") };
        val personVO : PersonVO = DozerMapper.parseObject(person, PersonVO::class.java);
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel();
        return personVO.add(withSelfRel);
    }

    fun create(person: PersonVO?) : PersonVO {
        if (person == null) throw NullObjectException();

        logger.info("Creating one person with name ${person.firstName}!")
        var entity: Person = DozerMapper.parseObject(person, Person::class.java)
        val personVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java);
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel();

        return personVO.add(withSelfRel);
    }

    fun update(person: PersonVO?) : PersonVO {
        if (person == null) throw NullObjectException();

        logger.info("Updating one person with ID ${person.key}!")
        val entity = repository.findById(person.key)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender
        val personVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java);
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel();

        return personVO.add(withSelfRel);
    }

    fun delete(id: Long) {
        logger.info("Deleting one person with ID $id!")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }
}