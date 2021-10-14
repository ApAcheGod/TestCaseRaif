package com.example.caseraif.repositories;

import com.example.caseraif.entities.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Sock, Long> {

    public Sock findByColorAndCottonPartEquals(String color, int cottonPart);
    public List<Sock> findByColorAndCottonPartGreaterThan(String color, int cottonPart);
    public List<Sock> findByColorAndCottonPartLessThan(String color, int cottonPart);
    public Sock findByColorAndCottonPart(String color, int cottonPart);

}
