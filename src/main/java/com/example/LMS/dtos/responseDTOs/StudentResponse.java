package com.example.LMS.dtos.responseDTOs;


import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {

    String name;

    String email;

    String message;

    LibraryResponse libraryResponse;

}
