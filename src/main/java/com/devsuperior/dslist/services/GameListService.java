package com.devsuperior.dslist.services;


import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projection.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {
    @Autowired
    private GameListRepository _gameListRepository;
    @Autowired
    private GameRepository _gameRepository;

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
    @Transactional
    public void move(long listId, int souceIdex,int destinationIndex){
        List<GameMinProjection> list = _gameRepository.searchByList(listId);
        GameMinProjection obj = list.remove(souceIdex);
        list.add(destinationIndex,obj);

        int min = souceIdex < destinationIndex ? souceIdex: destinationIndex;
        int max = souceIdex < destinationIndex ? destinationIndex: souceIdex;
        for (int i = min ; i <=max; i++){
            _gameListRepository.updateBelongingPosition(listId,list.get(i).getId(),i);

        }
    }


}
