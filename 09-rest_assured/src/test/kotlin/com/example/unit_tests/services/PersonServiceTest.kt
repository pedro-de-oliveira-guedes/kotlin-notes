package com.example.unit_tests.services

import com.example.exceptions.NullObjectException
import com.example.repository.PersonRepository
import com.example.services.PersonService
import com.example.unit_tests.mocks.MockPerson
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock

import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class PersonServiceTest {

    private lateinit var inputObject: MockPerson;

    @InjectMocks
    private lateinit var service: PersonService;

    @Mock
    private lateinit var repository: PersonRepository;

    @BeforeEach
    fun setUpMock() {
        this.inputObject = MockPerson();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    fun findAll() {
        val list = inputObject.mockEntityList();

        `when`(repository.findAll()).thenReturn(list);

        val persons = service.findAll();

        assertNotNull(persons);
        assertEquals(14, persons.size);

        val personOne = persons[1];
        assertNotNull(personOne);
        assertNotNull(personOne.key);

        assertEquals("First Name Test1", personOne.firstName);
        assertEquals("Last Name Test1", personOne.lastName);
        assertEquals("Address Test1", personOne.address);
        assertEquals("Female", personOne.gender);

        assertNotNull(personOne.links);
        assertTrue( personOne.links.toString() == "</api/v1/person/1>;rel=\"self\"" );

        val personFour = persons[4];
        assertNotNull(personFour);
        assertNotNull(personFour.key);

        assertEquals("First Name Test4", personFour.firstName);
        assertEquals("Last Name Test4", personFour.lastName);
        assertEquals("Address Test4", personFour.address);
        assertEquals("Male", personFour.gender);

        assertNotNull(personFour.links);
        assertTrue( personFour.links.toString() == "</api/v1/person/4>;rel=\"self\"" );

        val personSeven = persons[7];
        assertNotNull(personSeven);
        assertNotNull(personSeven.key);

        assertEquals("First Name Test7", personSeven.firstName);
        assertEquals("Last Name Test7", personSeven.lastName);
        assertEquals("Address Test7", personSeven.address);
        assertEquals("Female", personSeven.gender);

        assertNotNull(personSeven.links);
        assertTrue( personSeven.links.toString() == "</api/v1/person/7>;rel=\"self\"" );
    }

    @Test
    fun findById() {
        val person = inputObject.mockEntity(1);
        person.id = 1L;
        `when`(repository.findById(1)).thenReturn(Optional.of(person));

        val result = service.findById(1);

        assertNotNull(result);
        assertNotNull(result.key);

        assertEquals("First Name Test1", result.firstName);
        assertEquals("Last Name Test1", result.lastName);
        assertEquals("Address Test1", result.address);
        assertEquals("Female", result.gender);

        assertNotNull(result.links);
        assertTrue( result.links.toString() == "</api/v1/person/1>;rel=\"self\"" );
    }

    @Test
    fun createNull() {
        val exception: Exception = assertThrows(
            NullObjectException::class.java
        ) { service.create(null); };

        val expectedMessage = "Cannot handle null value received.";
        val actualMessage = exception.message;

        assertTrue(actualMessage == expectedMessage);
    }

    @Test
    fun create() {
        val entity = inputObject.mockEntity(1);

        val persisted = entity.copy();
        persisted.id = 1;

        `when`(repository.save(entity)).thenReturn(persisted);

        val vo = inputObject.mockVO(1);
        val result = service.create(vo);

        assertEquals("First Name Test1", result.firstName);
        assertEquals("Last Name Test1", result.lastName);
        assertEquals("Address Test1", result.address);
        assertEquals("Female", result.gender);

        assertNotNull(result.links);
        assertTrue( result.links.toString() == "</api/v1/person/1>;rel=\"self\"" );
    }

    @Test
    fun updateNull() {
        val exception: Exception = assertThrows(
            NullObjectException::class.java
        ) { service.update(null); };

        val expectedMessage = "Cannot handle null value received.";
        val actualMessage = exception.message;

        assertTrue(actualMessage == expectedMessage);
    }
    @Test
    fun update() {
        val entity = inputObject.mockEntity(1);

        val persisted = entity.copy();
        persisted.id = 1;

        `when`(repository.findById(1)).thenReturn(Optional.of(entity));
        `when`(repository.save(entity)).thenReturn(persisted);

        val vo = inputObject.mockVO(1);
        val result = service.update(vo);

        assertEquals("First Name Test1", result.firstName);
        assertEquals("Last Name Test1", result.lastName);
        assertEquals("Address Test1", result.address);
        assertEquals("Female", result.gender);

        assertNotNull(result.links);
        assertTrue( result.links.toString() == "</api/v1/person/1>;rel=\"self\"" );
    }

    @Test
    fun delete() {
        val entity = inputObject.mockEntity(1);

        `when`(repository.findById(1)).thenReturn(Optional.of(entity));

        service.delete(1);
    }
}