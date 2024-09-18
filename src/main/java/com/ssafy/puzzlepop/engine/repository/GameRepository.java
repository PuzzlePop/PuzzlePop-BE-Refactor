package com.ssafy.puzzlepop.engine.repository;

import com.ssafy.puzzlepop.engine.vo.GameVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<GameVO, Long> {

    GameVO findByGameId(Long GameId);
}
