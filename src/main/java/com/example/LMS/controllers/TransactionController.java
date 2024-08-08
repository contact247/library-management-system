package com.example.LMS.controllers;


import com.example.LMS.dtos.responseDTOs.TransactionResponse;
import com.example.LMS.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook/bookId/{bookId}/regNo/{regNo}")
    public ResponseEntity issueBook(@PathVariable  int bookId,@PathVariable int regNo){
        try {
            TransactionResponse response = transactionService.issueBook(bookId, regNo);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/returnBook/{id}")
    public ResponseEntity returnBook(@PathVariable("id") int id){
        try{
         TransactionResponse response =  transactionService.returnBook(id);
         return new ResponseEntity(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
