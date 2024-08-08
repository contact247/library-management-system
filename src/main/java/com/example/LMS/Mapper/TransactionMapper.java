package com.example.LMS.Mapper;

import com.example.LMS.dtos.responseDTOs.TransactionResponse;
import com.example.LMS.models.Transaction;

public class TransactionMapper {

    public static TransactionResponse modelToResponseDto(Transaction transaction){
        TransactionResponse transactionResponse = TransactionResponse.builder()
                .transactionNumber(transaction.getTransactionNumber())
                .transactionDate(transaction.getTransactionDate())
                .id(transaction.getId())
                .transactionStatus(transaction.getTransactionStatus())
                .transactionType(transaction.getTransactionType())
                .authorName(transaction.getBook().getAuthor().getName())
                .bookTitle(transaction.getBook().getTitle())
                .libraryCardNumber(transaction.getLibraryCard().getCardNo())
                .regNo(transaction.getLibraryCard().getStudent().getRegNo())
                .build();

        return transactionResponse;
    }
}
