package com.ssafy.puzzlepop.engine.repository;

import com.ssafy.puzzlepop.engine.domain.Game;
import com.ssafy.puzzlepop.engine.vo.GameVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, String> {

    Game findByGameId(String gameId);
    Game findByGameName(String name);

    List<Game> findByGameTypeOrderByGameIdDesc(String gameType);
}
