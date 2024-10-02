package com.ssafy.puzzlepop.engine.repository;

import com.ssafy.puzzlepop.engine.domain.Game;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, String> {

    Game findByGameId(String gameId);
    Game findByGameName(String name);

    List<Game> findByGameTypeOrderByGameIdDesc(String gameType);
}
