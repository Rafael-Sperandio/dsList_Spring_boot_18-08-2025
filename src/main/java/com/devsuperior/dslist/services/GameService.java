package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projection.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository _gameRepository;

    //Transactional semelhante ao async
    @Transactional(readOnly = true)
    public  GameDTO findById(long id){
        //falta tratamento de id inexistente
        Game result = _gameRepository.findById(id).get();
        return new GameDTO(result);
    }

    //Transactional semelhante ao async
    @Transactional(readOnly = true)

    public List<GameMinDTO> findAll(){
        List<Game> result = _gameRepository.findAll();
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

    public List<GameMinDTO> findByList(long listId){
        List<GameMinProjection> result = _gameRepository.searchByList(listId);
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }
}
