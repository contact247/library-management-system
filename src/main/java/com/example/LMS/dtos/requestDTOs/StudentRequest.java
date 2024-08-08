package com.example.LMS.dtos.requestDTOs;


import com.example.LMS.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequest {

    String name;

    String email;

    int age;

    Gender gender;


}
