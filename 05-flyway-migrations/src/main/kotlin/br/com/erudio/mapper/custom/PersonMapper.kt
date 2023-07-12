package br.com.erudio.mapper.custom

import br.com.erudio.data.vo.v2.PersonVO
import br.com.erudio.model.Person
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonMapper {
    fun mapEntityToVO(person: Person): PersonVO {
        val vo = PersonVO();

        vo.id = person.id;
        vo.firstName = person.firstName;
        vo.lastName = person.lastName;
        vo.address = person.address;
        vo.gender = person.gender;
        vo.birthDate = Date();

        return vo;
    }

    fun mapEntityListToVO(people: List<Person>): ArrayList<PersonVO> {
        var voList = ArrayList<PersonVO>();

        for (person in people)
            voList.add(mapEntityToVO(person));

        return  voList;
    }

    fun mapVOToEntity(personVO: PersonVO): Person {
        val person = Person();

        person.id = person.id;
        person.firstName = person.firstName;
        person.lastName = person.lastName;
        person.address = person.address;
        person.gender = person.gender;
        // person.birthDate = Date();

        return person;
    }


    fun mapVOListToEntity(peopleVO: List<PersonVO>): ArrayList<Person> {
        var entityList = ArrayList<Person>();

        for (personVO in peopleVO)
            entityList.add(mapVOToEntity(personVO));

        return  entityList;
    }
}