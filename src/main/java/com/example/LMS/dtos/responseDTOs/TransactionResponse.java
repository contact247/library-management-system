package com.example.LMS.dtos.responseDTOs;

import com.example.LMS.enums.Genre;
import com.example.LMS.enums.TransactionStatus;
import com.example.LMS.enums.TransactionType;
import com.example.LMS.models.Book;
import com.example.LMS.models.LibraryCard;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    int id;
    String transactionNumber;
    Date transactionDate;
    TransactionStatus transactionStatus;
    TransactionType transactionType;
    String bookTitle;
    String authorName;
    String libraryCardNumber;
    int regNo;
}
