package com.example.LMS.models;

import com.example.LMS.enums.TransactionStatus;
import com.example.LMS.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String transactionNumber;

    @CreationTimestamp
    Date transactionDate;

    @Enumerated(EnumType.STRING)
    TransactionStatus transactionStatus;

    @Enumerated(EnumType.STRING)
    TransactionType transactionType;

    @ManyToOne
    @JoinColumn
    Book book;

    @ManyToOne
    @JoinColumn
    LibraryCard libraryCard;
}
