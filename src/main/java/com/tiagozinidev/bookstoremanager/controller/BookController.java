package com.tiagozinidev.bookstoremanager.controller;

import com.tiagozinidev.bookstoremanager.entity.Book;
import com.tiagozinidev.bookstoremanager.repository.BookRepository;
import com.tiagozinidev.bookstoremanager.service.BookService;
import dto.BookDTO;
import dto.MessageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {


    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public MessageResponseDTO create(@RequestBody @Valid BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }

    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

}