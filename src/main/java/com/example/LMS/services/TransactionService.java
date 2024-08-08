package com.example.LMS.services;

import com.example.LMS.Mapper.TransactionMapper;
import com.example.LMS.dtos.responseDTOs.TransactionResponse;
import com.example.LMS.enums.TransactionStatus;
import com.example.LMS.enums.TransactionType;
import com.example.LMS.exceptions.BookException;
import com.example.LMS.exceptions.StudentNotFound;
import com.example.LMS.exceptions.TransactionNotFoundException;
import com.example.LMS.models.Book;
import com.example.LMS.models.Student;
import com.example.LMS.models.Transaction;
import com.example.LMS.repositories.BookRepository;
import com.example.LMS.repositories.StudentRepository;
import com.example.LMS.repositories.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    //@Autowired
  //  JavaMailSender javaMailSender;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TransactionRepo transactionRepo;

    public TransactionResponse issueBook(int bookId, int regNo) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new BookException("Book Not Found");
        }
        Optional<Student> optionalStudent = studentRepository.findById(regNo);
        if(optionalStudent.isEmpty()){
            throw new StudentNotFound("Student Not Found");
        }


        Book book = optionalBook.get();
        if(book.isIssued()){
            throw new BookException("Book Already issued!!");
        }

        book.setIssued(true);
        Student student = optionalStudent.get();

        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionType(TransactionType.ISSUE);
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setBook(book);
        transaction.setLibraryCard(student.getLibraryCard());
        Transaction savedTransaction = transactionRepo.save(transaction);

//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        String message = "Hello "+student.getName()+" .\n Book is issued. \nBook name : "+ book.getTitle()+" \nTransaction id: "+transaction.getId();
//        simpleMailMessage.setSubject("Issued");
//        simpleMailMessage.setText(message);
//        simpleMailMessage.setTo(student.getEmail());
//        simpleMailMessage.setFrom("email@outlook.com");
//        javaMailSender.send(simpleMailMessage);

        book.getTransactions().add(savedTransaction);
        bookRepository.save(book);
        student.getLibraryCard().getTransactions().add(savedTransaction);
        studentRepository.save(student);

        return TransactionMapper.modelToResponseDto(savedTransaction);
    }

    public TransactionResponse returnBook(int id) {
        Optional<Transaction> optionalTransaction =  transactionRepo.findById(id);
        if(optionalTransaction.isEmpty()){
            throw new TransactionNotFoundException("Transaction not found");
        }

        Book book = optionalTransaction.get().getBook();
        if(!book.isIssued()){
            throw new BookException("Book not issued");
        }

        book.setIssued(false);
        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setLibraryCard(optionalTransaction.get().getLibraryCard());
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionType(TransactionType.RETURN);
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        Transaction savedTransaction = transactionRepo.save(transaction);
        book.getTransactions().add(savedTransaction);
        bookRepository.save(book);

        Student student = optionalTransaction.get().getLibraryCard().getStudent();
        student.getLibraryCard().getTransactions().add(savedTransaction);
        studentRepository.save(student);

        return TransactionMapper.modelToResponseDto(savedTransaction);
    }
}
