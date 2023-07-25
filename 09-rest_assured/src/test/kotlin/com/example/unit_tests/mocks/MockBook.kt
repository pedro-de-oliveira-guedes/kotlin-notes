package com.example.unit_tests.mocks

import com.example.data.vo.v1.BookVO
import com.example.model.Book
import java.util.Date

class MockBook {

    fun mockEntity() : Book {
        return mockEntity(0);
    }

    fun mockVO() : BookVO {
        return mockVO(0);
    }

    fun mockEntityList() : ArrayList<Book> {
        val books : ArrayList<Book> = ArrayList();
        for (number in 0 .. 5) {
            books.add(mockEntity(number.toLong()));
        }
        return books;
    }

    fun mockVOList() : ArrayList<BookVO> {
        val booksVO : ArrayList<BookVO> = ArrayList();
        for (number in 0 .. 5) {
            booksVO.add(mockVO(number.toLong()));
        }
        return booksVO;
    }

    private fun mockEntity(number: Long) : Book {
        val book = Book();

        book.id = number;
        book.title = "Book $number";
        book.price = (2 * number + 5).toDouble();
        book.author = "Author $number";
        book.launchDate = null;

        return book;
    }

    private fun mockVO(number: Long) : BookVO {
        val book = BookVO();

        book.key = number;
        book.title = "Book $number";
        book.price = (2 * number + 5).toDouble();
        book.author = "Author $number";
        book.launchDate = null;

        return book;
    }
}