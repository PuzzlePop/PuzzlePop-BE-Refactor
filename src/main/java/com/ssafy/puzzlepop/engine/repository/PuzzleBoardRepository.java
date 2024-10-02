package com.ssafy.puzzlepop.engine.repository;

import com.ssafy.puzzlepop.engine.domain.PuzzleBoard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuzzleBoardRepository extends CrudRepository<PuzzleBoard, String> {
}
