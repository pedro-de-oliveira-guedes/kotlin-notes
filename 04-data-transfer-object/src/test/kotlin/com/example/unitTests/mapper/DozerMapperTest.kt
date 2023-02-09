package com.example.unitTests.mapper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import br.com.erudio.unittests.mapper.mocks.MockPerson
import com.example.dataTransferObjects.personDto.PersonDto
import com.example.models.person.Person
import com.example.objectMapper.DozerMapper

class DozerMapperTest {

    var inputObject: MockPerson? = null

    @BeforeEach
    fun setUp() {
        inputObject = MockPerson()
    }

    @Test
    fun parseEntityToVOTest() {
        val output: PersonDto = DozerMapper.parseObject(
            origin = inputObject!!.mockEntity(),
            destine = PersonDto::class.java
        )
        assertEquals(0, output.id)
        assertEquals("Name Test0", output.name)
        assertEquals("Address Test0", output.address)
        assertEquals(0, output.age)
    }

    @Test
    fun parseEntityListToVOListTest() {
        val outputList: List<PersonDto> =
            DozerMapper.parseObjectList(inputObject!!.mockEntityList(), PersonDto::class.java)

        val outputZero: PersonDto = outputList[0]

        assertEquals(0, outputZero.id)
        assertEquals("Name Test0", outputZero.name)
        assertEquals("Address Test0", outputZero.address)
        assertEquals(0, outputZero.age)

        val outputSeven: PersonDto = outputList[7]
        assertEquals(7.toLong(), outputSeven.id)
        assertEquals("Name Test7", outputSeven.name)
        assertEquals("Address Test7", outputSeven.address)
        assertEquals(7, outputSeven.age)

        val outputTwelve: PersonDto = outputList[12]
        assertEquals(12.toLong(), outputTwelve.id)
        assertEquals("Name Test12", outputTwelve.name)
        assertEquals("Address Test12", outputTwelve.address)
        assertEquals(12, outputTwelve.age)
    }

    @Test
    fun parseVOToEntityTest() {

        val output: Person = DozerMapper.parseObject(inputObject!!.mockVO(), Person::class.java)

        assertEquals(0, output.id)
        assertEquals("Name Test0", output.name)
        assertEquals("Address Test0", output.address)
        assertEquals(0, output.age)
    }

    @Test
    fun parserVOListToEntityListTest() {

        val outputList: List<Person> = DozerMapper.parseObjectList(inputObject!!.mockVOList(), Person::class.java)

        val outputZero: Person = outputList[0]
        assertEquals(0, outputZero.id)
        assertEquals("Name Test0", outputZero.name)
        assertEquals("Address Test0", outputZero.address)
        assertEquals(0, outputZero.age)

        val outputSeven: Person = outputList[7]
        assertEquals(7, outputSeven.id)
        assertEquals("Name Test7", outputSeven.name)
        assertEquals("Address Test7", outputSeven.address)
        assertEquals(7, outputSeven.age)

        val outputTwelve: Person = outputList[12]
        assertEquals(12, outputTwelve.id)
        assertEquals("Name Test12", outputTwelve.name)
        assertEquals("Address Test12", outputTwelve.address)
        assertEquals(12, outputTwelve.age)
    }
}