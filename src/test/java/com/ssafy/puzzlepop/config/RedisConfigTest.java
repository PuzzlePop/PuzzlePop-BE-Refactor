package com.ssafy.puzzlepop.config;

import com.ssafy.puzzlepop.engine.repository.GameRepository;
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
    public void crudTest() {
        GameVO gameVO = new GameVO(1l, 10000);

        GameVO save = gameRepository.save(gameVO);

        GameVO byGameId = gameRepository.findByGameId(1l);

        assertThat(gameVO.getGameId()).isEqualTo(byGameId.getGameId());
    }
}