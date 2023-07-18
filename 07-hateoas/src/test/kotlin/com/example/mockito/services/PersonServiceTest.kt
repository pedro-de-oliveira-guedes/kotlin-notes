package com.example.mockito.services

import com.example.repository.PersonRepository
import com.example.services.PersonService
import com.example.unittests.mapper.mocks.MockPerson
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
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
    fun create() {
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }
}