package com.example.LMS.models;


import com.example.LMS.enums.Gender;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
//@Table(name = "student_new")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int regNo;

  // @Column(name="student_name")
    @Column(nullable = false)
    String name;

    @Column(unique = true,nullable = false)
    String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Gender gender;

    @Column(nullable = false)
    int age;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    LibraryCard libraryCard;

}
