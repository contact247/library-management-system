package com.example.LMS.Mapper;

import com.example.LMS.dtos.requestDTOs.StudentRequest;
import com.example.LMS.dtos.responseDTOs.LibraryResponse;
import com.example.LMS.dtos.responseDTOs.StudentResponse;
import com.example.LMS.models.Student;

public class StudentMapper {
    public static Student requestDtoToModel(StudentRequest studentRequest){
        Student student = Student.builder()
                .name(studentRequest.getName())
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .build();
        return  student;
    }

    public static StudentResponse modelToResponseDto(Student student){

        LibraryResponse libraryResponse = LibraryResponse.builder()
                .cardNo(student.getLibraryCard().getCardNo())
                .issueDate(student.getLibraryCard().getIssueDate())
                .status(student.getLibraryCard().getStatus())
                .build();

        StudentResponse studentResponse = StudentResponse.builder()
                .libraryResponse(libraryResponse)
                .email(student.getEmail())
                .message("You are registered successfully!!")
                .name(student.getName())
                .build();

        return studentResponse;
    }
}
