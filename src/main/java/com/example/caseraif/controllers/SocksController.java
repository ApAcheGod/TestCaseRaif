package com.example.caseraif.controllers;

import com.example.caseraif.services.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private SocksService service;

    @Autowired
    public SocksController(SocksService service) {
        this.service = service;
    }

    @PostMapping("/income")
    public void add(@RequestParam(name = "color") Optional<String> color,
                    @RequestParam(name = "cottonPart") Optional<Integer> cottonPart,
                    @RequestParam(name = "quantity") Optional<Integer> quantity) {
        service.addOrIncrease(color.get(), cottonPart.orElse(-1), quantity.orElse(-1));
    }

    @PostMapping("/outcome")
    public void giveOut(@RequestParam(name = "color") Optional<String> color,
                        @RequestParam(name = "cottonPart") Optional<Integer> cottonPart,
                        @RequestParam(name = "quantity") Optional<Integer> quantity) {
        service.reduce(color.get(), cottonPart.orElse(-1), quantity.orElse(-1));
    }

    @GetMapping()
    public int getCountOfSocks(@RequestParam(name = "color") Optional<String> color,
                               @RequestParam(name = "operation") Optional<String> operation,
                               @RequestParam(name = "cottonPart") Optional<Integer> cottonPart){

        return service.findByOperation(color.orElse(""), operation.orElse(""), cottonPart.orElse(-1));
    }
}
