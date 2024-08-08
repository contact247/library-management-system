package com.example.LMS.services;

import com.example.LMS.Mapper.AuthorMapper;
import com.example.LMS.Mapper.BookMapper;
import com.example.LMS.dtos.requestDTOs.BookRequest;
import com.example.LMS.dtos.responseDTOs.AuthorResponse;
import com.example.LMS.dtos.responseDTOs.BookResponse;
import com.example.LMS.enums.Genre;
import com.example.LMS.exceptions.AuthorNotFoundException;
import com.example.LMS.models.Author;
import com.example.LMS.models.Book;
import com.example.LMS.repositories.AuthorRepository;
import com.example.LMS.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Service
public class BookService {

    @Autowired
    AuthorRepository ar;

    @Autowired
    BookRepository br;

    @PostMapping("/add")
    public BookResponse addBook(BookRequest bookRequest) {
        Optional<Author> response = ar.findById(bookRequest.getAuthorId());
        if(response.isEmpty()){
            throw new AuthorNotFoundException("Invalid Author Id!!");
        }
        Author author = response.get();
        Book book = BookMapper.requestDtoToModel(bookRequest);
        book.setAuthor(author);
        Book savedBook = br.save(book);
        author.getBooks().add(savedBook);
        ar.save(author);
        return BookMapper.modelToresponseDto(savedBook);
    }

    public void deleteABook(int id) {
        br.deleteById(id);
    }

    public List<BookResponse> getAllByGenre(Genre g) {
        List<Book> response = br.findByGenre(g);
        List<BookResponse> ans = new ArrayList<>();
        for(Book x: response){
            ans.add(BookMapper.modelToresponseDto(x));
        }
        return ans;
    }

    public List<BookResponse> getAllBooksByGenreAndCostGTX(Genre g, int cost) {
        List<Book> response = br.findByGenreAndCostGreaterThan(g, cost);

        List<BookResponse> ans = new ArrayList<>();
        for(Book x: response){
            ans.add(BookMapper.modelToresponseDto(x));
        }
        return ans;
    }

    public List<BookResponse> getBooksBetweenAandBPages(int a, int b) {
        List<Book> response = br.findByNoOfPagesBetween(a,b);
        List<BookResponse> ans = new ArrayList<>();
        for(Book x: response){
            ans.add(BookMapper.modelToresponseDto(x));
        }
        return ans;
    }

    public List<AuthorResponse> allAuthorWrittenAGenre(Genre g) {
        List<Book> response = br.findByGenre(g);
        Map<String, AuthorResponse> tans = new HashMap<>();

        for(Book x: response){
            tans.put(x.getAuthor().getName(), AuthorMapper.modelToResponseDto(x.getAuthor()));
        }
        List<AuthorResponse> ans = new ArrayList<>();
        for(AuthorResponse t: tans.values()){
            ans.add(t);
        }
        return ans;
    }

    public BookResponse addBookV2(BookRequest bookRequest) {
        Optional<Author> response = ar.findById(bookRequest.getAuthorId());
        if(response.isEmpty()){
            throw new AuthorNotFoundException("Author Not Present");
        }
        Author author = response.get();
        Book book = BookMapper.requestDtoToModel(bookRequest);
        book.setAuthor(author);
        Book savedBook = br.save(book);

        author.getBooks().add(savedBook);
        ar.save(author);
        return BookMapper.modelToresponseDto(savedBook);
    }
}
