package com.example.LMS.controllers;


import com.example.LMS.dtos.requestDTOs.AuthorRequest;
import com.example.LMS.dtos.responseDTOs.AuthorResponse;
import com.example.LMS.dtos.responseDTOs.BookResponse;
import com.example.LMS.exceptions.AuthorNotFoundException;
import com.example.LMS.models.Author;
import com.example.LMS.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService as;

    @PostMapping("/add")
    ResponseEntity addAuthor(@RequestBody AuthorRequest authorRequest){

        try {
            AuthorResponse authorResponse = as.addAuthor(authorRequest);
            return new ResponseEntity(authorResponse, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    // update the email id of an author
    @PutMapping("/update-email")
    ResponseEntity updateEmail(@PathVariable int id,@RequestParam String newEmail){
        AuthorResponse authorResponse = as.updateEmail(id,newEmail);
        return new ResponseEntity(authorResponse,HttpStatus.OK);
    }
    // give the names of all the books written by a particular author
    @GetMapping("/allBooksByAuthor")
    ResponseEntity allBooksByAuthor(@RequestParam int id){
        List<BookResponse> responses = as.allBooksByAuthor(id);
        return new ResponseEntity(responses,HttpStatus.FOUND);
    }


    // give the names of all author who have written more than 'x' number of books
    @GetMapping("/getNamesGreaterThanX/{x}")
    List<AuthorResponse> getNamesGreaterThanX(@PathVariable int x){
        List<AuthorResponse> responses = as.getNamesGreaterThanX(x);
        return responses;
    }


}
