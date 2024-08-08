package com.example.LMS.controllers;


import com.example.LMS.dtos.requestDTOs.StudentRequest;
import com.example.LMS.dtos.responseDTOs.StudentResponse;
import com.example.LMS.services.StudentService;
import com.example.LMS.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService ss;

    @PostMapping("/add")
    ResponseEntity addStudent(@RequestBody StudentRequest s){
        StudentResponse response = ss.addStudent(s);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    ResponseEntity getStudent(@RequestParam("id") int regNo){
        StudentResponse response = ss.getStudent(regNo);
        if(response!=null) {
            return new ResponseEntity(response, HttpStatus.FOUND);
        }

        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    // delete a student --> regNo

    @DeleteMapping("/delete")
    ResponseEntity deleteStudent(@RequestParam("id") int regNo){
        ss.deleteStudent(regNo);
        return new ResponseEntity("Successfully Deleted",HttpStatus.OK);
    }
    // update a age of the student -> regNo,age


    @PutMapping("/update-age")
    ResponseEntity updateAge(@RequestParam("id") int regNo, @RequestParam int age){
        StudentResponse response  = ss.updateAge(regNo,age);
        return new ResponseEntity(response,HttpStatus.OK);
    }


    // get all student in the db
    @GetMapping("/getAll")
    ResponseEntity getAll(){
        List<StudentResponse> response = ss.getAll();
        return new ResponseEntity(response,HttpStatus.FOUND);
    }


    // get list of all male student
    @GetMapping("/get-male")
    ResponseEntity getMale(){
        List<StudentResponse> response = ss.getMale();
        return new ResponseEntity(response,HttpStatus.FOUND);
    }




}
