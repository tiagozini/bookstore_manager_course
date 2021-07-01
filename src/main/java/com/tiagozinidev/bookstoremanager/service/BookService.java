package com.tiagozinidev.bookstoremanager.service;

import com.tiagozinidev.bookstoremanager.entity.Book;
import com.tiagozinidev.bookstoremanager.repository.BookRepository;
import dto.MessageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(Book book) {
        Book bookSaved = bookRepository.save(book);
        return MessageResponseDTO.builder()
                .message("Book created with ID " + bookSaved.getId())
                .build();
    }
}
