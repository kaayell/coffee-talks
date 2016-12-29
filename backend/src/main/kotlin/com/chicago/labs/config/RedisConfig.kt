package com.chicago.labs.config

import com.chicago.labs.humans.Human
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories


@Configuration
@EnableRedisRepositories
open class RedisConfig {

    @Bean
    open fun redisConnectionFactory(): RedisConnectionFactory {
        return JedisConnectionFactory()
    }

    @Bean
    open fun redisTemplate(cf: RedisConnectionFactory): RedisTemplate<String, Human> {
        val redisTemplate = RedisTemplate<String, Human>()
        redisTemplate.connectionFactory = cf
        return redisTemplate
    }

}