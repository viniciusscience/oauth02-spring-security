package com.security.authservice.controller;

import com.security.authservice.entity.Agenda;
import com.security.authservice.repository.AgendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/auth/hello")
@RequiredArgsConstructor
public class AuthServiceController {

    private final AgendaRepository agendaRepository;

    @GetMapping
    public ResponseEntity<List<Agenda>> hello()  {
        var list =   agendaRepository.findAll();
        return ResponseEntity.ok(list);
    }
}
