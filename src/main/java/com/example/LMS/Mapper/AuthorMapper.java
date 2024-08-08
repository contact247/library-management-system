package com.example.LMS.Mapper;

import com.example.LMS.dtos.requestDTOs.AuthorRequest;
import com.example.LMS.dtos.responseDTOs.AuthorResponse;
import com.example.LMS.models.Author;

public class AuthorMapper {

    public static Author requestDtoToModel(AuthorRequest authorRequest){
        Author author = Author.builder()
                .age(authorRequest.getAge())
                .email(authorRequest.getEmail())
                .name(authorRequest.getName())
                .build();

        return author;
    }

    public static AuthorResponse modelToResponseDto(Author author){
        AuthorResponse authorResponse = AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .age(author.getAge())
                .build();

        return authorResponse;
    }

}
