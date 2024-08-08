package com.example.LMS.controllers;


import com.example.LMS.dtos.requestDTOs.BookRequest;
import com.example.LMS.dtos.responseDTOs.AuthorResponse;
import com.example.LMS.dtos.responseDTOs.BookResponse;
import com.example.LMS.enums.Genre;
import com.example.LMS.models.Author;
import com.example.LMS.models.Book;
import com.example.LMS.repositories.AuthorRepository;
import com.example.LMS.services.AuthorService;
import com.example.LMS.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bs;


    @PostMapping("/add")
    ResponseEntity addBook(@RequestBody BookRequest bookRequest){

        try {
            BookResponse bookResponse = bs.addBook(bookRequest);
            return new ResponseEntity(bookResponse, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-V2")
    ResponseEntity addBookV2(@RequestBody BookRequest bookRequest){
        try{
            BookResponse response = bs.addBookV2(bookRequest);
            return new ResponseEntity(response,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // delete a book
    @DeleteMapping("/delete-a-book")
    String deleteABook(@RequestParam int id){
        bs.deleteABook(id);
        return "Book deleted successfully!!";
    }
    // give the names of all the books of a particular genre
    @GetMapping("/getAllByGenre/{g}")
    List<BookResponse> getAllByGenre(@PathVariable Genre g){
        List<BookResponse> responses = bs.getAllByGenre(g);
        return responses;
    }
    // give the names of all the books of a particular genre and cost greater than 500
    @GetMapping("/getAllBooksByGenreAndCostGTX")
    List<BookResponse> getAllBooksByGenreAndCostGTX(@RequestParam Genre g,@RequestParam int cost){
        List<BookResponse> responses = bs.getAllBooksByGenreAndCostGTX(g,cost);
        return responses;
    }

    // give the books having number of pages between 'a' and 'b'
    @GetMapping("/books-between-a-and-b-pages")
    List<BookResponse> getBooksBetweenAandBPages(@RequestParam int a,@RequestParam int b){
        List<BookResponse> responses = bs.getBooksBetweenAandBPages(a,b);
        return responses;
    }
    // give the names of all authors who write a particular genre
    @GetMapping("/all-author-written-a-genre/{g}")
    List<AuthorResponse> allAuthorWrittenAGenre(@PathVariable Genre g){
        List<AuthorResponse> responses = bs.allAuthorWrittenAGenre(g);
        return responses;
    }

}
