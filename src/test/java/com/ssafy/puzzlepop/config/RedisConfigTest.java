package com.ssafy.puzzlepop.config;

import com.ssafy.puzzlepop.engine.domain.Game;
import com.ssafy.puzzlepop.engine.domain.Room;
import com.ssafy.puzzlepop.engine.domain.User;
import com.ssafy.puzzlepop.engine.repository.GameRepository;
import com.ssafy.puzzlepop.engine.service.GameService;
import com.ssafy.puzzlepop.engine.vo.GameVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisConfigTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    @Test
    public void testRedisInsertAndRetrieve() {
        String key = "testKey";
        String value = "Hello, Redis!";

        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        valueOps.set(key, value);

        String retrievedValue = valueOps.get(key);

        assertThat(retrievedValue).isEqualTo(value);
    }

    @Test
    public void insertTest() {
        String name = "room.getName()";
        String userid = "room.getUserid()";
        int roomSize = 4;
        String gameType = "COOPERATION";

        Room room = new Room();
        room.setUserid(userid);
        room.setName(name);
        room.setRoomSize(roomSize);
        room.setGameType(gameType);
        Game game = gameService.createRoom(room);
        game.enterPlayer(new User("message.getSender()", true, "sessionId"), "sessionId");

        gameRepository.save(game);
        Game byGameId = gameRepository.findByGameId(game.getGameId());
        assertThat(game).isEqualTo(byGameId);
    }

    @Test
    public void updateTest() {
        String name = "room.getName()";
        String userid = "room.getUserid()";
        int roomSize = 4;
        String gameType = "COOPERATION";

        Room room = new Room();
        room.setUserid(userid);
        room.setName(name);
        room.setRoomSize(roomSize);
        room.setGameType(gameType);
        Game game = gameService.createRoom(room);
        game.enterPlayer(new User("message.getSender()", true, "sessionId"), "sessionId");

        gameRepository.save(game);
        Game byGameId = gameRepository.findByGameId(game.getGameId());

//        Iterable<Game> before = gameRepository.findAll();
//
//        System.out.println("---------시작 전----------");
//        for (Game g : before) {
//            System.out.println(g);
//        }
        System.out.println(byGameId);

        gameService.startGame(byGameId.getGameId());

        gameRepository.findByGameId(game.getGameId());
//        Iterable<Game> after = gameRepository.findAll();
//
//        System.out.println("---------시작 후----------");
//        for (Game g : after) {
//            System.out.println(g);
//        }
    }
}