package com.tiagozinidev.bookstoremanager.service;

import com.tiagozinidev.bookstoremanager.entity.Book;
import com.tiagozinidev.bookstoremanager.mapper.BookMapper;
import com.tiagozinidev.bookstoremanager.repository.BookRepository;
import dto.BookDTO;
import dto.MessageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiagozinidev.bookstoremanager.exception.BookNotFoundException;

import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(BookDTO bookDTO) {
        Book bookToSave = bookMapper.toModel(bookDTO);
        Book bookSaved = bookRepository.save(bookToSave);
        return MessageResponseDTO.builder()
                .message("Book created with ID " + bookSaved.getId())
                .build();
    }

    public BookDTO findById(Long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return bookMapper.toDTO(book);
    }
}
