package com.tiagozinidev.bookstoremanager.controller;

import com.tiagozinidev.bookstoremanager.service.BookService;
import com.tiagozinidev.bookstoremanager.utils.BookUtils;
import dto.BookDTO;
import dto.MessageResponseDTO;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Mapping;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import java.net.URI;
import java.net.URISyntaxException;

import static com.tiagozinidev.bookstoremanager.utils.BookUtils.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    public static final String BOOK_API_URL_PATH = "/api/v1/books";
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setCustomArgumentResolvers(
                        new PageableHandlerMethodArgumentResolver()
                ).setViewResolvers((s, locale) -> new MappingJackson2XmlView()).build();
    }

    @Test
    void testWhenPOStisCalledThenABookShouldBeCreated() throws Exception {
        BookDTO bookDTO = createFakeBookDTO();
        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
            .message("Book created with ID " + bookDTO.getId())
            .build();
        when(bookService.create(bookDTO)).thenReturn(expectedMessageResponse);

        mockMvc.perform(post(BOOK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(BookUtils.asJsonString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message",Is.is(expectedMessageResponse.getMessage())));
    }

    @Test
    void testWhenPOStisCalledWithInvalidISBNThenABookShouldBeCreated() throws Exception {
        BookDTO bookDTO = createFakeBookDTO();
        bookDTO.setIsbn("Invalid ISBN");

        mockMvc.perform(post(BOOK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(BookUtils.asJsonString(bookDTO)))
                .andExpect(status().isBadRequest());
    }

}
