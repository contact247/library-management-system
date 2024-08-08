package com.example.LMS.repositories;

import com.example.LMS.enums.Genre;
import com.example.LMS.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByGenre(Genre g);
    List<Book> findByGenreAndCostGreaterThan(Genre g,int cost);
    List<Book> findByNoOfPagesBetween(int a,int b);
}
