package com.devsuperior.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.services.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping(value ="/{id}")
    public ResponseEntity<GameDTO> findAById(@PathVariable Long id) {
        var game = gameService.findById(id);

        return ResponseEntity.ok(game);
    }

    @GetMapping
    public ResponseEntity<List<GameMinDTO>> findAll() {
        var games = gameService.findAll();

        return ResponseEntity.ok(games);
    }
}
