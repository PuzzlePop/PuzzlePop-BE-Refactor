package com.ssafy.puzzlepop.engine.vo;

import com.nimbusds.oauth2.sdk.token.RefreshToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "game")
@EnableRedisRepositories(enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
public class GameVO {

    @Id
    @Indexed
    private Long gameId;

    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private long ttl;

    public GameVO update(String token, long ttl) {
        this.ttl = ttl;
        return this;
    }
}
