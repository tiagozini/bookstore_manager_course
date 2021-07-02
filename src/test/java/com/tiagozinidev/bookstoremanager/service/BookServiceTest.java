package com.tiagozinidev.bookstoremanager.service;

import com.tiagozinidev.bookstoremanager.entity.Book;
import com.tiagozinidev.bookstoremanager.exception.BookNotFoundException;
import com.tiagozinidev.bookstoremanager.repository.BookRepository;
import com.tiagozinidev.bookstoremanager.utils.BookUtils;
import dto.BookDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGivenExistingIdThenReturnThisBook() throws BookNotFoundException {
        Book expectedFoundBook = BookUtils.createFakeBook();
        when(bookRepository.findById(expectedFoundBook.getId())).thenReturn(Optional.of(expectedFoundBook));

        BookDTO bookDTO = bookService.findById(expectedFoundBook.getId());

        assertEquals(expectedFoundBook.getName(), bookDTO.getName());
        assertEquals(expectedFoundBook.getIsbn(), bookDTO.getIsbn());
        assertEquals(expectedFoundBook.getPublisherName(), bookDTO.getPublisherName());
    }

    @Test
    void testGivenUnexistingIdThenNotFoundThrowAnException() {
        var invalidId = 10L;
        when(bookRepository.findById(invalidId)).thenReturn(Optional.ofNullable(any(Book.class)));
        assertThrows(BookNotFoundException.class, () -> bookService.findById(invalidId));
    }
}
