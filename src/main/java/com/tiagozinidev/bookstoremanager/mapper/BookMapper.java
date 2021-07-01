package com.tiagozinidev.bookstoremanager.mapper;

import com.tiagozinidev.bookstoremanager.entity.Book;
import dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    Book toModel(BookDTO bookDTO);

    BookDTO toDTO(Book book);
}
