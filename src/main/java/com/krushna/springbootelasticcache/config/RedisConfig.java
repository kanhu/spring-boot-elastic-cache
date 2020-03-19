package com.krushna.springbootelasticcache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.Arrays;


@Configuration
@EnableCaching
public class RedisConfig {

    @Value("${redis.hostname}")
    private String redisHostName;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.prefix}")
    private String redisPrefix;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHostName, redisPort);
        // If the AWS elastic cahce is running on the cluster mode, then you can pass the primary node and can use RedisClusterConfiguration
        // this will automatically find all the avilabale nodes and use them.
        // https://stackoverflow.com/questions/59289359/how-to-connect-aws-elasticache-redis-cluster-to-spring-boot-app
        //RedisClusterConfiguration redisStandaloneConfiguration= new RedisClusterConfiguration( Arrays.asList(new String[] {redisHostName+":"+redisPort}));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean(value = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Primary
    @Bean(name = "cacheManager") // Default cache manager is infinite
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().prefixKeysWith(redisPrefix)).build();
    }

    @Bean(name = "cacheManager1Hour")
    public CacheManager cacheManager1Hour(RedisConnectionFactory redisConnectionFactory) {
        Duration expiration = Duration.ofHours(1);
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().prefixKeysWith(redisPrefix).entryTtl(expiration)).build();
    }

}
