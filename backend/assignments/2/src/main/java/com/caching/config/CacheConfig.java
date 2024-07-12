package com.caching.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

/**
 * Configuration class for setting up caching with Caffeine in a Spring application.
 */
@Configuration
public class CacheConfig {

    /**
     * Configures Caffeine with specific parameters for the cache.
     *
     * @return Configured Caffeine instance
     */
    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(60, TimeUnit.MINUTES);
    }

    /**
     * Configures the CacheManager with the provided Caffeine instance.
     *
     * @param caffeine Configured Caffeine instance
     * @return Configured CacheManager
     */
    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }
}
