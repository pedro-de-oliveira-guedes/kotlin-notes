package com.example.repository

import com.example.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository <Book, Long?>