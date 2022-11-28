package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.InfoService;

import java.util.Optional;

@RestController
@RequestMapping(path = "info")
public class InfoController {
    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping(path = "/getNumber")
    public ResponseEntity<Integer> getNumber() {
        return ResponseEntity.ok(infoService.getNumber());
    }

    @GetMapping(path = "/getPort")
    public ResponseEntity<Integer> getPort() {
        return ResponseEntity.ok(infoService.getPort());
    }



}
