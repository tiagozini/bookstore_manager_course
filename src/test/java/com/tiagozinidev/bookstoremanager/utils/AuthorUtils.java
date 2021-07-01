package com.tiagozinidev.bookstoremanager.utils;

import com.github.javafaker.Faker;
import com.tiagozinidev.bookstoremanager.entity.Author;
import dto.AuthorDTO;

public class AuthorUtils {

    private static final Faker faker = Faker.instance();

    public static AuthorDTO createFakeAuthorDTO() {
        return AuthorDTO.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .age(faker.number().numberBetween(0, 100))
                .build();
    }

    public static Author createFakeAuthor() {
        return Author.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .age(faker.number().numberBetween(0, 100))
                .build();
    }
}
