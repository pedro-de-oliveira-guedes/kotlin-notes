package com.example.unit_tests.services

import com.example.exceptions.NullObjectException
import com.example.repository.BookRepository
import com.example.services.BookService
import com.example.unit_tests.mocks.MockBook
import java.util.Optional
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.math.exp

@ExtendWith(MockitoExtension::class)
class BookServiceTest {
    private lateinit var inputObject: MockBook;

    @InjectMocks
    private lateinit var service: BookService;

    @Mock
    private lateinit var repository: BookRepository;

    @BeforeEach
    fun setupMock() {
        this.inputObject = MockBook();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    fun findAll() {
        val list = inputObject.mockEntityList();

        `when`(repository.findAll()).thenReturn(list);

        val books = service.findAll();

        assertNotNull(books);
        assertEquals(6, books.size);

        val bookOne = books[0];
        assertNotNull(bookOne);
        assertNotNull(bookOne.key);

        assertEquals("Book 0", bookOne.title);
        assertEquals(5.0, bookOne.price);
        assertEquals("Author 0", bookOne.author);
        assertEquals(null, bookOne.launchDate);

        assertNotNull(bookOne);
        assertTrue( bookOne.links.toString() == "</api/v1/book/0>;rel=\"self\"" );

        val bookThree = books[3];
        assertNotNull(bookThree);
        assertNotNull(bookThree.key);

        assertEquals("Book 3", bookThree.title);
        assertEquals(11.0, bookThree.price);
        assertEquals("Author 3", bookThree.author);
        assertEquals(null, bookThree.launchDate);

        assertNotNull(bookThree);
        assertTrue( bookThree.links.toString() == "</api/v1/book/3>;rel=\"self\"" );

        val bookFive = books[5];
        assertNotNull(bookFive);
        assertNotNull(bookFive.key);

        assertEquals("Book 5", bookFive.title);
        assertEquals(15.0, bookFive.price);
        assertEquals("Author 5", bookFive.author);
        assertEquals(null, bookFive.launchDate);

        assertNotNull(bookFive);
        assertTrue( bookFive.links.toString() == "</api/v1/book/5>;rel=\"self\"" );
    }

//    @Test
//    fun findById() {
//        val book = inputObject.mockEntity();
//        book.id = 0;
//
//        `when`(repository.findById(0)).thenReturn(Optional.of(book));
//
//        val result = service.findById(0);
//
//        assertNotNull(result);
//        assertNotNull(result.key);
//
//        assertEquals("Book 0", result.title);
//        assertEquals(5.0, result.price);
//        assertEquals("Author 0", result.author);
//        assertEquals(null, result.launchDate);
//
//        assertNotNull(result);
//        assertTrue( result.links.toString() == "</api/v1/book/0>;rel=\"self\"" );
//    }

    @Test
    fun createNull() {
        val exception : Exception = assertThrows(
            NullObjectException::class.java
        ) { service.create(null); }

        val expectedMessage = "Cannot handle null value received.";
        val actualMessage = exception.message;

        assertTrue(actualMessage == expectedMessage);
    }

    @Test
    fun create() {
        val entity = inputObject.mockEntity();
        val persisted = entity.copy();

        `when`(repository.save(entity)).thenReturn(persisted);

        val vo = inputObject.mockVO();
        val result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.key);

        assertEquals("Book 0", result.title);
        assertEquals(5.0, result.price);
        assertEquals("Author 0", result.author);
        assertEquals(null, result.launchDate);

        assertNotNull(result);
        assertTrue( result.links.toString() == "</api/v1/book/0>;rel=\"self\"" );
    }

    @Test
    fun updateNull() {
        val exception : Exception = assertThrows(
            NullObjectException::class.java
        ) { service.update(null); }

        val expectedMessage = "Cannot handle null value received.";
        val actualMessage = exception.message;

        assertTrue(actualMessage == expectedMessage);
    }

    @Test
    fun update() {
        val entity = inputObject.mockEntity();
        val persisted = entity.copy();

        `when`(repository.findById(0)).thenReturn(Optional.of(entity));
        `when`(repository.save(entity)).thenReturn(persisted);

        val vo = inputObject.mockVO();
        val result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.key);

        assertEquals("Book 0", result.title);
        assertEquals(5.0, result.price);
        assertEquals("Author 0", result.author);
        assertEquals(null, result.launchDate);

        assertNotNull(result);
        assertTrue( result.links.toString() == "</api/v1/book/0>;rel=\"self\"" );
    }

    @Test
    fun delete() {
        val entity = inputObject.mockEntity();

        `when`(repository.findById(0)).thenReturn(Optional.of(entity));

        service.delete(0);
    }
}