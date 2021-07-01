package com.tiagozinidev.bookstoremanager.repository;

import com.tiagozinidev.bookstoremanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}