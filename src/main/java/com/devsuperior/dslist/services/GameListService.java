package com.devsuperior.dslist.services;


import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {
    @Autowired
    private GameListRepository _gameListRepository;

    //Transactional semelhante ao async
    @Transactional(readOnly = true)
    public GameListDTO findById(long id){
        //falta tratamento de id inexistente
        GameList result = _gameListRepository.findById(id).get();
        return new GameListDTO(result);
    }

    //Transactional semelhante ao async
    @Transactional(readOnly = true)

    public List<GameListDTO> findAll(){
        List<GameList> result = _gameListRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }

}
