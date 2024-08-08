package com.example.LMS.dtos.responseDTOs;

import com.example.LMS.enums.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {
    String title;

    Genre genre;

    int noOfPages;

    int cost;

    AuthorResponse authorResponse;
}
