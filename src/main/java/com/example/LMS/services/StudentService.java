package com.example.LMS.services;

import com.example.LMS.Mapper.StudentMapper;
import com.example.LMS.dtos.requestDTOs.StudentRequest;
import com.example.LMS.dtos.responseDTOs.LibraryResponse;
import com.example.LMS.dtos.responseDTOs.StudentResponse;
import com.example.LMS.enums.CardStatus;
import com.example.LMS.models.LibraryCard;
import com.example.LMS.models.Student;
import com.example.LMS.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository sr;
    public StudentResponse addStudent(StudentRequest s) {
        // DTO >> Model
        Student student = StudentMapper.requestDtoToModel(s);

        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);
        Student savedStudent = sr.save(student);

        StudentResponse studentResponse = StudentMapper.modelToResponseDto(savedStudent);

        return studentResponse;
    }


    public StudentResponse getStudent(int regNo) {
       Optional<Student> response =  sr.findById(regNo);
        if(response.isPresent()){
            return StudentMapper.modelToResponseDto(response.get());
        }
        return null;
    }

    public void deleteStudent(int regNo) {
        sr.deleteById(regNo);
    }

    public StudentResponse updateAge(int regNo, int age) {
        Optional<Student> response = sr.findById(regNo);
        if(response.isPresent()){
            Student temp=response.get();
            temp.setAge(age);
            Student updatedStudent = sr.save(temp);
            return  StudentMapper.modelToResponseDto(updatedStudent);
        }
        return null;
    }

    public List<StudentResponse> getAll()
    {
        List<Student> students =  sr.findAll();
        List<StudentResponse> responses = new ArrayList<>();
        for(Student x:students){
            responses.add(StudentMapper.modelToResponseDto(x));
        }
        return responses;
    }

    public List<StudentResponse> getMale()
    {
        List<Student> students =  sr.getMale();
        List<StudentResponse> responses = new ArrayList<>();
        for(Student x:students){
            responses.add(StudentMapper.modelToResponseDto(x));
        }
        return responses;
    }
}
