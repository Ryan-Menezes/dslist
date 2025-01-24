package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.ReplacementDTO;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;

@Service
public class GameListService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        var lists = gameListRepository.findAll();
        return lists.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional
    public void move(Long id, ReplacementDTO data) {
        int sourceIndex = data.getSourceIndex();
        int destinationIndex = data.getDestinationIndex();

        var games = gameRepository.searchByList(id);

        var obj = games.remove(sourceIndex);
        games.add(destinationIndex, obj);

        int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        int max = sourceIndex > destinationIndex ? sourceIndex : destinationIndex;

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(id, games.get(i).getId(), i);
        }
    }
}
