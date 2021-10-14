package com.example.caseraif.services;

import com.example.caseraif.entities.Sock;
import com.example.caseraif.repositories.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SocksService {

    private SocksRepository repository;

    @Autowired
    public SocksService(SocksRepository repository) {
        this.repository = repository;
    }

    public Sock find(String color, int cottonPart){
        return repository.findByColorAndCottonPart(color, cottonPart);
    }

    public int findByOperation(String color, String operation, int cottonPart) {

        if (color.isBlank() || operation.isBlank() || cottonPart < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int result;
        switch (operation) {
            case "equals" -> result = repository
                    .findByColorAndCottonPartEquals(color, cottonPart)
                    .getQuantity();
            case "moreThan" -> result = repository
                    .findByColorAndCottonPartGreaterThan(color, cottonPart)
                    .stream()
                    .mapToInt(Sock::getQuantity)
                    .sum();
            case "lessThan" -> result = repository
                    .findByColorAndCottonPartLessThan(color, cottonPart)
                    .stream()
                    .mapToInt(Sock::getQuantity)
                    .sum();
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    public void addOrIncrease(String color, int cottonPart, int quantity){

        if (color.isBlank() || quantity < 0 || cottonPart < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Sock s;
        if ((s = find(color, cottonPart)) != null){
            s.setQuantity(s.getQuantity() + quantity);
        }else{
            s = new Sock(color, cottonPart, quantity);
        }
        repository.save(s);
    }

    public void reduce(String color, int cottonPart, int quantity){

        if (color.isBlank() || quantity < 0 || cottonPart < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Sock s;
        if ((s = find(color, cottonPart)) != null){
            s.setQuantity(s.getQuantity() - quantity);
            System.out.println(s);
            if (s.getQuantity() == 0){
                repository.delete(s);
            }
            if (s.getQuantity() > 0){
                repository.save(s);
            }
            if (s.getQuantity() < 0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
