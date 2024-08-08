package com.example.LMS.services;


import com.example.LMS.Mapper.AuthorMapper;
import com.example.LMS.Mapper.BookMapper;
import com.example.LMS.dtos.requestDTOs.AuthorRequest;
import com.example.LMS.dtos.responseDTOs.AuthorResponse;
import com.example.LMS.dtos.responseDTOs.BookResponse;
import com.example.LMS.exceptions.AuthorNotFoundException;
import com.example.LMS.models.Author;
import com.example.LMS.models.Book;
import com.example.LMS.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository ar;
    public AuthorResponse addAuthor(AuthorRequest authorRequest) {

        Author author = AuthorMapper.requestDtoToModel(authorRequest);
        Author savesAuthor = ar.save(author);
        return AuthorMapper.modelToResponseDto(savesAuthor);
    }

    public AuthorResponse updateEmail(int id,String newEmail) {
        Optional<Author> response = ar.findById(id);
        if(response.isEmpty()){
            throw new AuthorNotFoundException("Author Not Present");
        }

        Author oldAuthor = response.get();
        oldAuthor.setEmail(newEmail);
        Author savedAuthor = ar.save(oldAuthor);

        return AuthorMapper.modelToResponseDto(savedAuthor);

    }

    public List<BookResponse> allBooksByAuthor(int id) {
        Optional<Author> response = ar.findById(id);
        if(response.isEmpty()){
            throw new AuthorNotFoundException("Author Not Present");
        }
        Author author = response.get();
        List<BookResponse> ans = new ArrayList<>();
        for(Book x: author.getBooks()){
            ans.add(BookMapper.modelToresponseDto(x));
        }
        return ans;
    }

    public List<AuthorResponse> getNamesGreaterThanX(int x) {
        List<Author> response = ar.findAll();
        List<AuthorResponse> ans = new ArrayList<>();
        for(Author a: response){
            if(a.getBooks().size()>x){
                ans.add(AuthorMapper.modelToResponseDto(a));
            }
        }
        return ans;
    }
}
