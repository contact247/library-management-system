package com.example.LMS.Mapper;

import com.example.LMS.dtos.requestDTOs.BookRequest;
import com.example.LMS.dtos.responseDTOs.AuthorResponse;
import com.example.LMS.dtos.responseDTOs.BookResponse;
import com.example.LMS.models.Book;

public class BookMapper {

    public static Book requestDtoToModel(BookRequest bookRequest){
        Book book = Book.builder()
                .noOfPages(bookRequest.getNoOfPages())
                .title(bookRequest.getTitle())
                .genre(bookRequest.getGenre())
                .cost(bookRequest.getCost())
                .build();

        return book;
    }

    public static BookResponse modelToresponseDto(Book book){

        AuthorResponse authorResponse = AuthorMapper.modelToResponseDto(book.getAuthor());

        BookResponse bookResponse = BookResponse.builder()
                .authorResponse(authorResponse)
                .cost(book.getCost())
                .genre(book.getGenre())
                .noOfPages(book.getNoOfPages())
                .title(book.getTitle())
                .build();

        return  bookResponse;
    }


}
