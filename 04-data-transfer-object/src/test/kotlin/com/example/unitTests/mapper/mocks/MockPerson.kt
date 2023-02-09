package br.com.erudio.unittests.mapper.mocks

import java.util.ArrayList
import com.example.dataTransferObjects.personDto.PersonDto
import com.example.models.person.Person

class MockPerson {
    fun mockEntity(): Person {
        return mockEntity(0)
    }

    fun mockVO(): PersonDto {
        return mockVO(0)
    }

    fun mockEntityList(): ArrayList<Person> {
        val persons: ArrayList<Person> = ArrayList<Person>()
        for (i in 0..13) {
            persons.add(mockEntity(i))
        }
        return persons
    }

    fun mockVOList(): ArrayList<PersonDto> {
        val persons: ArrayList<PersonDto> = ArrayList()
        for (i in 0..13) {
            persons.add(mockVO(i))
        }
        return persons
    }

    fun mockEntity(number: Int): Person {
        val person = Person(
            id = number.toLong(),
            name = "Name Test$number",
            address = "Address Test$number",
            age = number,
        )

        return person
    }

    fun mockVO(number: Int): PersonDto {
        val person = PersonDto(
            id = number.toLong(),
            name = "Name Test$number",
            address = "Address Test$number",
            age = number,
        )

        return person
    }
}