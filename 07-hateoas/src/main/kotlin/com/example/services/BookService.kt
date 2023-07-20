package com.example.services

import com.example.data.vo.v1.BookVO
import com.example.data.vo.v1.PersonVO
import com.example.exceptions.NullObjectException
import com.example.exceptions.ResourceNotFoundException
import com.example.mapper.DozerMapper
import com.example.model.Book
import com.example.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BookService {
    @Autowired
    private lateinit var repository: BookRepository;

    private val logger = Logger.getLogger(BookService::class.java.name);

    fun findAll(): List<BookVO> {
        logger.info("Retrieving all registered books");

        val books = repository.findAll();
        var booksVO = DozerMapper.parseListObjects(books, BookVO::class.java);

//        for (book in booksVO) {
//            val selfRel = linkTo(BookC)
//        }

        return booksVO;
    }

    fun findById(id: Long) : BookVO {
        logger.info("Retrieving book with ID \"${id}\"");

        val book = repository.findById(id);
        var bookVO = DozerMapper.parseObject(book, BookVO::class.java);

        return bookVO;
    }

    fun create(book: BookVO?) : BookVO {
        if (book == null) throw NullObjectException();

        logger.info("Creating book with title \"${book.title}\"");

        val bookEntity = DozerMapper.parseObject(book, Book::class.java);
        var bookVO = DozerMapper.parseObject(repository.save(bookEntity), BookVO::class.java);

        return bookVO;
    }

    fun update(book: BookVO?) : BookVO {
        if (book == null) throw NullObjectException();

        logger.info("Updating book with ID \"${book.key}\"");

        val entity = repository.findById(book.key)
            .orElseThrow { ResourceNotFoundException("There is no such book") };

        entity.author = book.author;
        entity.launchDate = book.launchDate;
        entity.price = book.price;
        entity.title = entity.title;

        val bookVO = DozerMapper.parseObject(repository.save(entity), BookVO::class.java);

        return bookVO;
    }

    fun delete(id: Long) {
        val entity = repository.findById(id)
            .orElseThrow{ ResourceNotFoundException("There is no such book") };

        logger.info("Deleting book with ID \"${id}\"");

        repository.delete(entity);
    }
}