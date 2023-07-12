package br.com.erudio.controller

import br.com.erudio.data.vo.v1.PersonVO
import br.com.erudio.data.vo.v2.PersonVO as PersonVOV2
import br.com.erudio.services.PersonService
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

@RestController
@RequestMapping("/api/person")
class PersonController {


    @Autowired
    private lateinit var service: PersonService
    // var service: PersonService = PersonService()

    @GetMapping(value = ["/v1"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllV1(): List<PersonVO> {
        return service.findAll()
    }

    @GetMapping(value = ["/v2"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllV2(): List<PersonVOV2> {
        return service.findAllV2()
    }

    @GetMapping(value = ["/v1/{id}"],
                produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByIdv1(@PathVariable(value="id") id: Long): PersonVO {
        return service.findById(id)
    }

    @GetMapping(value = ["/v2/{id}"],
                produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByIdV2(@PathVariable(value="id") id: Long): PersonVOV2 {
        return service.findByIdV2(id)
    }

    @PostMapping(value = ["/v1"],
                consumes = [MediaType.APPLICATION_JSON_VALUE],
                produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createV1(@RequestBody person: PersonVO): PersonVO {
        return service.create(person)

    }

    @PostMapping(value = ["/v2"],
                consumes = [MediaType.APPLICATION_JSON_VALUE],
                produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createV2(@RequestBody person: PersonVOV2): PersonVOV2 {
        return service.createV2(person)

    }

    @PutMapping(value = ["/v1"],
                consumes = [MediaType.APPLICATION_JSON_VALUE],
                produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateV1(@RequestBody person: PersonVO): PersonVO {
        return service.update(person)
    }

    @PutMapping(value = ["/v2"],
                consumes = [MediaType.APPLICATION_JSON_VALUE],
                produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateV2(@RequestBody person: PersonVOV2): PersonVOV2 {
        return service.updateV2(person)
    }

    @DeleteMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable(value="id") id: Long) : ResponseEntity<*>{
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}