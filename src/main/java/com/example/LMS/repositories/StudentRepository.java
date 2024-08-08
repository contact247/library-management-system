package com.example.LMS.repositories;

import com.example.LMS.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Query("select s from Student s where s.gender=MALE")
    List<Student> getMale();

}
