package io.github.jithinsethu.hibernatevalidation.utils.mappers;

import io.github.jithinsethu.hibernatevalidation.dtos.BookCreateDTO;
import io.github.jithinsethu.hibernatevalidation.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MappingConfig.class)
public interface BookMapper {

    BookCreateDTO toBookDTO(Book book);

    @Mapping(source = "bookCreateDTO.bookName", target = "name")
    @Mapping(source = "bookCreateDTO.authorName", target = "author.name")
    Book fromBookDTO(BookCreateDTO bookCreateDTO);
}
