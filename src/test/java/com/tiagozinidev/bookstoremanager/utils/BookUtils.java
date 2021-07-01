package com.tiagozinidev.bookstoremanager.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.tiagozinidev.bookstoremanager.entity.Book;
import dto.AuthorDTO;
import dto.BookDTO;

public class BookUtils {

    private static final Faker faker = Faker.instance();

    public static BookDTO createFakeBookDTO() {
        return BookDTO.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(0,200))
                .chapters(faker.number().numberBetween(1,20))
                .isbn("8-596-52868-9")
                .publisherName(faker.book().publisher())
                .author(AuthorUtils.createFakeAuthorDTO())
                .build();
    }

    public static Book createFakeBook() {
        return Book.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(0,200))
                .chapters(faker.number().numberBetween(1,20))
                .isbn("8-596-52868-9")
                .publisherName(faker.book().publisher())
                .author(AuthorUtils.createFakeAuthor())
                .build();
    }

    public static String asJsonString(BookDTO bookDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            //objectMapper.configure(DeserializationFeature.FAIL_ON_
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,true);
            objectMapper.registerModule(new JavaTimeModule());

            return objectMapper.writeValueAsString(bookDTO);
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
